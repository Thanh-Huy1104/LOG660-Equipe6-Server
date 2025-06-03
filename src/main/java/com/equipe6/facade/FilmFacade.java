package com.equipe6.facade;

import com.equipe6.dao.FilmDAO;
import com.equipe6.model.Film;

import java.util.List;
import java.util.Map;

public class FilmFacade {

    private final FilmDAO filmDAO = new FilmDAO();

    /**
     * Retrieve a film by ID
     */
    public Film getFilmById(String id) {
        return filmDAO.findById(id);
    }

    /**
     * Search films based on dynamic criteria (delegates to DAO)
     */
    public List<Film> searchFilms(Map<String, String[]> params) {
        return filmDAO.searchByParams(params);
    }

    /**
     * Save or update a film (delegates to DAO)
     */
    public void saveFilm(Film film) {
        filmDAO.save(film);
    }

    /**
     * Delete a film
     */
    public void deleteFilm(Film film) {
        filmDAO.delete(film);
    }
}
