package fr.digi.cda2024.dto;

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
    private String pays;

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
    public String getPays() {
        return pays;
    }

    /** Setter
     * @param pays pays
     */
    public void setPays(String pays) {
        this.pays = pays;
    }
}
