package com.equipe6.model;

import jakarta.persistence.*;

@Entity
@Table(name = "\"FilmScenariste\"")
// Represents the many-to-many relationship between Film and Scenariste
@IdClass(FilmScenaristeId.class)
public class FilmScenariste {

    @Id
    @Column(name = "\"idFilm\"", length = 10)
    private String idFilm; // PK, FK to Film

    @Id
    @Column(name = "\"idScenariste\"", length = 36)
    private String idScenariste; // PK, FK to Scenariste

    // Getters and Setters
    public String getIdFilm() { return idFilm; }
    public void setIdFilm(String idFilm) { this.idFilm = idFilm; }

    public String getIdScenariste() { return idScenariste; }
    public void setIdScenariste(String idScenariste) { this.idScenariste = idScenariste; }
}