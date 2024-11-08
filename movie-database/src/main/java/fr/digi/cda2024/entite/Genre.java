package fr.digi.cda2024.entite;

import jakarta.persistence.*;

import java.io.Serializable;

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

    /** Constructeur */
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


}
