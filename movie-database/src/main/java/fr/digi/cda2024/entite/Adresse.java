package fr.digi.cda2024.entite;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Représente une adresse avec un identifiant unique, une région et une ville.
 * Cette classe est mappée sur la table "adresse" de la base de données.
 */
@Entity
@Table(name = "adresse")
public class Adresse implements Serializable {

    /**
     * Identifiant unique de l'adresse.
     * Ce champ est auto-généré par la base de données.
     */
    @Id
    @Column(name ="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Région de l'adresse.
     */
    @Column(name = "REGION")
    private String region;

    /**
     * Ville de l'adresse.
     */
    @Column(name = "VILLE")
    private String ville;

    /** Pays de l'adresse */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="ID_PAYS")
    private Pays pays;

    /** Liste des personnes nées a l'adresse */
    @OneToMany(mappedBy = "adresse",cascade = CascadeType.PERSIST)
    private Set<Personne> personnes;

    /** Listes des films tournant sur ce lieu */
    @ManyToMany
    @JoinTable(name = "filme_dans",
        joinColumns = @JoinColumn(name = "ID_FILM",referencedColumnName="ID"),
        inverseJoinColumns = @JoinColumn (name = "ID_ADRESSE",referencedColumnName = "ID")
    )
    private Set<Film> films;

    // arguments présents dans tous les constructeurs
    {
        personnes = new HashSet<>();
        films = new HashSet<>();
    }

    /** Constructeur vide*/
    public Adresse() {
    }

    /**
     * Constructeur parametre
     * @param region région
     * @param ville ville
     * @param pays pays
     */
    public Adresse(String region, String ville, Pays pays) {
        this.region = region;
        this.ville = ville;
        this.setPays(pays);
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
     * Getter
     * @return personnes
     */
    public Set<Personne> getPersonnes() {
        return personnes;
    }

    /**
     * Getter
     * @return films
     */
    public Set<Film> getFilms() {
        return films;
    }

    /**
     * Getter
     * @return pays
     */
    public Pays getPays() {
        return pays;
    }

    /**
     * Setter
     * @param pays pays
     */
    public void setPays(Pays pays) {
        if (this.pays != null) {
            this.pays.getAdresses().remove(this);
        }
        this.pays = pays;
        if (this.pays != null) {
            this.pays.getAdresses().add(this);
        }
    }

    /**
     * Ajoute un film dans la liste des films tourne a cette adresse
     * @param film film
     */
    public void addFilm(Film film) {
        film.addAdresse(this);
    }

    /**
     * Supprime un film dans la liste des films tourne a cette adresse
     * @param film film
     */
    public void removeFilm(Film film) {
        film.removeAdresse(this);
    }

    /** Methode equals permet de verifier l'egalite entre differente instance */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Adresse adresse)) return false;
        return Objects.equals(id, adresse.id) && Objects.equals(region, adresse.region) && Objects.equals(ville, adresse.ville) && Objects.equals(pays, adresse.pays);
    }

    /** Methode hashcode */
    @Override
    public int hashCode() {
        return Objects.hash(id, region, ville, pays);
    }

    /** Methode d'affichage */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Adresse{");
        sb.append("id=").append(id);
        sb.append(", region='").append(region).append('\'');
        sb.append(", ville='").append(ville).append('\'');
        sb.append(", pays=").append(pays);
        sb.append("}\n");
        return sb.toString();
    }
}