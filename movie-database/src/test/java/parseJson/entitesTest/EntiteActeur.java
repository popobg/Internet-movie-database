package parseJson.entitesTest;

import java.util.Objects;

public class EntiteActeur {
    private String nom;
    private String prenom;
    private String adresse;

    public EntiteActeur() {
    }

    public EntiteActeur(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntiteActeur that)) return false;
        return Objects.equals(nom, that.nom) && Objects.equals(prenom, that.prenom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EntiteActeur{");
        sb.append("nom='").append(nom).append('\'');
        sb.append(", prenom='").append(prenom).append('\'');
        sb.append(", adresse='").append(adresse).append('\'');
        sb.append('}');
        return sb.toString();
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
     * @return prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /** Setter
     * @param prenom prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
