package com.equipe6.resources;

import com.equipe6.dto.FilmDTO;
import com.equipe6.dto.FilmDetailDTO;
import com.equipe6.facade.FilmFacade;
import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FilmResourceTest {

    private FilmFacade filmFacade;
    private FilmResource filmResource;

    @BeforeEach
    void setUp() {
        filmFacade = mock(FilmFacade.class);
        filmResource = new FilmResource(filmFacade);
    }

    @Test
    void getFilmById_shouldReturnFilm_whenExists() {
        FilmDetailDTO dto = new FilmDetailDTO("F1", "Inception", 2010, "EN", 148, "Mind heist",
                "inception.jpg", "Nolan",
                List.of("Sci-Fi"), List.of("USA"), List.of("Nolan"), List.of(), List.of(), 5);

        when(filmFacade.getFilmById("F1")).thenReturn(dto);

        Response response = filmResource.getFilmById("F1");

        assertEquals(200, response.getStatus());
        assertEquals(dto, response.getEntity());
    }

    @Test
    void getFilmById_shouldReturn404_whenNotFound() {
        when(filmFacade.getFilmById("unknown")).thenReturn(null);

        Response response = filmResource.getFilmById("unknown");

        assertEquals(404, response.getStatus());
        assertEquals("Film introuvable", response.getEntity());
    }

    @Test
    void searchFilms_shouldReturnResults_whenQueryGiven() {
        FilmDTO dto = new FilmDTO("F1", "Inception", 2010, "EN", 148, "Heist in dreams",
                "inception.jpg", List.of("Sci-Fi"));

        UriInfo uriInfo = mock(UriInfo.class);
        MultivaluedHashMap<String, String> query = new MultivaluedHashMap<>();
        query.add("titre", "Inception");

        when(uriInfo.getQueryParameters()).thenReturn(query);
        when(filmFacade.searchFilms(anyMap())).thenReturn(List.of(dto));

        Response response = filmResource.searchFilms(uriInfo);

        assertEquals(200, response.getStatus());
        List<FilmDTO> result = (List<FilmDTO>) response.getEntity();
        assertEquals(1, result.size());
        assertEquals("Inception", result.get(0).getTitre());
    }

}
