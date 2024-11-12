package fr.digi.cda2024.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Objet Role intermédiaire obtenu après parsing du fichier JSON
 */
public class RoleDTO implements Serializable {
    /**
     * Nom du personnage
     */
    @JsonProperty("characterName")
    private String personnage;

    /**
     * Acteur qui incarne le rôle
     */
    private PersonneDTO acteur;

    /**
     * Id du film concerné
     */
    private String film;

    /** Getter
     * @return personnage
     */
    public String getPersonnage() {
        return personnage;
    }

    /** Setter
     * @param personnage personnage
     */
    public void setPersonnage(String personnage) {
        this.personnage = personnage;
    }

    /** Getter
     * @return acteur
     */
    public PersonneDTO getActeur() {
        return acteur;
    }

    /** Setter
     * @param acteur acteur
     */
    public void setPersonne(PersonneDTO acteur) {
        this.acteur = acteur;
    }

    /** Getter
     * @return film
     */
    public String getFilm() {
        return film;
    }

    /** Setter
     * @param film film
     */
    public void setFilm(String film) {
        this.film = film;
    }
}
