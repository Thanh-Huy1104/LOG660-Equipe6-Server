package com.equipe6.dto;

import java.util.Date;

public class LocationDTO {
    private String idLocation;
    private String idCopie;
    private String dateDebut;
    private String dateFin;

    // Constructors
    public LocationDTO() {
    }

    public LocationDTO(String idLocation, String idCopie, String dateDebut, String dateFin) {
        this.idLocation = idLocation;
        this.idCopie = idCopie;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    // Getters and Setters
    public String getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(String idLocation) {
        this.idLocation = idLocation;
    }

    public String getIdCopie() {
        return idCopie;
    }

    public void setIdCopie(String idCopie) {
        this.idCopie = idCopie;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }
}
