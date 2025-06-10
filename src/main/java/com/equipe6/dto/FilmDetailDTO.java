package com.equipe6.dto;

import java.util.List;

public class FilmDetailDTO {
    private String idFilm;
    private String titre;
    private int anneeSortie;
    private String langue;
    private int duree;
    private String resume;
    private String affiche;
    private String nomRealisateur;
    private List<String> genres;
    private List<String> pays;
    private List<String> scenaristes;
    private List<String> annonces;
    private List<RoleDTO> roles;
    private long nombreCopiesDisponibles;

    public FilmDetailDTO() {};

    public FilmDetailDTO(String idFilm, String titre, int anneeSortie, String langue, int duree,
                         String resume, String affiche, String nomRealisateur,
                         List<String> genres, List<String> pays,
                         List<String> scenaristes,
                         List<RoleDTO> roles, List<String> annonces, long nombreCopiesDisponibles) {
        this.idFilm = idFilm;
        this.titre = titre;
        this.anneeSortie = anneeSortie;
        this.langue = langue;
        this.duree = duree;
        this.resume = resume;
        this.affiche = affiche;
        this.nomRealisateur = nomRealisateur;
        this.genres = genres;
        this.pays = pays;
        this.scenaristes = scenaristes;
        this.roles = roles;
        this.annonces = annonces;
        this.nombreCopiesDisponibles = nombreCopiesDisponibles;
    }

    // Getters and Setters
    public String getIdFilm() {
        return idFilm;
    }
    public void setIdFilm(String idFilm) {
        this.idFilm = idFilm;
    }

    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public int getAnneeSortie() {
        return anneeSortie;
    }
    public void setAnneeSortie(int anneeSortie) {
        this.anneeSortie = anneeSortie;
    }
    public String getLangue() {
        return langue;
    }
    public void setLangue(String langue) {
        this.langue = langue;
    }
    public int getDuree() {
        return duree;
    }
    public void setDuree(int duree) {
        this.duree = duree;
    }
    public String getResume() {
        return resume;
    }
    public void setResume(String resume) {
        this.resume = resume;
    }
    public String getAffiche() {
        return affiche;
    }
    public void setAffiche(String affiche) {
        this.affiche = affiche;
    }
    public String getNomRealisateur() {
        return nomRealisateur;
    }
    public void setNomRealisateur(String nomRealisateur) {
        this.nomRealisateur = nomRealisateur;
    }
    public List<String> getGenres() {
        return genres;
    }
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
    public List<String> getPays() {
        return pays;
    }
    public void setPays(List<String> pays) {
        this.pays = pays;
    }
    public List<String> getScenaristes() {
        return scenaristes;
    }
    public void setScenaristes(List<String> scenaristes) {
        this.scenaristes = scenaristes;
    }
    public List<RoleDTO> getRoles() {
        return roles;
    }
    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }
    public List<String> getAnnonces() {
        return annonces;
    }
    public void setAnnonces(List<String> annonces) {
        this.annonces = annonces;
    }

    public long getNombreCopiesDisponibles() {
        return nombreCopiesDisponibles;
    }

    public void setNombreCopiesDisponibles(long nombreCopiesDisponibles) {
        this.nombreCopiesDisponibles = nombreCopiesDisponibles;
    }

    @Override
    public String toString() {
        return "FilmDetailDTO{" +
                "idFilm='" + idFilm + '\'' +
                ", titre='" + titre + '\'' +
                ", anneeSortie=" + anneeSortie +
                ", langue='" + langue + '\'' +
                ", duree=" + duree +
                ", resume='" + resume + '\'' +
                ", affiche='" + affiche + '\'' +
                ", nomRealisateur='" + nomRealisateur + '\'' +
                ", genres=" + genres +
                ", pays=" + pays +
                ", scenaristes=" + scenaristes +
                ", roles=" + roles +
                ", annonces=" + annonces +
                ", nombreCopiesDisponibles=" + nombreCopiesDisponibles +
                '}';
    }
}
