package fr.digi.cda2024.entite;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Représente une personne avec un identifiant unique, une identité, une date de naissance et une taille.
 * Cette classe est mappée sur la table "personne" de la base de données.
 */
@Entity
@Table(name = "personne")
public class Personne implements Serializable {

    /** Identifiant unique de la personne. */
    @Id
    @Column(name ="ID")
    private String id;

    /**
     * Nom complet ou identité de la personne.
     */
    @Column(name = "IDENTITE")
    private String identite;

    /** Date de naissance de la personne. */
    @Column(name = "DATE_NAISSANCE")
    private LocalDate dateNaissance;

    /** Taille de la personne en mètres. */
    @Column(name = "TAILLE")
    private float taille;

    /** Adresse de la personne */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="ID_ADRESSE")
    private Adresse adresse;

    /** Liste des rôles que la personne a incarné */
    @OneToMany(mappedBy = "acteur")
    private Set<Role> roles;

    /** Liste des films ou la personne est en casting principal */
    @OneToMany(mappedBy = "acteur")
    private Set<CastingPrincipal> castingsPrincipaux;

    /** Liste des films ou la personne est réalisateur */
    @OneToMany(mappedBy = "realisateur")
    private Set<Realisateur> realisateurs;

    /** Attributs present dans tous les constructeurs */
    {
        roles = new HashSet<>();
        castingsPrincipaux = new HashSet<>();
        realisateurs = new HashSet<>();
    }

    /** Constructeur vide*/
    public Personne() {
    }

    /**
     * Constructeur paramètres
     * @param id id
     * @param identite identité
     * @param dateNaissance date de naissance
     * @param taille taille
     * @param adresse adresse
     */
    public Personne(String id, String identite, LocalDate dateNaissance, float taille, Adresse adresse) {
        this.id = id;
        this.identite = identite;
        this.dateNaissance = dateNaissance;
        this.taille = taille;
        setAdresse(adresse);
    }

    /**
     * Getter
     * @return l'identifiant unique de la personne.
     */
    public String getId() {
        return id;
    }

    /**
     * Setter
     * @param id l'identifiant unique à définir pour la personne.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter
     * @return l'identité de la personne.
     */
    public String getIdentite() {
        return identite;
    }

    /**
     * Setter
     * @param identite l'identité de la personne à définir.
     */
    public void setIdentite(String identite) {
        this.identite = identite;
    }

    /**
     * Getter
     * @return la date de naissance de la personne.
     */
    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Setter
     * @param dateNaissance la date de naissance à définir pour la personne.
     */
    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Getter
     * @return la taille de la personne en mètres.
     */
    public float getTaille() {
        return taille;
    }

    /**
     * Setter
     * @param taille la taille à définir pour la personne en mètres.
     */
    public void setTaille(float taille) {
        this.taille = taille;
    }

    /**
     * Getter
     * @return adresse
     */
    public Adresse getAdresse() {
        return adresse;
    }

    /**
     * Getter
     * @return roles
     */
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * Getter
     * @return roles
     */
    public Set<CastingPrincipal> getCastingPrincipal() {
        return castingsPrincipaux;
    }

    /**
     * Getter
     * @return roles
     */
    public Set<Realisateur> getRealisateur() {
        return realisateurs;
    }

    /**
     * Setter
     * @param adresse adresse
     */
    public void setAdresse(Adresse adresse) {
        if (adresse != null) {
            adresse.getPersonnes().remove(this);
        }
        this.adresse = adresse;
        if (adresse != null) {
            adresse.getPersonnes().add(this);
        }
    }

    /**
     * Ajoute un rôle et un film à une personne.
     * @param role rôle
     * @param film film
     */
    public void addRole(Role role,Film film) {
        role.setActeur(this);
        role.setFilm(film);
    }

    /**
     * Ajoute un rôle et un film à une personne.
     * @param castingPrincipal casting principal
     * @param film film
     */
    public void addCastingPrincipal(CastingPrincipal castingPrincipal,Film film) {
        castingPrincipal.setActeur(this);
        castingPrincipal.setFilm(film);
    }

    /**
     * Ajoute un film réalisé à une personne.
     * @param realisateur réalisateur
     * @param film film
     */
    public void addRealisateur(Realisateur realisateur,Film film) {
        realisateur.setRealisateur(this);
        realisateur.setFilm(film);
    }

    /** Methode equals permet de verifier l'egalite entre differente instance */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Personne personne)) return false;
        return Float.compare(taille, personne.taille) == 0 && Objects.equals(id, personne.id) && Objects.equals(identite, personne.identite) && Objects.equals(dateNaissance, personne.dateNaissance) && Objects.equals(adresse, personne.adresse);
    }

    /** Methode hashcode */
    @Override
    public int hashCode() {
        return Objects.hash(id, identite, dateNaissance, taille, adresse);
    }


    /** Methode d'affichage */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Personne{");
        sb.append("adresse=").append(adresse);
        sb.append(", taille=").append(taille);
        sb.append(", dateNaissance=").append(dateNaissance);
        sb.append(", identite='").append(identite).append('\'');
        sb.append(", id='").append(id).append('\'');
        sb.append("}\n");
        return sb.toString();
    }
}
