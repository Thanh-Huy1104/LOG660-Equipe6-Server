package com.equipe6.dto;

public class RoleDTO {
    private String nomActeur;
    private String nomPersonnage;

    public RoleDTO() {}

    public RoleDTO(String nomActeur, String nomPersonnage) {
        this.nomActeur = nomActeur;
        this.nomPersonnage = nomPersonnage;
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
