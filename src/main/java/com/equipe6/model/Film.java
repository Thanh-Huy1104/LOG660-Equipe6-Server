package com.equipe6.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "\"Film\"")
public class Film {

    @Id
    @Column(name = "\"idFilm\"", length = 10)
    private String idFilm;

    @Column(name = "\"titre\"", nullable = false, length = 255)
    private String titre;

    @Column(name = "\"anneeSortie\"", nullable = false)
    private int anneeSortie;

    @Column(name = "\"langue\"", length = 50)
    private String langue;

    @Column(name = "\"dureeFilm\"", nullable = false)
    private int duree;

    @Lob
    @Column(name = "\"resume\"")
    private String resume;

    @Column(name = "\"affiche\"", length = 255)
    private String affiche;

    // --- Relationships ---

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"idRealisateur\"", nullable = false)
    private Personne realisateur;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Copie> copies = new HashSet<>();

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<BandeAnnonce> bandeAnnonces = new HashSet<>();

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "\"FilmGenre\"",
            joinColumns = @JoinColumn(name = "\"idFilm\""),
            inverseJoinColumns = @JoinColumn(name = "\"nomGenre\"")
    )
    private Set<Genre> genres = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "\"FilmPays\"",
            joinColumns = @JoinColumn(name = "\"idFilm\""),
            inverseJoinColumns = @JoinColumn(name = "\"nomPays\"")
    )
    private Set<PaysProduction> paysProduction = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "\"FilmScenariste\"",
            joinColumns = @JoinColumn(name = "\"idFilm\""),
            inverseJoinColumns = @JoinColumn(name = "\"idScenariste\"")
    )
    private Set<Scenariste> scenaristes = new HashSet<>();

    // --- Getters and Setters ---
    public String getIdFilm() { return idFilm; }
    public void setIdFilm(String id) { this.idFilm = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public int getAnneeSortie() { return anneeSortie; }
    public void setAnneeSortie(int anneeSortie) { this.anneeSortie = anneeSortie; }

    public String getLangue() { return langue; }
    public void setLangue(String langue) { this.langue = langue; }

    public int getDuree() { return duree; }
    public void setDuree(int duree) { this.duree = duree; }

    public String getResume() { return resume; }
    public void setResume(String resume) { this.resume = resume; }

    public String getAffiche() { return affiche; }
    public void setAffiche(String affiche) { this.affiche = affiche; }

    public Personne getRealisateur() { return realisateur; }
    public void setRealisateur(Personne realisateur) { this.realisateur = realisateur; }

    public Set<Copie> getCopies() { return copies; }
    public void setCopies(Set<Copie> copies) { this.copies = copies; }

    public Set<BandeAnnonce> getBandeAnnonces() { return bandeAnnonces; }
    public void setBandeAnnonces(Set<BandeAnnonce> bandeAnnonces) { this.bandeAnnonces = bandeAnnonces; }

    public Set<Role> getRoles() { return roles; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }

    public Set<Genre> getGenres() { return genres; }
    public void setGenres(Set<Genre> genres) { this.genres = genres; }

    public Set<PaysProduction> getPaysProduction() { return paysProduction; }
    public void setPaysProduction(Set<PaysProduction> paysProduction) { this.paysProduction = paysProduction; }

    public Set<Scenariste> getScenaristes() { return scenaristes; }
    public void setScenaristes(Set<Scenariste> scenaristes) { this.scenaristes = scenaristes; }

    // Optional: Convenience methods for managing bidirectional @ManyToMany relationships
    public void addGenre(Genre genre) {
        this.genres.add(genre);
        genre.getFilms().add(this);
    }
    public void removeGenre(Genre genre) {
        this.genres.remove(genre);
        genre.getFilms().remove(this);
    }
    public void addPaysProduction(PaysProduction pays) {
        this.paysProduction.add(pays);
        pays.getFilms().add(this);
    }
    public void removePaysProduction(PaysProduction pays) {
        this.paysProduction.remove(pays);
        pays.getFilms().remove(this);
    }
    public void addScenariste(Scenariste scenariste) {
        this.scenaristes.add(scenariste);
        scenariste.getFilms().add(this);
    }
    public void removeScenariste(Scenariste scenariste) {
        this.scenaristes.remove(scenariste);
        scenariste.getFilms().remove(this);
    }
}