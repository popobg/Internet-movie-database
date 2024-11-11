package fr.digi.cda2024.entite;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
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
    private String id;

    /**
     * Nom complet ou identité de la personne.
     * Ce champ est obligatoire et sa longueur maximale est de 50 caractères.
     */
    @Column(name = "IDENTITE", length = 50, nullable = false)
    private String identite;

    /** Date de naissance de la personne. */
    @Column(name = "DATE_NAISSANCE")
    private LocalDate dateNaissance;

    /** Taille de la personne en mètres. */
    @Column(name = "TAILLE")
    private float taille;

    /** Adresse de la personne */
    @ManyToOne
    @JoinColumn(name="ID_ADRESSE")
    private Adresse adresse;

    /**
     * liste des rôles que la personne a incarné
     */
    @OneToMany (mappedBy = "acteur")
    private Set<Role> roles;

    /** Films lie au realisateur */
    @ManyToMany
    @JoinTable(
            name = "realise",
            joinColumns = @JoinColumn(name = "ID_PERSONNE", referencedColumnName="ID"),
            inverseJoinColumns = @JoinColumn(name = "ID_FILM", referencedColumnName="ID")
    )
    private Set<Film> filmsRealise;

    @ManyToMany
    @JoinTable(
            name = "casting_principale",
            joinColumns = @JoinColumn(name = "ID_PERSONNE"),
            inverseJoinColumns = @JoinColumn(name = "ID_FILM")
    )
    private Set<Film> filmsJoue;

    @OneToMany(mappedBy = "personne", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CastingPrincipale> castingsPrincipaux = new HashSet<>();

    {
        roles = new HashSet<>();
        filmsRealise = new HashSet<>();
    }

    /** Constructeur vide*/
    public Personne() {
    }

    /** Constructeur paremetre */
    public Personne(String identite, LocalDate dateNaissance, float taille, Adresse adresse) {
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
     *
     * @return adresse
     */
    public Adresse getAdresse() {
        return adresse;
    }

    /**
     * Getter
     *
     * @return roles
     */

    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * Setter
     *
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
     * Ajoute un role et un film a une personne
     * @param role rôle
     * @param film film
     */
    public void addRole(Role role,Film film) {
        role.setActeur(this);
        role.setFilm(film);
    }
}
