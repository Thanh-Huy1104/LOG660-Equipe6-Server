package com.equipe6.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "\"PaysProduction\"")
public class PaysProduction {

    @Id
    @Column(name = "\"nomPays\"", length = 60)
    private String nomPays;

    @ManyToMany(mappedBy = "paysProduction", fetch = FetchType.LAZY)
    private Set<Film> films = new HashSet<>();

    // Getters and Setters
    public String getNomPays() { return nomPays; }
    public void setNomPays(String nomPays) { this.nomPays = nomPays; }

    public Set<Film> getFilms() { return films; }
    public void setFilms(Set<Film> films) { this.films = films; }
}