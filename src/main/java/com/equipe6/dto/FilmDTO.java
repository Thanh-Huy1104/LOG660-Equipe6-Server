package com.equipe6.dto;

import com.equipe6.model.Genre;

import java.util.List;

public class FilmDTO {
    private String idFilm;
    private String titre;
    private int anneeSortie;
    private String langue;
    private int duree;
    private String resume;
    private String affiche;
    private List<String> genres;

    public FilmDTO() {}

    public FilmDTO(String idFilm, String titre, int anneeSortie, String langue, int duree,
                   String resume, String affiche,
                   List<String> genres
                   ) {
        this.idFilm = idFilm;
        this.titre = titre;
        this.anneeSortie = anneeSortie;
        this.langue = langue;
        this.duree = duree;
        this.resume = resume;
        this.affiche = affiche;
        this.genres = genres;
    }

    // Getters and Setters
    public String getIdFilm() { return idFilm; }
    public void setIdFilm(String idFilm) { this.idFilm = idFilm; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public int getAnneeSortie() { return anneeSortie; }
    public void setAnneeSortie(int anneeSortie) { this.anneeSortie = anneeSortie; }

    public String getLangue() { return langue; }
    public void setLangue(String langue) { this.langue = langue; }

    public int getDuree() { return duree; }
    public void setDuree(int duree) { this.duree = duree; }

    public String getResume() { return resume; }
    public void setResume(String resume) { this.resume = resume; }

    public String getAffiche() { return affiche; }
    public void setAffiche(String affiche) { this.affiche = affiche; }

    public List<String> getGenres() { return genres; }
    public void setGenres(List<String> genres) { this.genres = genres; }
}
