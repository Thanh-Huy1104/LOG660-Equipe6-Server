package com.equipe6.dto;

import com.equipe6.model.Client;
import com.equipe6.model.Utilisateur;

public class ClientDTO {
    private String idUser; // Unique identifier for the client
    private String motDePasse;
    private String prenom;
    private String nom;
    private String courriel;
    private String codeForfait;

    public ClientDTO(Client client) {
        Utilisateur u = client.getUtilisateur();
        this.idUser = u.getIdUser();
        this.motDePasse = u.getMotDePasse(); // Assuming you want to include the password
        this.prenom = u.getPrenom();
        this.nom = u.getNom();
        this.courriel = u.getCourriel();
        this.codeForfait = client.getForfait() != null
                ? client.getForfait().getCodeForfait()
                : null; // Assuming Forfait has a getCodeForfait() method
    }

    // Getters and Setters
    public String getIdUser() { return idUser; }
    public void setIdUser(String idUser) { this.idUser = idUser; }
    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getCourriel() { return courriel; }
    public void setCourriel(String courriel) { this.courriel = courriel; }
    public String getCodeForfait() { return codeForfait; }
    public void setCodeForfait(String codeForfait) { this.codeForfait = codeForfait; }
}
