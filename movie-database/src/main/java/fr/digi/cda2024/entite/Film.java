package fr.digi.cda2024.entite;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
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
    @Column(name ="ID")
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
    private String anneeSortie;

    /**
     * URL associée au film.
     * Ce champ est unique et sa longueur maximale est de 50 caractères.
     */
    @Column(name = "URL", length = 50, unique = true)
    private String url;

    /** Liste des films tourne a cette adresse */
    @ManyToMany(mappedBy = "films")
    private Set<Adresse> adresses;

    /** Liste des acteurs dans le film */
    @OneToMany(mappedBy = "film")
    private Set<Role> acteurs;

    /** Liste des genres associés au film */
    @ManyToMany(mappedBy = "films")
    private Set<Genre> genres;

    /** Nationalite du film */
    @ManyToOne
    @JoinColumn(name = "ID_PAYS")
    private Pays pays;

    /** Liste des realisateurs dans le film */
    @OneToMany(mappedBy = "film")
    private Set<Realisateur> realisateurs;

    /** Liste du Casting principal dans le film */
    @OneToMany(mappedBy = "film", cascade = CascadeType.PERSIST)
    private Set<CastingPrincipal> castingsPrincipaux = new HashSet<>();

    /** Attributs present dans tous les constructeurs */
    {
        adresses = new HashSet<>();
        acteurs = new HashSet<>();
        genres = new HashSet<>();
        realisateurs = new HashSet<>();
        castingsPrincipaux = new HashSet<>();
    }

    /** Constructeur */
    public Film() {
    }

    /**
     * constructeur parametre
     * @param url
     * @param id
     * @param nom
     * @param resume
     * @param langue
     * @param anneeSortie
     * @param pays
     */
    public Film(String url, String id, String nom, String resume, String langue, String anneeSortie, Pays pays) {
        this.url = url;
        this.id = id;
        this.nom = nom;
        this.resume = resume;
        this.langue = langue;
        this.anneeSortie = anneeSortie;
        this.pays = pays;
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
    public String getAnneeSortie() {
        return anneeSortie;
    }

    /**
     * Setter
     * @param anneeSortie la date de sortie à définir pour le film.
     */
    public void setAnneeSortie(String anneeSortie) {
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
     * @return castingsPrincipaux
     */
    public Set<CastingPrincipal> getCastingsPrincipaux() {
        return castingsPrincipaux;
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
    public Set<Realisateur> getRealisateurs() {
        return realisateurs;
    }

    /**
     * Ajoute une adresse de tournage au film
     * @param adresse adresse
     */
    public void addAdresse(Adresse adresse) {
        if (adresse != null) {
            adresse.getFilms().add(this);
        }
        adresses.add(adresse);
    }

    /**
     * Supprime une adresse de tournage au film
     * @param adresse adresse
     */
    public void removeAdresse(Adresse adresse) {
        if (adresse != null) {
            adresse.getFilms().remove(this);
        }
        adresses.remove(adresse);
    }

    /**
     * Ajoute une personne avec son role dans la liste des acteurs du film
     * @param role role
     * @param personne personne
     */
    public void addActeur(Role role,Personne personne) {
        personne.addRole(role, this);
    }

    /**
     * Ajoute une personne avec son role dans la liste des acteurs du film
     * @param realisateur realisateur
     * @param personne personne
     */
    public void addRealisateur(Realisateur realisateur,Personne personne) {
        personne.addRealisateur(realisateur, this);
    }

    /**
     * Ajoute une personne au casting princiaple du film
     * @param castingPrincipal casting princiaple
     * @param personne personne
     */
    public void addActeurCastingPrincipal(CastingPrincipal castingPrincipal,Personne personne) {
        personne.addCastingPrincipal(castingPrincipal, this);
    }

    /**
     * Ajoute un genre au film
     * @param genre genre
     */
    public void addGenre(Genre genre) {
        if (genre != null) {
            genre.getFilms().add(this);
        }
        genres.add(genre);
    }

    /**
     * Supprime un genre du film
     * @param genre genre
     */
    public void removeGenre(Genre genre) {
        if (genre != null) {
            genre.getFilms().remove(this);
        }
        genres.remove(genre);
    }

    /**
     * Set la valeur du pays du film
     * @param pays pays
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

    /** Methode equals permet de verifier l'egalite entre differente instance */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Film film)) return false;
        return Objects.equals(id, film.id) && Objects.equals(nom, film.nom) && Objects.equals(resume, film.resume) && Objects.equals(langue, film.langue) && Objects.equals(anneeSortie, film.anneeSortie) && Objects.equals(url, film.url) && Objects.equals(pays, film.pays);
    }

    /** Methode hashcode */
    @Override
    public int hashCode() {
        return Objects.hash(id, nom, resume, langue, anneeSortie, url, pays);
    }

    /** Methode d'affichage */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Film{");
        sb.append("id='").append(id).append('\'');
        sb.append(", nom='").append(nom).append('\'');
        sb.append(", resume='").append(resume).append('\'');
        sb.append(", langue='").append(langue).append('\'');
        sb.append(", anneeSortie=").append(anneeSortie);
        sb.append(", url='").append(url).append('\'');
        sb.append(", pays=").append(pays);
        sb.append("}\n");
        return sb.toString();
    }
}