package fr.digi.cda2024.entite;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
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
    private Integer id;

    /**
     * Nom du pays.
     * Ce champ est obligatoire, unique, et sa longueur maximale est de 50 caractères.
     */
    @Column(name = "NOM", length = 50, nullable = false, unique = true)
    private String nom;

    /**
     * URL associée au pays.
     * Ce champ est obligatoire, unique, et sa longueur maximale est de 100 caractères.
     */
    @Column(name = "URL", length = 100, nullable = false, unique = true)
    private String url;

    /**
     * listes des adresses dans le pays
     */
    @OneToMany (mappedBy = "pays", cascade = CascadeType.PERSIST)
    private Set<Adresse> adresses;

    /**argument present dans tout les constructeur*/
    {
        adresses = new HashSet<>();
    }

    /** Constructeur */
    public Pays() {
    }

    public Pays(String nom, String url) {
        this.nom = nom;
        this.url = url;
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
     *
     * @return adresses
     */

    public Set<Adresse> getAdresses() {
        return adresses;
    }

}