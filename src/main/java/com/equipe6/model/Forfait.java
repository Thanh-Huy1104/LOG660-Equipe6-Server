package com.equipe6.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "\"Forfait\"")
public class Forfait {

    @Id
    @Column(name = "\"codeForfait\"", length = 10)
    private String codeForfait;

    @Column(name = "\"coûtMensuel\"", nullable = false, precision = 10, scale = 2)
    private BigDecimal coutMensuel; // Changed from coûtMensuel to coutMensuel for Java naming convention

    @Column(name = "\"locationsMax\"", nullable = false)
    private int locationsMax;

    @Column(name = "\"dureeMaxJours\"", nullable = false)
    private int dureeMaxJours;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"type\"", nullable = false) // This "type" column refers to DomaineForfait's PK
    private DomaineForfait domaineForfait; // Renamed from "type" to avoid conflict with a potential keyword

    @OneToMany(mappedBy = "forfait", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Client> clients = new HashSet<>();

    // Getters and Setters
    public String getCodeForfait() { return codeForfait; }
    public void setCodeForfait(String codeForfait) { this.codeForfait = codeForfait; }

    public BigDecimal getCoutMensuel() { return coutMensuel; }
    public void setCoutMensuel(BigDecimal coutMensuel) { this.coutMensuel = coutMensuel; }

    public int getLocationsMax() { return locationsMax; }
    public void setLocationsMax(int locationsMax) { this.locationsMax = locationsMax; }

    public int getDureeMaxJours() { return dureeMaxJours; }
    public void setDureeMaxJours(int dureeMaxJours) { this.dureeMaxJours = dureeMaxJours; }

    public DomaineForfait getDomaineForfait() { return domaineForfait; }
    public void setDomaineForfait(DomaineForfait domaineForfait) { this.domaineForfait = domaineForfait; }

    public Set<Client> getClients() { return clients; }
    public void setClients(Set<Client> clients) { this.clients = clients; }
}