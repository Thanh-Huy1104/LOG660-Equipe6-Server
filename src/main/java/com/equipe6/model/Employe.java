package com.equipe6.model;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Employe\"")
public class Employe {

    @Id
    @Column(name = "\"idUser\"", length = 10)
    private String idUser;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "\"idUser\"")
    private Utilisateur utilisateur;

    @Column(name = "\"matricule\"", nullable = false, unique = true)
    private int matricule;

    // Getters and Setters
    public String getIdUser() { return idUser; }
    public void setIdUser(String idUser) { this.idUser = idUser; }

    public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }

    public int getMatricule() { return matricule; }
    public void setMatricule(int matricule) { this.matricule = matricule; }
}