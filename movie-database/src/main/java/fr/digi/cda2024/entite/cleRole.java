package fr.digi.cda2024.entite;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

/**
 * Sert a creer la cle primaire de la classe role
 * qui est une table de jointure entre personne et film
 */
@Embeddable
public class cleRole implements Serializable {

    /** Cle permettant la liaison entre role et personne */
    private String personneId;

    /** Cle permettant la liaison entre role et film */
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
     * @param personneId Id d'une personne
     * @param filmId Id d'un film
     * @param nomRole nom d'un role
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

    /** Methode equals permet de verifier l'egalite entre differente instance */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof cleRole cleRole)) return false;
        return Objects.equals(personneId, cleRole.personneId) && Objects.equals(filmId, cleRole.filmId) && Objects.equals(nomRole, cleRole.nomRole);
    }

    /** Methode hashcode */
    @Override
    public int hashCode() {
        return Objects.hash(personneId, filmId, nomRole);
    }

    /** Methode d'affichage */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("cleRole{");
        sb.append("personneId='").append(personneId).append('\'');
        sb.append(", filmId='").append(filmId).append('\'');
        sb.append(", nomRole='").append(nomRole).append('\'');
        sb.append("}\n");
        return sb.toString();
    }
}
