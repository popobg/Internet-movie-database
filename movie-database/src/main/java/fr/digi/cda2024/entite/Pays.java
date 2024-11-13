package fr.digi.cda2024.entite;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Représente un pays avec un identifiant unique, un nom, et une URL associée.
 * Cette classe est mappée sur la table "pays" de la base de données.
 */
@Entity
@Table(name = "pays")
public class Pays implements Serializable {

    /**
     * Identifiant unique de l'entité.
     * Ce champ est auto-généré par la base de données.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID")
    private Integer id;

    /**
     * Nom du pays.
     * Ce champ est obligatoire, unique, et sa longueur maximale est de 50 caractères.
     */
    @Column(name = "NOM")
    private String nom;

    /**
     * URL associée au pays.
     * Ce champ est obligatoire, unique, et sa longueur maximale est de 100 caractères.
     */
    @Column(name = "URL", unique = true)
    private String url;

    /** Liste des adresses dans le pays */
    @OneToMany(mappedBy = "pays", cascade = CascadeType.PERSIST)
    private Set<Adresse> adresses;

    /** Liste des films de cette nationalite */
    @OneToMany(mappedBy = "pays")
    private Set<Film> films;

    /** Attributs present dans tous les constructeurs */
    {
        adresses = new HashSet<>();
        films = new HashSet<>();
    }

    /** Constructeur vide*/
    public Pays() {
    }

    /** Constructeur parametre*/
    public Pays(String nom, String url) {
        this.nom = nom;
        this.url = url;
    }

    /**
     * Getter
     * @return films
     */
    public Set<Film> getFilms() {
        return films;
    }

    /**
     * Getter
     * @return l'identifiant unique du pays.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Getter
     * @return le nom du pays.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter
     * @param nom le nom du pays à définir.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter
     * @return l'URL associée au pays.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Setter
     * @param url l'URL à définir pour le pays.
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
     * Ajoute l'adresse dans la listes des adresses disponible dans le pays
     * @param adresse adresses
     */
    public void addAdresses(Adresse adresse) {
        adresse.setPays(this);
    }

    /**
     * Ajoute un film dans la listes tourné dans le pays
     */
    public void addFilm(Film film) {
        film.setPays(this);
    }

    /**
     * Methode equals permet de verifier l'egalite entre differente instance
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pays pays)) return false;
        return Objects.equals(id, pays.id) && Objects.equals(nom, pays.nom) && Objects.equals(url, pays.url);
    }

    /** Methode hashcode */
    @Override
    public int hashCode() {
        return Objects.hash(id, nom, url);
    }

    /** Methode d'affichage */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pays{");
        sb.append("id=").append(id);
        sb.append(", nom='").append(nom).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append("}\n");
        return sb.toString();
    }
}