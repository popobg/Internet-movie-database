package fr.digi.cda2024.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Objet Personne intermédiaire récupéré après parsing du fichier JSON
 */
public class PersonneDTO implements Serializable {
    /**
     * id de la personne
     */
    private String id;

    /**
     * Nom et prénom de la personne
     */
    private String identite;

    /**
     * Objet naissance de la personne
     */
    private NaissanceDTO naissance;

    /**
     * url rattaché à la personne
     */
    private String url;

    /**
     * taille de la personne
     */
    @JsonProperty("height")
    private double taille;

    /**
     * Rôles incarnés par la personne
     */
    private String[] roles;

    /** Getter
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
     * @return identite
     */
    public String getIdentite() {
        return identite;
    }

    /** Setter
     * @param identite identite
     */
    public void setIdentite(String identite) {
        this.identite = identite;
    }

    /** Getter
     * @return naissance
     */
    public NaissanceDTO getNaissance() {
        return naissance;
    }

    /** Setter
     * @param naissance naissance
     */
    public void setNaissance(NaissanceDTO naissance) {
        this.naissance = naissance;
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
     * @return taille
     */
    public double getTaille() {
        return taille;
    }

    /** Setter
     * @param taille taille
     */
    public void setTaille(double taille) {
        this.taille = taille;
    }

    /** Getter
     * @return roles
     */
    public String[] getRoles() {
        return roles;
    }

    /** Setter
     * @param roles roles
     */
    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}
