package fr.digi.cda2024.entite;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "realisateur")
public class Realisateur implements Serializable {

    @EmbeddedId
    private CleDeuxFacteurs id;

    /** Liaison entre casting_principal et personne */
    @ManyToOne
    @MapsId("facteur1")
    @JoinColumn(name = "ID_PERSONNE")
    private Personne realisateur;

    /** Liaison entre casting_principal et film */
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

    public Personne getRealisateur() {
        return realisateur;
    }

    public Film getFilm() {
        return film;
    }

    /**
     * Fonction pour associer un réalisateur et son film
     * @param realisateur personne réalisant le film
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
     * Fonction pour associer un film et un réalisateur
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
