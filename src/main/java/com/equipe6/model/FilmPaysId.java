package com.equipe6.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FilmPaysId implements Serializable {

    @Column(name = "\"idFilm\"", length = 10)
    private String idFilm;

    @Column(name = "\"nomPays\"", length = 60)
    private String nomPays;

    // Constructors
    public FilmPaysId() {}

    public FilmPaysId(String idFilm, String nomPays) {
        this.idFilm = idFilm;
        this.nomPays = nomPays;
    }

    // Getters and Setters
    public String getIdFilm() { return idFilm; }
    public void setIdFilm(String idFilm) { this.idFilm = idFilm; }

    public String getNomPays() { return nomPays; }
    public void setNomPays(String nomPays) { this.nomPays = nomPays; }

    // hashCode and equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmPaysId that = (FilmPaysId) o;
        return Objects.equals(idFilm, that.idFilm) &&
                Objects.equals(nomPays, that.nomPays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFilm, nomPays);
    }
}