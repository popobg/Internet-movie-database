package fr.digi.cda2024.entite;

import jakarta.persistence.*;

/**
 * Table de jointure entre film et personne
 */
@Entity
@Table(name = "role")
public class Role {

    /** L'id de la table de jointure role */
    @EmbeddedId
    private cleRole id;

    /** Liaison entre role et personne */
    @ManyToOne
    @MapsId("personneId")
    @JoinColumn(name = "ID_PERSONNE")
    private Personne acteur;

    /** Liaison entre role et film */
    @ManyToOne
    @MapsId("filmId")
    @JoinColumn(name = "ID_FILM")
    private Film film;

    /** Donnee supplementaire valide sur une personne qui est acteur uniquement */
    @Column(name ="URL")
    private String url;

    /** Constructeur vide */
    public Role() {
    }

    /**
     * Constructeur parametre
     * @param role rôle
     * @param personne acteur
     * @param film film
     * @param url url de l'acteur pour ce rôle
     */
    public Role(String role, Personne personne, Film film, String url) {

        this.id = new cleRole(personne.getId(), film.getId(),role);
        this.url = url;
        personne.addRole(this, film);
    }

    /**
     * Getter
     * @return id
     */
    public cleRole getId() {
        return id;
    }

    /**
     * Setter
     * @param id id
     */
    public void setId(cleRole id) {
        this.id = id;
    }

    /**
     * Getter
     * @return personne
     */
    public Personne getActeur() {
        return acteur;
    }

    /**
     * Getter
     * @return film
     */
    public Film getFilm() {
        return film;
    }

    /**
     * Getter
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Setter
     * @param url url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Fonction pour associer un acteur et son role
     * @param acteur acteur
     */
    public void setActeur(Personne acteur) {
        if (this.acteur != null) {
            this.acteur.getRoles().remove(this);
        }
        this.acteur = acteur;
        if (this.acteur != null) {
            this.acteur.getRoles().add(this);
        }
    }

    /**
     * Fonction pour associer un film et un role
     * @param film film
     */
    public void setFilm(Film film) {
        if (this.film != null) {
            this.film.getActeurs().remove(this);
        }
        this.film = film;
        if (this.film != null) {
            this.film.getActeurs().add(this);
        }
    }

}
