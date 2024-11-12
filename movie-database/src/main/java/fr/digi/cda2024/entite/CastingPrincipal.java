package fr.digi.cda2024.entite;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "casting_principal")
public class CastingPrincipal implements Serializable {

    @EmbeddedId
    private cleCastingPrincipal id;

    /** Liaison entre casting_principal et personne */
    @ManyToOne
    @MapsId("personneId")
    @JoinColumn(name = "ID_PERSONNE")
    private Personne acteur;

    /** Liaison entre casting_principal et film */
    @ManyToOne
    @MapsId("filmId")
    @JoinColumn(name = "ID_FILM")
    private Film film;

    /** Donnee supplementaire valide sur une personne qui est realisateur uniquement */
    @Column(name ="URL")
    private String url;

    /** Constructeur vide */
    public CastingPrincipal() {
    }

    /**
     * Constructeur parametre
     * @param personne
     * @param film
     * @param url
     */
    public CastingPrincipal(Personne personne, Film film, String url) {
        this.id = new cleCastingPrincipal(personne.getId(), film.getId());
        this.url = url;
        personne.addCastingPrincipal(this, film);
    }
    public Personne getActeur() {
        return acteur;
    }

    public Film getFilm() {
        return film;
    }

    /**
     * Fonction pour associer un acteur et son role
     * @param acteur
     */
    public void setActeur(Personne acteur) {
        if (this.acteur != null) {
            this.acteur.getCastingPrincipal().remove(this);
        }
        this.acteur = acteur;
        if (this.acteur != null) {
            this.acteur.getCastingPrincipal().add(this);
        }
    }

    /**
     * Fonction pour associer un film et un role
     * @param film
     */
    public void setFilm(Film film) {
        if (this.film != null) {
            this.film.getActeurs().remove(this);
        }
        this.film = film;
        if (this.film != null) {
            this.film.getCastingsPrincipaux().add(this);
        }
    }
}
