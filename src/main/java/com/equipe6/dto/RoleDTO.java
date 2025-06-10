package com.equipe6.dto;

public class RoleDTO {
    private String idPersonne;
    private String nomActeur;
    private String nomPersonnage;

    public RoleDTO(String idPersonne, String nomActeur, String nomPersonnage) {
        this.idPersonne = idPersonne;
        this.nomActeur = nomActeur;
        this.nomPersonnage = nomPersonnage;
    }

    public String getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(String idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getNomActeur() {
        return nomActeur;
    }

    public void setNomActeur(String nomActeur) {
        this.nomActeur = nomActeur;
    }

    public String getNomPersonnage() {
        return nomPersonnage;
    }

    public void setNomPersonnage(String nomPersonnage) {
        this.nomPersonnage = nomPersonnage;
    }
}
