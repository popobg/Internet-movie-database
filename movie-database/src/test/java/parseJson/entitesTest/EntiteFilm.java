package parseJson.entitesTest;

import fr.digi.cda2024.entite.Acteur;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class EntiteFilm implements Serializable {
    private String ID;
    private String nom;
    private EntiteActeur[] acteurs;

    public EntiteFilm() {
    }

    public EntiteFilm(String ID, String nom, EntiteActeur[] acteurs) {
        this.ID = ID;
        this.nom = nom;
        this.acteurs = acteurs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntiteFilm that)) return false;
        return Objects.equals(ID, that.ID) && Objects.equals(nom, that.nom) && Objects.deepEquals(acteurs, that.acteurs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, nom, Arrays.hashCode(acteurs));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EntiteTest{");
        sb.append("ID=").append(ID);
        sb.append(", nom='").append(nom).append('\'');
        sb.append(", acteurs=");

        for (EntiteActeur acteur: acteurs) {
            sb.append("\n");
            sb.append(acteur);
        }

        sb.append("\n}");
        return sb.toString();
    }

    /** Getter
     * @return ID
     */
    public String getID() {
        return ID;
    }

    /** Setter
     * @param ID ID
     */
    public void setID(String ID) {
        this.ID = ID;
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
     * @return acteurs
     */
    public EntiteActeur[] getActeurs() {
        return acteurs;
    }

    /** Setter
     * @param acteurs acteurs
     */
    public void setActeurs(EntiteActeur[] acteurs) {
        this.acteurs = acteurs;
    }
}
