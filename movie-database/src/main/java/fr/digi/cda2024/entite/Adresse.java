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

    /**
     * pays de l'adresse
     */
    @ManyToOne
    @JoinColumn(name="ID_PAYS")
    private Pays pays;

    /** Constructeur */
    public Adresse() {
    }

    /**
     * constructeur parametre
     * @param region
     * @param ville
     * @param pays
     */
    public Adresse(String region, String ville, Pays pays) {
        this.region = region;
        this.ville = ville;
        setPays(pays);
    }

    /**
     * Getter
     * @return l'identifiant unique de l'adresse.
     */
    public Integer getId() {
        return id;
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

    /**
     * Setter
     * @param pays pays
     */
    public void setPays(Pays pays) {
        if (pays != null) {
            pays.getAdresses().remove(this);
        }
        this.pays = pays;
        if (pays != null) {
            pays.getAdresses().add(this);
        }
    }
}