package com.equipe6.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FilmGenreId implements Serializable {

    @Column(name = "\"idFilm\"", length = 10)
    private String idFilm;

    @Column(name = "\"nomGenre\"", length = 20)
    private String nomGenre;

    // Constructors
    public FilmGenreId() {}

    public FilmGenreId(String idFilm, String nomGenre) {
        this.idFilm = idFilm;
        this.nomGenre = nomGenre;
    }

    // Getters and Setters
    public String getIdFilm() { return idFilm; }
    public void setIdFilm(String idFilm) { this.idFilm = idFilm; }

    public String getNomGenre() { return nomGenre; }
    public void setNomGenre(String nomGenre) { this.nomGenre = nomGenre; }

    // hashCode and equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmGenreId that = (FilmGenreId) o;
        return Objects.equals(idFilm, that.idFilm) &&
                Objects.equals(nomGenre, that.nomGenre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFilm, nomGenre);
    }
}