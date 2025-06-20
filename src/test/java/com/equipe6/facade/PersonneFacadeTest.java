package com.equipe6.facade;

import com.equipe6.dao.PersonneDAO;
import com.equipe6.dto.PersonneDTO;
import com.equipe6.model.Personne;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PersonneFacadeTest {

    private PersonneDAO personneDAO;
    private PersonneFacade personneFacade;

    @BeforeEach
    void setUp() {
        personneDAO = mock(PersonneDAO.class);
        personneFacade = new PersonneFacade(personneDAO);
    }

    @Test
    void getPersonneById_shouldReturnDTO_whenPersonExists() {
        // Arrange
        Personne personne = new Personne();
        personne.setNom("Jean Dupont");
        personne.setDateNaissance(new Date(90, 0, 1));
        personne.setLieuNaissance("Paris");
        personne.setPhoto("photo.jpg");
        personne.setBiographie("Un acteur célèbre.");

        when(personneDAO.findById("P123")).thenReturn(personne);

        // Act
        PersonneDTO result = personneFacade.getPersonneById("P123");

        // Assert
        assertNotNull(result);
        assertEquals("Jean Dupont", result.getNom());
        assertEquals("Paris", result.getLieuNaissance());
        assertEquals("photo.jpg", result.getPhoto());
        assertEquals("Un acteur célèbre.", result.getBiographie());
        assertNotNull(result.getDateNaissance());
    }

    @Test
    void getPersonneById_shouldReturnNull_whenPersonNotFound() {
        when(personneDAO.findById("P999")).thenReturn(null);

        PersonneDTO result = personneFacade.getPersonneById("P999");

        assertNull(result);
    }
}
