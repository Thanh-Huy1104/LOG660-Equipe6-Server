package com.equipe6.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "\"Location\"")
public class Location {

    @Id
    @Column(name = "\"idLocation\"", length = 10)
    private String idLocation;

    @Temporal(TemporalType.DATE)
    @Column(name = "\"dateDebut\"", nullable = false)
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    @Column(name = "\"dateFin\"", nullable = false)
    private Date dateFin;

    @Temporal(TemporalType.DATE)
    @Column(name = "\"dateRetourEffectif\"")
    private Date dateRetourEffectif;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"idClient\"", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"idCopie\"", nullable = false)
    private Copie copie;

    // Getters and Setters
    public String getIdLocation() { return idLocation; }
    public void setIdLocation(String idLocation) { this.idLocation = idLocation; }

    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }

    public Date getDateFin() { return dateFin; }
    public void setDateFin(Date dateFin) { this.dateFin = dateFin; }

    public Date getDateRetourEffectif() { return dateRetourEffectif; }
    public void setDateRetourEffectif(Date dateRetourEffectif) { this.dateRetourEffectif = dateRetourEffectif; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public Copie getCopie() { return copie; }
    public void setCopie(Copie copie) { this.copie = copie; }
}