package fr.digi.cda2024.entite;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

/**
 * Sert a creer la cle primaire des classes CastingPrincipal et Realisateur
 */
@Embeddable
public class ClePersonneFilmDeuxFacteurs implements Serializable {

    /** Cle permettant la liaison entre la table donne et personne */
    private String facteur1;

    /** Cle permettant la liaison entre la table donne et film */
    private String facteur2;

    /** Constructeur vide */
    public ClePersonneFilmDeuxFacteurs() {
    }

    /**
     * Constructeur parametre
     * @param facteur1 facteur 1
     * @param facteur2 facteur 2
     */
    public ClePersonneFilmDeuxFacteurs(String facteur1, String facteur2) {
        this.facteur1 = facteur1;
        this.facteur2 = facteur2;
    }

    /**
     * Getter
     * @return facteur 1
     */
    public String getFacteur1() {
        return facteur1;
    }

    /**
     * Setter
     * @param facteur1 facteur 1
     */
    public void setFacteur1(String facteur1) {
        this.facteur1 = facteur1;
    }

    /**
     * Getter
     * @return facteur deux
     */
    public String getFacteur2() {
        return facteur2;
    }

    /**
     * Setter
     * @param facteur2 facteur deux
     */
    public void setFacteur2(String facteur2) {
        this.facteur2 = facteur2;
    }
}