package com.equipe6.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "\"Genre\"")
public class Genre {

    @Id
    @Column(name = "\"nomGenre\"", length = 20)
    private String nomGenre;

    @ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
    private Set<Film> films = new HashSet<>();

    // Getters and Setters
    public String getNomGenre() { return nomGenre; }
    public void setNomGenre(String nomGenre) { this.nomGenre = nomGenre; }

    public Set<Film> getFilms() { return films; }
    public void setFilms(Set<Film> films) { this.films = films; }
}