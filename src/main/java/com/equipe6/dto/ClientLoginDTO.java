package com.equipe6.dto;

public class ClientLoginDTO {
    private String idUser;
    private String nom;
    private String prenom;
    private String courriel;

    public ClientLoginDTO(String idUser, String nom, String prenom, String courriel) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.courriel = courriel;
    }

    // Getters and setters
    public String getIdUser() { return idUser; }
    public void setIdUser(String idUser) { this.idUser = idUser; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getCourriel() { return courriel; }
    public void setCourriel(String courriel) { this.courriel = courriel; }
}
