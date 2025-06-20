package com.equipe6.resources;

import com.equipe6.dto.PersonneDTO;
import com.equipe6.facade.PersonneFacade;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PersonneResourceTest {

    private PersonneFacade personneFacadeMock;
    private PersonneResource personneResource;

    @BeforeEach
    void setUp() {
        personneFacadeMock = mock(PersonneFacade.class);
        personneResource = new PersonneResource(personneFacadeMock);
    }

    @Test
    void getPersonneById_shouldReturn200_whenPersonExists() {
        // Arrange
        String id = "456";
        PersonneDTO dto = new PersonneDTO(
                "Marie Curie",
                "1867-11-07",
                "Varsovie",
                "photo.png",
                "Scientifique polonaise"
        );

        when(personneFacadeMock.getPersonneById(id)).thenReturn(dto);

        // Act
        Response response = personneResource.getPersonneById(id);

        // Assert
        assertEquals(200, response.getStatus());
        PersonneDTO result = (PersonneDTO) response.getEntity();
        assertNotNull(result);
        assertEquals("Marie Curie", result.getNom());
        assertEquals("Varsovie", result.getLieuNaissance());
    }

    @Test
    void getPersonneById_shouldReturn404_whenPersonNotFound() {
        // Arrange
        when(personneFacadeMock.getPersonneById("999")).thenReturn(null);

        // Act
        Response response = personneResource.getPersonneById("999");

        // Assert
        assertEquals(404, response.getStatus());
        assertEquals("Personne introuvable", response.getEntity());
    }
}
