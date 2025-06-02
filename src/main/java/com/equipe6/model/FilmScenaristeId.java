package com.equipe6.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FilmScenaristeId implements Serializable {

    @Column(name = "\"idFilm\"", length = 10)
    private String idFilm;

    @Column(name = "\"idScenariste\"", length = 36)
    private String idScenariste;

    // Constructors
    public FilmScenaristeId() {}

    public FilmScenaristeId(String idFilm, String idScenariste) {
        this.idFilm = idFilm;
        this.idScenariste = idScenariste;
    }

    // Getters and Setters
    public String getIdFilm() { return idFilm; }
    public void setIdFilm(String idFilm) { this.idFilm = idFilm; }

    public String getIdScenariste() { return idScenariste; }
    public void setIdScenariste(String idScenariste) { this.idScenariste = idScenariste; }

    // hashCode and equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmScenaristeId that = (FilmScenaristeId) o;
        return Objects.equals(idFilm, that.idFilm) &&
                Objects.equals(idScenariste, that.idScenariste);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFilm, idScenariste);
    }
}