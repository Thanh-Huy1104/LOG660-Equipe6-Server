package com.equipe6.facade;

import com.equipe6.dao.FilmDAO;
import com.equipe6.dto.FilmDTO;
import com.equipe6.dto.FilmDetailDTO;
import com.equipe6.dto.RoleDTO;
import com.equipe6.model.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FilmFacade {

    private final FilmDAO filmDAO;

    public FilmFacade(FilmDAO filmDAO) {
        this.filmDAO = filmDAO;
    }

    public FilmDetailDTO getFilmById(String id) {
        Film film = filmDAO.findById(id);
        return (film != null) ? toFilmDetailDTO(film) : null;
    }

    public List<FilmDTO> searchFilms(Map<String, String[]> params) {
        List<Film> films = filmDAO.searchByParams(params);
        return films.stream()
                .map(this::toFilmDTO)
                .collect(Collectors.toList());
    }

    private FilmDTO toFilmDTO(Film film) {
        return new FilmDTO(
                film.getIdFilm(),
                film.getTitre(),
                film.getAnneeSortie(),
                film.getLangue(),
                film.getDuree(),
                film.getResume(),
                film.getAffiche(),
                film.getGenres() != null
                        ? film.getGenres().stream()
                        .map(Genre::getNomGenre)
                        .collect(Collectors.toList())
                        : null
        );
    }

    private FilmDetailDTO toFilmDetailDTO(Film film) {
        return new FilmDetailDTO(
                film.getIdFilm(),
                film.getTitre(),
                film.getAnneeSortie(),
                film.getLangue(),
                film.getDuree(),
                film.getResume(),
                film.getAffiche(),
                film.getRealisateur().getNom(),
                film.getGenres() != null
                        ? film.getGenres().stream()
                        .map(Genre::getNomGenre)
                        .collect(Collectors.toList())
                        : null,
                film.getPaysProduction() != null
                        ? film.getPaysProduction().stream()
                        .map(PaysProduction::getNomPays)
                        .collect(Collectors.toList())
                        : null,
                film.getScenaristes() != null
                        ? film.getScenaristes().stream()
                        .map(Scenariste::getNom)
                        .collect(Collectors.toList())
                        : null,
                film.getRoles() != null
                        ? film.getRoles().stream()
                        .map(role -> new RoleDTO(role.getActeur().getIdPersonne(), role.getActeur().getNom(), role.getPersonnage()))
                        .collect(Collectors.toList())
                        : null,
                film.getBandeAnnonces() != null
                        ? film.getBandeAnnonces().stream()
                        .map(BandeAnnonce::getUrl)
                        .collect(Collectors.toList())
                        : null,
                film.getCopies() != null ?
                        film.getCopies().stream()
                                .filter(copie -> "Disponible".equals(copie.getDomaineCopie().getEtat()))
                                .count() : 0
        );


    }
}
