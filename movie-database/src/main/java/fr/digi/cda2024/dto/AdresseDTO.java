package fr.digi.cda2024.dto;

import fr.digi.cda2024.entite.Pays;

import java.io.Serializable;

/**
 * Objet Adresse intermédiaire obtenu après parsing du fichier JSON
 */
public class AdresseDTO implements Serializable {
    /**
     * ville extraite du document
     */
    private String ville;

    /**
     * etatDept extrait du document
     */
    private String etatDept;

    /**
     * pays extrait du document
     */
    private PaysDTO pays;

    /**
     * Constructeur vide
     */
    public AdresseDTO() {
    }

    /**
     * Constructeur
     * @param ville ville
     * @param etatDept département, état, région
     * @param pays pays
     */
    public AdresseDTO(String ville, String etatDept, PaysDTO pays) {
        this.ville = ville;
        this.etatDept = etatDept;
        this.pays = pays;
    }

    /** Getter
     * @return ville
     */
    public String getVille() {
        return ville;
    }

    /** Setter
     * @param ville ville
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /** Getter
     * @return etatDept
     */
    public String getEtatDept() {
        return etatDept;
    }

    /** Setter
     * @param etatDept etatDept
     */
    public void setEtatDept(String etatDept) {
        this.etatDept = etatDept;
    }

    /** Getter
     * @return pays
     */
    public PaysDTO getPays() {
        return pays;
    }

    /** Setter
     * @param pays pays
     */
    public void setPays(PaysDTO pays) {
        this.pays = pays;
    }
}
