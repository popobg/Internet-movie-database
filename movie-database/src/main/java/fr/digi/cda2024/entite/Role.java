package fr.digi.cda2024.entite;

import jakarta.persistence.*;

/**
 * table de jointure entre film et personne
 */
@Entity
@Table(name = "role")
public class Role {
    /**
     * l'id de la tabnle de jointure role
     */
    @EmbeddedId
    private cleRole id;
    /**
     * liaison entre role et personne
     */
    @ManyToOne
    @MapsId("personneId")
    @JoinColumn(name = "ID_PERSONNE")
    private Personne acteur;
    /**
     * liaison entre role et film
     */
    @ManyToOne
    @MapsId("filmId")
    @JoinColumn(name = "ID_FILM")
    private Film film;

    /**
     * donnee supplementaire valide sur une personne qui est acteur uniquement
     */
    @Column(name ="URL")
    private String url;

    /**
     * constructeur vide
     */
    public Role() {
    }

    /**
     * constructeur parametre
     * @param id
     * @param personne
     * @param film
     * @param url
     */
    public Role(cleRole id, Personne personne, Film film, String url) {
        this.id = id;
        this.acteur = personne;
        this.film = film;
        this.url = url;
    }

    /**
     * Getter
     *
     * @return id
     */

    public cleRole getId() {
        return id;
    }

    /**
     * Setter
     *
     * @param id id
     */


    public void setId(cleRole id) {
        this.id = id;
    }

    /**
     * Getter
     *
     * @return personne
     */

    public Personne getActeur() {
        return acteur;
    }

    /**
     * Setter
     *
     * @param personne personne
     */


    public void setActeur(Personne personne) {
        this.acteur = personne;
    }

    /**
     * Getter
     *
     * @return film
     */

    public Film getFilm() {
        return film;
    }

    /**
     * Setter
     *
     * @param film film
     */


    public void setFilm(Film film) {
        this.film = film;
    }

    /**
     * Getter
     *
     * @return url
     */

    public String getUrl() {
        return url;
    }

    /**
     * Setter
     *
     * @param url url
     */


    public void setUrl(String url) {
        this.url = url;
    }
}
