package com.equipe6.model;

import jakarta.persistence.*;

@Entity
@Table(name = "\"FilmPays\"")
@IdClass(FilmPaysId.class) // Or use @EmbeddedId with FilmPaysId
public class FilmPays {

    @Id
    @Column(name = "\"idFilm\"", length = 10)
    private String idFilm; // PK, FK to Film

    @Id
    @Column(name = "\"nomPays\"", length = 60)
    private String nomPays; // PK, FK to PaysProduction

    // Getters and Setters
    public String getIdFilm() { return idFilm; }
    public void setIdFilm(String idFilm) { this.idFilm = idFilm; }

    public String getNomPays() { return nomPays; }
    public void setNomPays(String nomPays) { this.nomPays = nomPays; }
}