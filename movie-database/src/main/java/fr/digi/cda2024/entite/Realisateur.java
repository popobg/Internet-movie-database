package fr.digi.cda2024.entite;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * Table de jointure entre film et personne contenant les realisateurs du film
 */
@Entity
@Table(name = "realisateur")
public class Realisateur implements Serializable {

    /** Id issu de la classe CleDeuxFacteurs */
    @EmbeddedId
    private CleDeuxFacteurs id;

    /** Liaison entre realisateur et personne */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @MapsId("facteur1")
    @JoinColumn(name = "ID_PERSONNE")
    private Personne realisateur;

    /** Liaison entre realisateur et film */
    @ManyToOne
    @MapsId("facteur2")
    @JoinColumn(name = "ID_FILM")
    private Film film;

    /** Donnee supplementaire valide sur une personne qui est realisateur uniquement */
    @Column(name ="URL")
    private String url;

    /** Constructeur vide */
    public Realisateur() {
    }

    /**
     * Constructeur parametre
     * @param personne réalisateur
     * @param film film
     * @param url url
     */
    public Realisateur(Personne personne, Film film, String url) {
        this.id = new CleDeuxFacteurs(personne.getId(), film.getId());
        this.url = url;
        personne.addRealisateur(this, film);
    }

    /**
     * Getter
     * @return realisateur
     */
    public Personne getRealisateur() {
        return realisateur;
    }

    /**
     * Getter
     * @return film
     */
    public Film getFilm() {
        return film;
    }

    /**
     * Fonction pour associer un réalisateur et sa table realisateur
     * @param realisateur réalisateur
     */
    public void setRealisateur(Personne realisateur) {
        if (this.realisateur != null) {
            this.realisateur.getRealisateur().remove(this);
        }
        this.realisateur = realisateur;
        if (this.realisateur != null) {
            this.realisateur.getRealisateur().add(this);
        }
    }

    /**
     * Fonction pour associer un film et sa table realisateur
     * @param film film
     */
    public void setFilm(Film film) {
        if (this.film != null) {
            this.film.getActeurs().remove(this);
        }
        this.film = film;
        if (this.film != null) {
            this.film.getRealisateurs().add(this);
        }
    }
}
