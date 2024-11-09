package fr.digi.cda2024.entite;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

/**
 * sert a creer la cle primaire de la classe role
 * qui est une table de jointure entre personne et film
 */
@Embeddable
public class cleRole implements Serializable {
    /**
     *     cle permettant la liaison entre role et personne
     */
    @Column(name ="ID_PERSONNE")
    private String personneId;
    /**
     * cle permettant la liaison entre role et film
     */
    @Column(name ="ID_FILM")
    private String filmId;
    /**
     * cle primaire supplementaire permettant a une personne
     * de jouer plusieur role different dans un meme film
     */
    @Column(name ="NOM_ROLE")
    private String nomRole;

    /**
     * constructeur vide
     */
    public cleRole() {
    }

    /**
     * constructeur parametre
     * @param personneId
     * @param filmId
     * @param nomRole
     */
    public cleRole(String personneId, String filmId, String nomRole) {
        this.personneId = personneId;
        this.filmId = filmId;
        this.nomRole = nomRole;
    }

    /**
     * Getter
     *
     * @return personneId
     */

    public String getPersonneId() {
        return personneId;
    }

    /**
     * Setter
     *
     * @param personneId personneId
     */


    public void setPersonneId(String personneId) {
        this.personneId = personneId;
    }

    /**
     * Getter
     *
     * @return filmId
     */

    public String getFilmId() {
        return filmId;
    }

    /**
     * Setter
     *
     * @param filmId filmId
     */


    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    /**
     * Getter
     *
     * @return nomRole
     */

    public String getNomRole() {
        return nomRole;
    }

    /**
     * Setter
     *
     * @param nomRole nomRole
     */


    public void setNomRole(String nomRole) {
        this.nomRole = nomRole;
    }
}
