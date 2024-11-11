package fr.digi.cda2024.entite;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

/**
 * Sert a creer la cle primaire de la classe role
 * qui est une table de jointure entre personne et film
 */
@Embeddable
public class cleRole implements Serializable {

    /** Cle permettant la liaison entre role et personne */
    @Column(name ="ID_PERSONNE")
    private String personneId;

    /** Cle permettant la liaison entre role et film */
    @Column(name ="ID_FILM")
    private String filmId;

    /**
     * Cle primaire supplementaire permettant a une personne
     * de jouer plusieur role different dans un meme film
     */
    @Column(name ="NOM_ROLE")
    private String nomRole;

    /** Constructeur vide */
    public cleRole() {
    }

    /**
     * Constructeur parametre
     * @param personneId identifiant d'une personne
     * @param filmId identifiant d'un film
     * @param nomRole nom du personnage
     */
    public cleRole(String personneId, String filmId, String nomRole) {
        this.personneId = personneId;
        this.filmId = filmId;
        this.nomRole = nomRole;
    }

    /**
     * Getter
     * @return personneId
     */
    public String getPersonneId() {
        return personneId;
    }

    /**
     * Setter
     * @param personneId personneId
     */
    public void setPersonneId(String personneId) {
        this.personneId = personneId;
    }

    /**
     * Getter
     * @return filmId
     */
    public String getFilmId() {
        return filmId;
    }

    /**
     * Setter
     * @param filmId filmId
     */
    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    /**
     * Getter
     * @return nomRole
     */
    public String getNomRole() {
        return nomRole;
    }

    /**
     * Setter
     * @param nomRole nomRole
     */
    public void setNomRole(String nomRole) {
        this.nomRole = nomRole;
    }
}
