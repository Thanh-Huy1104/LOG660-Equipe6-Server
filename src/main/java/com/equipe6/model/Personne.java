package com.equipe6.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "\"Personne\"")
public class Personne {

    @Id
    @Column(name = "\"idPersonne\"", length = 10)
    private String idPersonne;

    @Column(name = "\"nom\"", nullable = false, length = 255)
    private String nom;

    @Temporal(TemporalType.DATE)
    @Column(name = "\"dateNaissance\"")
    private Date dateNaissance;

    @Column(name = "\"lieuNaissance\"", length = 255)
    private String lieuNaissance;

    @Column(name = "\"photo\"", length = 255)
    private String photo;

    @Lob
    @Column(name = "\"biographie\"")
    private String biographie;

    @OneToMany(mappedBy = "realisateur", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Film> filmsRealises = new HashSet<>();

    @OneToMany(mappedBy = "acteur", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Role> rolesActeur = new HashSet<>();

    // Getters and Setters
    public String getIdPersonne() { return idPersonne; }
    public void setIdPersonne(String idPersonne) { this.idPersonne = idPersonne; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public Date getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(Date dateNaissance) { this.dateNaissance = dateNaissance; }

    public String getLieuNaissance() { return lieuNaissance; }
    public void setLieuNaissance(String lieuNaissance) { this.lieuNaissance = lieuNaissance; }

    public String getPhoto() { return photo; }
    public void setPhoto(String photo) { this.photo = photo; }

    public String getBiographie() { return biographie; }
    public void setBiographie(String biographie) { this.biographie = biographie; }

    public Set<Film> getFilmsRealises() { return filmsRealises; }
    public void setFilmsRealises(Set<Film> filmsRealises) { this.filmsRealises = filmsRealises; }

    public Set<Role> getRolesActeur() { return rolesActeur; }
    public void setRolesActeur(Set<Role> rolesActeur) { this.rolesActeur = rolesActeur; }
}