package com.equipe6.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "\"Client\"")
public class Client {

    @Id
    @Column(name = "\"idUser\"", length = 10)
    private String idUser;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "\"idUser\"")
    private Utilisateur utilisateur;

    @Column(name = "\"carteCreditNumero\"", nullable = false, unique = true, length = 19)
    private String carteCreditNumero;

    @Column(name = "\"carteCreditExpMois\"", nullable = false)
    private int carteCreditExpMois;

    @Column(name = "\"carteCreditExpAnnee\"", nullable = false)
    private int carteCreditExpAnnee;

    @Column(name = "\"carteCreditCVV\"", nullable = false)
    private int carteCreditCVV;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"carteCreditType\"", nullable = false)
    private DomaineCarteCredit domaineCarteCredit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"codeForfait\"", nullable = false)
    private Forfait forfait;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Location> locations = new HashSet<>();

    // Getters and Setters
    public String getIdUser() { return idUser; }
    public void setIdUser(String idUser) { this.idUser = idUser; }

    public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }

    public String getCarteCreditNumero() { return carteCreditNumero; }
    public void setCarteCreditNumero(String carteCreditNumero) { this.carteCreditNumero = carteCreditNumero; }

    public int getCarteCreditExpMois() { return carteCreditExpMois; }
    public void setCarteCreditExpMois(int carteCreditExpMois) { this.carteCreditExpMois = carteCreditExpMois; }

    public int getCarteCreditExpAnnee() { return carteCreditExpAnnee; }
    public void setCarteCreditExpAnnee(int carteCreditExpAnnee) { this.carteCreditExpAnnee = carteCreditExpAnnee; }

    public int getCarteCreditCVV() { return carteCreditCVV; }
    public void setCarteCreditCVV(int carteCreditCVV) { this.carteCreditCVV = carteCreditCVV; }

    public DomaineCarteCredit getDomaineCarteCredit() { return domaineCarteCredit; }
    public void setDomaineCarteCredit(DomaineCarteCredit domaineCarteCredit) { this.domaineCarteCredit = domaineCarteCredit; }

    public Forfait getForfait() { return forfait; }
    public void setForfait(Forfait forfait) { this.forfait = forfait; }

    public Set<Location> getLocations() { return locations; }
    public void setLocations(Set<Location> locations) { this.locations = locations; }
}