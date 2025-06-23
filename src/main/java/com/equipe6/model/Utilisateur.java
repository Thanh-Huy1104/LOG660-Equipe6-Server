package com.equipe6.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "\"Utilisateur\"")
public class Utilisateur {

    @Id
    @Column(name = "\"idUser\"", length = 10)
    private String idUser;

    @Column(name = "\"prenom\"", nullable = false, length = 255)
    private String prenom;

    @Column(name = "\"nom\"", nullable = false, length = 255)
    private String nom;

    @Column(name = "\"courriel\"", nullable = false, unique = true, length = 255)
    private String courriel;

    @Column(name = "\"motDePasse\"", nullable = false, length = 72)
    private String motDePasse;

    @Column(name = "\"telephone\"", nullable = false, length = 20)
    private String telephone;

    @Column(name = "\"adresse\"", nullable = false, length = 255)
    private String adresse;

    @Column(name = "\"ville\"", nullable = false, length = 255)
    private String ville;

    @Column(name = "\"province\"", nullable = false, length = 20)
    private String province;

    @Column(name = "\"codePostal\"", nullable = false, length = 10)
    private String codePostal;

    @Temporal(TemporalType.DATE)
    @Column(name = "\"dateNaissance\"", nullable = false)
    private Date dateNaissance;

    @OneToOne(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private Client clientProfil;

    @OneToOne(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private Employe employeProfil;

    // Getters and Setters
    public String getIdUser() { return idUser; }
    public void setIdUser(String idUser) { this.idUser = idUser; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getCourriel() { return courriel; }
    public void setCourriel(String courriel) { this.courriel = courriel; }

    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }

    public String getProvince() { return province; }
    public void setProvince(String province) { this.province = province; }

    public String getCodePostal() { return codePostal; }
    public void setCodePostal(String codePostal) { this.codePostal = codePostal; }

    public Date getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(Date dateNaissance) { this.dateNaissance = dateNaissance; }

    public Client getClientProfil() { return clientProfil; }
    public void setClientProfil(Client clientProfil) { this.clientProfil = clientProfil; }

    public Employe getEmployeProfil() { return employeProfil; }
    public void setEmployeProfil(Employe employeProfil) { this.employeProfil = employeProfil; }
}