package fr.digi.cda2024.entite;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

/** Sert a creer la cle primaire de la classe CastingPrincipal */
@Embeddable
public class CleDeuxFacteurs implements Serializable{

    /** Cle permettant la liaison entre casting_principal et personne */
    private String facteur1;

    /** Cle permettant la liaison entre casting_principal et film */
    private String facteur2;

    /** Constructeur vide */
    public CleDeuxFacteurs() {
    }

    /**
     * Constructeur parametre
     * @param facteurUn
     * @param facteurDeux
     */
    public CleDeuxFacteurs(String facteurUn, String facteurDeux) {
        this.facteur1 = facteurUn;
        this.facteur2 = facteurDeux;
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
     * @param facteurUn facteur un
     */
    public void setFacteur1(String facteurUn) {
        this.facteur1 = facteurUn;
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
     * @param facteurDeux facteur deux
     */
    public void setFacteur2(String facteurDeux) {
        this.facteur2 = facteurDeux;
    }
}