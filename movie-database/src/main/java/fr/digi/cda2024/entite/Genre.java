package fr.digi.cda2024.entite;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import java.util.Set;

/**
 * Représente un genre avec un identifiant unique et un nom.
 * Cette classe est mappée sur la table "genre" de la base de données.
 */
@Entity
@Table(name = "genre")
public class Genre implements Serializable {

    /**
     * Identifiant unique du genre.
     * Ce champ est auto-généré par la base de données.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Nom du genre.
     * Ce champ est obligatoire, unique, et sa longueur maximale est de 50 caractères.
     */
    @Column(name = "NOM", length = 50, nullable = false, unique = true)
    private String nom;

    /** Ensemble des films associé au genre */
    @ManyToMany
    @JoinTable(
            name = "integre",
            joinColumns = @JoinColumn(name = "ID_GENRE", referencedColumnName="ID"),
            inverseJoinColumns = @JoinColumn (name = "ID_FILM", referencedColumnName = "ID")
    )
    private Set<Film> films;


    /** Constructeur vide */
    public Genre() {
    }

    /**
     * Getter
     * @return le nom du genre.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter
     * @param nom le nom du genre à définir.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter
     * @return l'identifiant unique du genre.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Getter
     * @return films
     */
    public Set<Film> getFilms() {
        return films;
    }

    /**
     * rajoute un film qui a ce genre
     * @param film
     */
    public void addFilm(Film film) {
        if (film != null) {
            film.addGenre(this);
        }
    }

    /**
     * supprime un film qui a ce genre
     * @param film
     */
    public void removeFilm(Film film) {
        if (film != null) {
            film.removeGenre(this);
        }
    }

    /**
     * methode equals permet de verifier l'egalite entre differente instance
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre genre)) return false;
        return Objects.equals(id, genre.id) && Objects.equals(nom, genre.nom);
    }

    /**
     * methode hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, nom);
    }

    /**
     * methode d'affichage
     */

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Genre{");
        sb.append("id=").append(id);
        sb.append(", nom='").append(nom).append('\'');
        sb.append(", films=").append(films);
        sb.append("}\n");
        return sb.toString();
    }
}
