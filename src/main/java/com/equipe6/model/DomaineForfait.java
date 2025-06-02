package com.equipe6.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "\"DomaineForfait\"")
public class DomaineForfait {

    @Id
    @Column(name = "\"type\"", length = 255)
    private String type;

    @OneToMany(mappedBy = "domaineForfait", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Forfait> forfaits = new HashSet<>();

    // Getters and Setters
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Set<Forfait> getForfaits() { return forfaits; }
    public void setForfaits(Set<Forfait> forfaits) { this.forfaits = forfaits; }
}