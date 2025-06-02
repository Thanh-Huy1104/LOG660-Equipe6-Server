package com.equipe6.service;


import com.equipe6.model.Film;

import java.util.List;
import java.util.Map;

public interface FilmService {

    /**
     * Recherche des films à partir d'un ensemble de critères dynamiques.
     *
     * @param params Une map des paramètres (ex : titre, anneeMin, genre[], etc.)
     * @return Liste des films correspondant aux critères
     */
    List<Film> rechercherFilms(Map<String, String[]> params);

    /**
     * Recherche un film unique par son ID.
     *
     * @param id Identifiant du film
     * @return Film trouvé, ou null si inexistant
     */
    Film getFilmById(String id);
}
