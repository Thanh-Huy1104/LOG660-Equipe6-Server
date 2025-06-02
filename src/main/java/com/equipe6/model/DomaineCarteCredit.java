package com.equipe6.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "\"DomaineCarteCredit\"")
public class DomaineCarteCredit {

    @Id
    @Column(name = "\"carteCreditType\"", length = 10)
    private String carteCreditType;

    @OneToMany(mappedBy = "domaineCarteCredit", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Client> clients = new HashSet<>();

    // Getters and Setters
    public String getCarteCreditType() { return carteCreditType; }
    public void setCarteCreditType(String carteCreditType) { this.carteCreditType = carteCreditType; }

    public Set<Client> getClients() { return clients; }
    public void setClients(Set<Client> clients) { this.clients = clients; }
}