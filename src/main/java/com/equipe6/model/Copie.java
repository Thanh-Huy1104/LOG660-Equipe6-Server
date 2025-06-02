package com.equipe6.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "\"Copie\"")
public class Copie {

    @Id
    @Column(name = "\"code\"", length = 255)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"idFilm\"", nullable = false)
    private Film film;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"etat\"", nullable = false)
    private DomaineCopie domaineCopie; // Renamed from "etat" to avoid conflict if you had a field named "etat"

    @OneToMany(mappedBy = "copie", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Location> locations = new HashSet<>();

    // Getters and Setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public Film getFilm() { return film; }
    public void setFilm(Film film) { this.film = film; }

    public DomaineCopie getDomaineCopie() { return domaineCopie; }
    public void setDomaineCopie(DomaineCopie domaineCopie) { this.domaineCopie = domaineCopie; }

    public Set<Location> getLocations() { return locations; }
    public void setLocations(Set<Location> locations) { this.locations = locations; }
}