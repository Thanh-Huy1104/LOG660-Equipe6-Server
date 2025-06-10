package com.equipe6.dto;

public class PersonneDTO {

    public PersonneDTO() {}

    public PersonneDTO(String nom, String dateNaissance, String lieuNaissance, String photo, String biographie) {
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.photo = photo;
        this.biographie = biographie;
    }

    private String nom;
    private String dateNaissance;
    private String lieuNaissance;
    private String photo;
    private String biographie;

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getDateNaissance() {
        return dateNaissance;
    }
    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    public String getLieuNaissance() {
        return lieuNaissance;
    }
    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public String getBiographie() {
        return biographie;
    }
    public void setBiographie(String biographie) {
        this.biographie = biographie;
    }
}
