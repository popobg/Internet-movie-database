package fr.digi.cda2024.entite;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

/** Sert a creer la cle primaire de la classe CastingPrincipal */
@Embeddable
public class cleCastingPrincipal implements Serializable{

    /** Cle permettant la liaison entre casting_principal et personne */
    @Column(name = "ID_PERSONNE")
    private String personneId;

    /** Cle permettant la liaison entre casting_principal et film */
    @Column(name = "ID_FILM")
    private String filmId;

    /** Constructeur vide */
    public cleCastingPrincipal() {
    }

    /**
     * Constructeur parametre
     * @param personne
     * @param film
     */
    public cleCastingPrincipal(Personne personne, Film film) {
        this.personneId = personne.getId();
        this.filmId = film.getId();
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
}