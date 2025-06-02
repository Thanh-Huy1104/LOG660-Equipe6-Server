package com.equipe6.model;

import jakarta.persistence.*;

@Entity
@Table(name = "\"BandeAnnonce\"")
public class BandeAnnonce {

    @Id
    @Column(name = "\"idBandeAnnonce\"", length = 36)
    private String idBandeAnnonce;

    @Column(name = "\"url\"", nullable = false, length = 255)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"idFilm\"", nullable = false)
    private Film film;

    // Getters and Setters
    public String getIdBandeAnnonce() { return idBandeAnnonce; }
    public void setIdBandeAnnonce(String idBandeAnnonce) { this.idBandeAnnonce = idBandeAnnonce; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public Film getFilm() { return film; }
    public void setFilm(Film film) { this.film = film; }
}