package com.equipe6.model;

import jakarta.persistence.*;

@Entity
@Table(name = "\"FilmGenre\"")
//
@IdClass(FilmGenreId.class)
public class FilmGenre {

    @Id
    @Column(name = "\"idFilm\"", length = 10)
    private String idFilm; // PK, FK to Film

    @Id
    @Column(name = "\"nomGenre\"", length = 20)
    private String nomGenre; // PK, FK to Genre

    // Getters and Setters
    public String getIdFilm() { return idFilm; }
    public void setIdFilm(String idFilm) { this.idFilm = idFilm; }

    public String getNomGenre() { return nomGenre; }
    public void setNomGenre(String nomGenre) { this.nomGenre = nomGenre; }
}