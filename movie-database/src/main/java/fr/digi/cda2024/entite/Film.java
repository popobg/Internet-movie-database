package fr.digi.cda2024.entite;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Représente un film avec un identifiant unique, un nom, un résumé, une langue,
 * une date de sortie, et une URL associée. Cette classe est mappée sur la table "film"
 * de la base de données.
 */
@Entity
@Table(name = "film")
public class Film implements Serializable {

    /**
     * Identifiant unique du film.
     * Ce champ est auto-généré par la base de données.
     */
    @Id
    private String id;

    /**
     * Nom du film.
     * Ce champ est obligatoire, unique, et sa longueur maximale est de 50 caractères.
     */
    @Column(name = "NOM", length = 50, nullable = false, unique = true)
    private String nom;

    /**
     * Résumé du film.
     * Longueur maximale de 1000 caractères.
     */
    @Column(name = "RESUME", length = 1000)
    private String resume;

    /**
     * Langue du film.
     * Ce champ est obligatoire et sa longueur maximale est de 50 caractères.
     */
    @Column(name = "LANGUE", length = 50, nullable = false)
    private String langue;

    /** Date de sortie du film */
    @Column(name = "ANNEE_SORTIE")
    private LocalDate anneeSortie;

    /**
     * URL associée au film.
     * Ce champ est unique et sa longueur maximale est de 50 caractères.
     */
    @Column(name = "URL", length = 50, unique = true)
    private String url;

    /** Nombre de films tourne a cette adresse */
    @ManyToMany(mappedBy = "films")
    private Set<Adresse> adresses;

    /** Listes des acteurs dans le film */
    @OneToMany(mappedBy = "film")
    private Set<Role> acteurs;

    /** Ensemble de genre associé au film */
    @ManyToMany(mappedBy = "films")
    private Set<Genre> genres;

    /** Nationalite du film */
    @ManyToOne
    @JoinColumn(name = "ID_PAYS")
    private Pays pays;

    /** Realisateur associe au film */
    @ManyToMany(mappedBy = "filmsRealise")
    private Set<Personne> realisateurs;

    /** Casting principales associe au film */
    @ManyToMany(mappedBy = "filmsJoue")
    private Set<Personne> castingPrincipale;

   /* @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CastingPrincipale> castingsPrincipaux = new HashSet<>();*/

    /** Argument toujours present dans les constructeurs */
    {
        adresses = new HashSet<>();
        acteurs = new HashSet<>();
        genres = new HashSet<>();
        realisateurs = new HashSet<>();
        castingPrincipale = new HashSet<>();
    }

    /** Constructeur */
    public Film() {
    }

    /**
     * Getter
     * @return l'identifiant unique du film.
     */
    public String getId() {
        return id;
    }

    /**
     * Setter
     * @param id l'identifiant unique à définir pour le film.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter
     * @return le nom du film.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter
     * @param nom le nom du film à définir.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter
     * @return le résumé du film.
     */
    public String getResume() {
        return resume;
    }

    /**
     * Setter
     * @param resume le résumé du film à définir.
     */
    public void setResume(String resume) {
        this.resume = resume;
    }

    /**
     * Getter
     * @return la langue du film.
     */
    public String getLangue() {
        return langue;
    }

    /**
     * Setter
     * @param langue la langue du film à définir.
     */
    public void setLangue(String langue) {
        this.langue = langue;
    }

    /**
     * Getter
     * @return la date de sortie du film.
     */
    public LocalDate getAnneeSortie() {
        return anneeSortie;
    }

    /**
     * Setter
     * @param anneeSortie la date de sortie à définir pour le film.
     */
    public void setAnneeSortie(LocalDate anneeSortie) {
        this.anneeSortie = anneeSortie;
    }

    /**
     * Getter
     * @return l'URL associée au film.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Setter
     * @param url l'URL à définir pour le film.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Getter
     * @return adresses
     */
    public Set<Adresse> getAdresses() {
        return adresses;
    }

    /**
     * Getter
     * @return acteurs
     */
    public Set<Role> getActeurs() {
        return acteurs;
    }

    /**
     * Getter
     * @return genres
     */
    public Set<Genre> getGenres() {
        return genres;
    }

    /**
     * Getter
     * @return pays
     */
    public Pays getPays() {
        return pays;
    }

    /**
     * Getter
     * @return realisateurs
     */
    public Set<Personne> getRealisateurs() {
        return realisateurs;
    }

    /**
     * Getter
     * @return castingPrincipale
     */
    public Set<Personne> getCastingPrincipale() {
        return castingPrincipale;
    }

    /**
     * Ajoute une adresse de tournage au film
     * @param adresse
     */
    public void addAdresse(Adresse adresse) {
        if (adresse != null) {
            adresse.getFilms().add(this);
        }
        adresses.add(adresse);
    }


    /**
     * Supprime une adresse de tournage au film
     * @param adresse
     */
    public void removeAdresse(Adresse adresse) {
        if (adresse != null) {
            adresse.getFilms().remove(this);
        }
        adresses.remove(this);
    }

    /**
     * Ajoute une personne avec son role dans la liste des acteurs du film
     * @param role
     * @param personne
     */
    public void addActeur(Role role,Personne personne) {
        personne.addRole(role, this);
    }

    /**
     * Ajoute un genre au film
     * @param genre
     */
    public void addGenre(Genre genre) {
        if (genre != null) {
            genre.getFilms().add(this);
        }
        genres.add(genre);
    }

    /**
     * Supprime un genre du film
     * @param genre
     */
    public void removeGenre(Genre genre) {
        if (genre != null) {
            genre.getFilms().remove(this);
        }
        genres.remove(genre);
    }

    /**
     * change le pays ou le film a été tourné
     * @param pays
     */
    public void setPays(Pays pays) {
        if (this.pays != null) {
            this.pays.getFilms().remove(this);
        }
        this.pays = pays;
        if (this.pays != null) {
            this.pays.getFilms().add(this);
        }
    }
}