package com.equipe6.facade;

import com.equipe6.dao.FilmDAO;
import com.equipe6.dto.FilmDTO;
import com.equipe6.model.Film;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FilmFacade {

    private final FilmDAO filmDAO = new FilmDAO();

    public FilmDTO getFilmById(String id) {
        Film film = filmDAO.findById(id);
        return (film != null) ? toDTO(film) : null;
    }

    public List<FilmDTO> searchFilms(Map<String, String[]> params) {
        List<Film> films = filmDAO.searchByParams(params);
        return films.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private FilmDTO toDTO(Film film) {
        return new FilmDTO(
                film.getIdFilm(),                                // idFilm
                film.getTitre(),                                 // titre
                film.getAnneeSortie(),                           // anneeSortie
                film.getLangue(),                                // langue
                film.getDuree(),                                 // duree
                film.getResume(),                                // resume
                film.getAffiche(),                               // affiche
                film.getRealisateur() != null
                        ? film.getRealisateur().getNom()
                        : null,                                      // nomRealisateur
                // scenaristes
//                film.getScenaristes().stream()
//                        .map(s -> s.getNom())
//                        .collect(Collectors.toList()),

                // roles
                film.getRoles().stream()
                        .map(role -> new com.equipe6.dto.RoleDTO(
                                role.getActeur() != null ? role.getActeur().getNom() : null,
                                role.getPersonnage()
                        ))
                        .collect(Collectors.toList())
        );
    }
}
