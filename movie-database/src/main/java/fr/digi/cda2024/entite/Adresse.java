package fr.digi.cda2024.entite;

import jakarta.persistence.*;

/**
 * Représente une adresse avec un identifiant unique, une région et une ville.
 * Cette classe est mappée sur la table "adresse" de la base de données.
 */
@Entity
@Table(name = "adresse")
public class Adresse {

    /**
     * Identifiant unique de l'adresse.
     * Ce champ est auto-généré par la base de données.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Région de l'adresse.
     * Ce champ est obligatoire et sa longueur maximale est de 50 caractères.
     */
    @Column(name = "REGION", length = 50, nullable = false)
    private String region;

    /**
     * Ville de l'adresse.
     * Ce champ est obligatoire et sa longueur maximale est de 50 caractères.
     */
    @Column(name = "VILLE", length = 50, nullable = false)
    private String ville;

    /** Constructeur */
    public Adresse() {
    }

    /**
     * Getter
     * @return l'identifiant unique de l'adresse.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter
     * @param id l'identifiant unique à définir pour l'adresse.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter
     * @return la région de l'adresse.
     */
    public String getRegion() {
        return region;
    }

    /**
     * Setter
     * @param region la région de l'adresse à définir.
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Getter
     * @return la ville de l'adresse.
     */
    public String getVille() {
        return ville;
    }

    /**
     * Setter
     * @param ville la ville de l'adresse à définir.
     */
    public void setVille(String ville) {
        this.ville = ville;
    }
}