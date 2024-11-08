package fr.digi.cda2024.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Objet Film intermédiaire récupéré après parsing du fichier JSON
 */
public class FilmDTO implements Serializable {
    /**
     * Identifiant du film
     */
    private String id;

    /**
     * Nationalité du film
     */
    private PaysDTO pays;

    /**
     * Nom du film
     */
    private String nom;

    /**
     * url du film
     */
    private String url;

    /**
     * Résumé du film
     */
    @JsonProperty("plot")
    private String resume;

    /**
     * Langue du film
     */
    private String langue;

    /**
     * Lieux de tournage
     */
    private AdresseDTO lieuTournage;

    /**
     * Réalisateurs du film
     */
    private PersonneDTO[] realisateurs;

    /**
     * Acteurs principaux, têtes d'affiche
     */
    private PersonneDTO[] castingPrincipal;

    /**
     * Année de sortie du film
     */
    private String anneeSortie;

    /**
     * Rôles du film
     */
    private RoleDTO[] roles;

    /**
     * Genres du film
     */
    private String[] genres;

    /**Getter
     * @return id
     */
    public String getId() {
        return id;
    }

    /** Setter
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /** Getter
     * @return pays
     */
    public PaysDTO getPays() {
        return pays;
    }

    /** Setter
     * @param pays pays
     */
    public void setPays(PaysDTO pays) {
        this.pays = pays;
    }

    /** Getter
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /** Setter
     * @param nom nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /** Getter
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /** Setter
     * @param url url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /** Getter
     * @return resume
     */
    public String getResume() {
        return resume;
    }

    /** Setter
     * @param resume resume
     */
    public void setResume(String resume) {
        this.resume = resume;
    }

    /** Getter
     * @return langue
     */
    public String getLangue() {
        return langue;
    }

    /** Setter
     * @param langue langue
     */
    public void setLangue(String langue) {
        this.langue = langue;
    }

    /** Getter
     * @return lieuTournage
     */
    public AdresseDTO getLieuTournage() {
        return lieuTournage;
    }

    /** Setter
     * @param lieuTournage lieuTournage
     */
    public void setLieuTournage(AdresseDTO lieuTournage) {
        this.lieuTournage = lieuTournage;
    }

    /** Getter
     * @return realisateurs
     */
    public PersonneDTO[] getRealisateurs() {
        return realisateurs;
    }

    /** Setter
     * @param realisateurs realisateurs
     */
    public void setRealisateurs(PersonneDTO[] realisateurs) {
        this.realisateurs = realisateurs;
    }

    /** Getter
     * @return castingPrincipal
     */
    public PersonneDTO[] getCastingPrincipal() {
        return castingPrincipal;
    }

    /** Setter
     * @param castingPrincipal castingPrincipal
     */
    public void setCastingPrincipal(PersonneDTO[] castingPrincipal) {
        this.castingPrincipal = castingPrincipal;
    }

    /** Getter
     * @return anneeSortie
     */
    public String getAnneeSortie() {
        return anneeSortie;
    }

    /** Setter
     * @param anneeSortie anneeSortie
     */
    public void setAnneeSortie(String anneeSortie) {
        this.anneeSortie = anneeSortie;
    }

    /** Getter
     * @return roles
     */
    public RoleDTO[] getRoles() {
        return roles;
    }

    /** Setter
     * @param roles roles
     */
    public void setRoles(RoleDTO[] roles) {
        this.roles = roles;
    }

    /** Getter
     * @return genres
     */
    public String[] getGenres() {
        return genres;
    }

    /** Setter
     * @param genres genres
     */
    public void setGenres(String[] genres) {
        this.genres = genres;
    }
}
