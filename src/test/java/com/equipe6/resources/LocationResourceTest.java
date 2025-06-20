package com.equipe6.resources;

import com.equipe6.dto.LocationDTO;
import com.equipe6.facade.LocationFacade;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LocationResourceTest {

    private LocationFacade locationFacade;
    private LocationResource locationResource;

    @BeforeEach
    void setUp() {
        locationFacade = mock(LocationFacade.class);
        locationResource = new LocationResource(locationFacade);
    }

    @Test
    void louerFilm_shouldReturn201_whenRentalSuccessful() throws Exception {
        LocationDTO dto = new LocationDTO("L001", "C001", "2024-06-20", "2024-06-30");
        when(locationFacade.createLocation("F123", "C001")).thenReturn(dto);

        Response response = locationResource.louerFilm("F123", "C001");

        assertEquals(201, response.getStatus());
        assertEquals(dto, response.getEntity());
    }

    @Test
    void louerFilm_shouldReturn400_whenMissingParams() {
        Response response = locationResource.louerFilm(null, "C001");
        assertEquals(400, response.getStatus());

        response = locationResource.louerFilm("F123", "");
        assertEquals(400, response.getStatus());
    }

    @Test
    void louerFilm_shouldReturn403_whenBusinessRuleViolated() throws Exception {
        when(locationFacade.createLocation("F123", "C001"))
                .thenThrow(new WebApplicationException("Forfait limit reached.", 403));

        Response response = locationResource.louerFilm("F123", "C001");

        assertEquals(403, response.getStatus());
        assertEquals("Forfait limit reached.", response.getEntity());
    }

    @Test
    void louerFilm_shouldReturn500_whenUnexpectedError() throws Exception {
        when(locationFacade.createLocation("F123", "C001"))
                .thenThrow(new RuntimeException("DB connection lost"));

        Response response = locationResource.louerFilm("F123", "C001");

        assertEquals(500, response.getStatus());
        assertEquals("An internal error occurred during the rental process.", response.getEntity());
    }

    @Test
    void ping_shouldReturnLiveMessage() {
        String result = locationResource.ping();
        assertEquals("LocationResource is live.", result);
    }
}
