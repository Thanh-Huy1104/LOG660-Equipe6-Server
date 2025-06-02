package com.equipe6.model;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Role\"")
public class Role {

    @Id
    @Column(name = "\"idRole\"", length = 36)
    private String idRole;

    @Column(name = "\"personnage\"", length = 255)
    private String personnage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"idFilm\"", nullable = false)
    private Film film;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"idActeur\"", nullable = false)
    private Personne acteur;

    // Getters and Setters
    public String getIdRole() { return idRole; }
    public void setIdRole(String idRole) { this.idRole = idRole; }

    public String getPersonnage() { return personnage; }
    public void setPersonnage(String personnage) { this.personnage = personnage; }

    public Film getFilm() { return film; }
    public void setFilm(Film film) { this.film = film; }

    public Personne getActeur() { return acteur; }
    public void setActeur(Personne acteur) { this.acteur = acteur; }
}