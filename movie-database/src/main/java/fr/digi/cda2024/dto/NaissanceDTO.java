package fr.digi.cda2024.dto;

import java.io.Serializable;

/**
 * Objet Naissance intermédiaire récupéré après parsing du fichier JSON
 */
public class NaissanceDTO implements Serializable {
    /**
     * Date de naissance d'une personne
     */
    private String dateNaissance;

    /**
     * Lieu de naissance d'une personne
     */
    private String lieuNaissance;

    /** Getter
     * @return dateNaissance
     */
    public String getDateNaissance() {
        return dateNaissance;
    }

    /** Setter
     * @param dateNaissance dateNaissance
     */
    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /** Getter
     * @return lieuNaissance
     */
    public String getLieuNaissance() {
        return lieuNaissance;
    }

    /** Setter
     * @param lieuNaissance lieuNaissance
     */
    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }
}
