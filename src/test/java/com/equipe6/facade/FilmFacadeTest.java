package com.equipe6.facade;

import com.equipe6.dao.FilmDAO;
import com.equipe6.dto.FilmDTO;
import com.equipe6.dto.FilmDetailDTO;
import com.equipe6.model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class FilmFacadeTest {

    private FilmDAO filmDAOMock;
    private FilmFacade filmFacade;

    @BeforeEach
    void setUp() {
        filmDAOMock = mock(FilmDAO.class);
        filmFacade = new FilmFacade(filmDAOMock);
    }

    @Test
    void getFilmById_shouldReturnDetailDTO_whenFilmExists() {
        Film film = new Film();
        film.setIdFilm("F1");
        film.setTitre("Inception");
        film.setAnneeSortie(2010);
        film.setLangue("English");
        film.setDuree(148);
        film.setResume("A mind-bending thriller.");
        film.setAffiche("inception.jpg");

        Personne realisateur = new Personne();
        realisateur.setNom("Christopher Nolan");
        film.setRealisateur(realisateur);

        Genre genre = new Genre();
        genre.setNomGenre("Sci-Fi");
        film.setGenres(Set.of(genre));

        when(filmDAOMock.findById("F1")).thenReturn(film);

        FilmDetailDTO result = filmFacade.getFilmById("F1");

        // Assert
        assertNotNull(result);
        assertEquals("Inception", result.getTitre());
        assertEquals(2010, result.getAnneeSortie());
        assertEquals("Christopher Nolan", result.getNomRealisateur());
        assertTrue(result.getGenres().contains("Sci-Fi"));
    }

    @Test
    void getFilmById_shouldReturnNull_whenFilmDoesNotExist() {
        when(filmDAOMock.findById("invalid")).thenReturn(null);

        FilmDetailDTO result = filmFacade.getFilmById("invalid");

        assertNull(result);
    }

    @Test
    void searchFilms_shouldReturnListOfDTOs_whenFilmsExist() {
        Film film = new Film();
        film.setIdFilm("F2");
        film.setTitre("The Matrix");
        film.setAnneeSortie(1999);
        film.setLangue("English");
        film.setDuree(136);
        film.setResume("A hacker discovers reality is a simulation.");
        film.setAffiche("matrix.jpg");

        Genre genre = new Genre();
        genre.setNomGenre("Action");
        film.setGenres(Set.of(genre));

        when(filmDAOMock.searchByParams(anyMap())).thenReturn(List.of(film));

        Map<String, String[]> params = new HashMap<>();
        params.put("titre", new String[]{"Matrix"});

        List<FilmDTO> results = filmFacade.searchFilms(params);

        assertEquals(1, results.size());
        assertEquals("The Matrix", results.get(0).getTitre());
        assertTrue(results.get(0).getGenres().contains("Action"));
    }
}
