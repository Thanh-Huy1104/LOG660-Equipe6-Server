package com.equipe6.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "\"Scenariste\"")
public class Scenariste {

    @Id
    @Column(name = "\"idScenariste\"", length = 36)
    private String idScenariste;

    @Column(name = "\"nom\"", nullable = false, length = 255)
    private String nom;

    @ManyToMany(mappedBy = "scenaristes", fetch = FetchType.LAZY)
    private Set<Film> films = new HashSet<>();

    // Getters and Setters
    public String getIdScenariste() { return idScenariste; }
    public void setIdScenariste(String idScenariste) { this.idScenariste = idScenariste; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public Set<Film> getFilms() { return films; }
    public void setFilms(Set<Film> films) { this.films = films; }
}