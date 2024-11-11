package fr.digi.cda2024.dto;

/**
 * Objet intermédiaire récupéré après parsing du fichier JSON
 */
public class PaysDTO {
    /**
     * Nom du pays
     */
    private String nom;

    /**
     * url du pays
     */
    private String url;

    /**
     * Constructeur vide
     */
    public PaysDTO() {
    }

    /**
     * Constructeur
     * @param nom nom
     * @param url url
     */
    public PaysDTO(String nom, String url) {
        this.nom = nom;
        this.url = url;
    }

    /** Getter
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /** Setter
     * @param nom nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /** Getter
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /** Setter
     * @param url url
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
