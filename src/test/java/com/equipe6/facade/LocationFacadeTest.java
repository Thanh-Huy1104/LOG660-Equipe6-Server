package com.equipe6.facade;

import com.equipe6.dao.ClientDAO;
import com.equipe6.dao.LocationDAO;
import com.equipe6.dto.LocationDTO;
import com.equipe6.model.*;

import com.equipe6.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.ws.rs.WebApplicationException;
import org.mockito.MockedStatic;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LocationFacadeTest {

    private ClientDAO clientDAO;
    private LocationDAO locationDAO;
    private LocationFacade locationFacade;

    private Session session;
    private SessionFactory sessionFactory;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        clientDAO = mock(ClientDAO.class);
        locationDAO = mock(LocationDAO.class);
        locationFacade = new LocationFacade(locationDAO, clientDAO);

        session = mock(Session.class);
        sessionFactory = mock(SessionFactory.class);
        transaction = mock(Transaction.class);

        when(session.beginTransaction()).thenReturn(transaction);
        when(sessionFactory.openSession()).thenReturn(session);
    }

    @Test
    void createLocation_shouldSucceed_whenAllConditionsAreMet() throws Exception {
        // Given
        Client client = new Client();
        client.setIdUser("123");
        client.setLocations(new HashSet<>());

        Forfait forfait = new Forfait();
        forfait.setCodeForfait("I"); // Intermediate: 5 max
        client.setForfait(forfait);

        Copie copie = new Copie();
        copie.setCode("COPY1");

        DomaineCopie domaineLouee = new DomaineCopie();
        domaineLouee.setEtat("louee");

        when(clientDAO.findById("123", session)).thenReturn(Optional.of(client));
        when(locationDAO.findAvailableCopy("F1", session)).thenReturn(Optional.of(copie));
        when(session.get(DomaineCopie.class, "louee")).thenReturn(domaineLouee);
        doNothing().when(locationDAO).save(any(Location.class), eq(copie), eq(session));

        // When
        LocationDTO result;
        try (MockedStatic<HibernateUtil> util = mockStatic(HibernateUtil.class)) {
            util.when(HibernateUtil::getSessionFactory).thenReturn(sessionFactory);
            result = locationFacade.createLocation("F1", "123");
        }

        // Then
        assertNotNull(result);
        assertEquals("COPY1", result.getIdCopie());
        assertNotNull(result.getDateDebut());
    }

    @Test
    void createLocation_shouldThrow403_whenLimitReached() {
        Client client = new Client();
        client.setIdUser("123");

        Set<Location> existing = new HashSet<>();
        for (int i = 0; i < 5; i++) existing.add(new Location());
        client.setLocations(existing); // Already has 5

        Forfait forfait = new Forfait();
        forfait.setCodeForfait("I"); // Max = 5
        client.setForfait(forfait);

        when(clientDAO.findById("123", session)).thenReturn(Optional.of(client));

        try (MockedStatic<HibernateUtil> util = mockStatic(HibernateUtil.class)) {
            util.when(HibernateUtil::getSessionFactory).thenReturn(sessionFactory);
            WebApplicationException ex = assertThrows(WebApplicationException.class,
                    () -> locationFacade.createLocation("F1", "123"));
            assertEquals(403, ex.getResponse().getStatus());
        }
    }

    @Test
    void createLocation_shouldThrow409_whenNoCopiesAvailable() {
        Client client = new Client();
        client.setIdUser("123");
        client.setLocations(new HashSet<>());

        Forfait forfait = new Forfait();
        forfait.setCodeForfait("I");
        client.setForfait(forfait);

        when(clientDAO.findById("123", session)).thenReturn(Optional.of(client));
        when(locationDAO.findAvailableCopy("F1", session)).thenReturn(Optional.empty());

        try (MockedStatic<HibernateUtil> util = mockStatic(HibernateUtil.class)) {
            util.when(HibernateUtil::getSessionFactory).thenReturn(sessionFactory);
            WebApplicationException ex = assertThrows(WebApplicationException.class,
                    () -> locationFacade.createLocation("F1", "123"));
            assertEquals(409, ex.getResponse().getStatus());
        }
    }

    @Test
    void createLocation_shouldThrow404_whenClientNotFound() {
        when(clientDAO.findById("notfound", session)).thenReturn(Optional.empty());

        try (MockedStatic<HibernateUtil> util = mockStatic(HibernateUtil.class)) {
            util.when(HibernateUtil::getSessionFactory).thenReturn(sessionFactory);
            WebApplicationException ex = assertThrows(WebApplicationException.class,
                    () -> locationFacade.createLocation("F1", "notfound"));
            assertEquals(404, ex.getResponse().getStatus());
        }
    }
}