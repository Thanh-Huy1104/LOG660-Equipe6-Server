package com.equipe6.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "\"DomaineCopie\"")
public class DomaineCopie {

    @Id
    @Column(name = "\"etat\"", length = 10)
    private String etat;

    @OneToMany(mappedBy = "domaineCopie", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Copie> copies = new HashSet<>();

    // Getters and Setters
    public String getEtat() { return etat; }
    public void setEtat(String etat) { this.etat = etat; }

    public Set<Copie> getCopies() { return copies; }
    public void setCopies(Set<Copie> copies) { this.copies = copies; }
}