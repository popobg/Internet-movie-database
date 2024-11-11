package fr.digi.cda2024.services;

import fr.digi.cda2024.dto.*;
import fr.digi.cda2024.entite.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant de mapper les données contenues dans les classes DTO
 * vers les classes entités utilisées en base de données
 */
public class DtoEntitesMapper {
    /** Liste de films */
    private final List<Film> listeFilms;
    /** Liste de pays */
    private final List<Pays> listePays;
    /** Liste de genres cinématographiques */
    private final List<Genre> listeGenres;
    /** Liste d'adresses */
    private final List<Adresse> listeAdresses;
    // acteurs et réalisateurs sont des personnes,
    // on les retrouve donc dans la même liste
    /** Liste de personnes (acteurs ou réalisateurs) */
    private final List<Personne> listePersonne;
    /** Liste de rôles */
    private List<Role> listeRoles;

    {
        listeFilms = new ArrayList<>();
        listePays = new ArrayList<>();
        listeGenres = new ArrayList<>();
        listeAdresses = new ArrayList<>();
        listePersonne = new ArrayList<>();
        listeRoles = new ArrayList<>();
    }

    /**
     * Constructeur vide
     */
    public DtoEntitesMapper() {}

    /**
     * Méthode permettant de mapper les classes DTO avec les classes entités.
     * @param filmsJson objets films extraits du JSON
     * @return objets entités films
     */
    public List<Film> mapDtoVersEntites(FilmDTO[] filmsJson) {
        for (FilmDTO filmDTO : filmsJson) {
            Film film = new Film();

            film.setId(filmDTO.getId());
            film.setNom(filmDTO.getNom());
            film.setResume(filmDTO.getResume());
            film.setLangue(filmDTO.getLangue());
            film.setPays(mapPays(filmDTO.getPays()));
            film.setUrl(filmDTO.getUrl());
            // voir comment stocker l'année de sortie en DB :
            // LocalDate actuel dans Film pose problème
            film.setAnneeSortie(mapAnneeSortieFilm(filmDTO.getAnneeSortie()));
            mapLieuTournage(filmDTO.getLieuTournage(), film);
            mapGenresFilm(filmDTO.getGenres(), film);
            mapRolesFilm(filmDTO.getRoles(), film);

            // MAP REALISATEURS
            mapRealisateursFilm(filmDTO.getRealisateurs(), film);

            // MAP CASTING PRINCIPAL --> classe Film
            mapCastingPrincipalFilm(filmDTO.getCastingPrincipal(), film);

            listeFilms.add(film);
        }

        return this.listeFilms;
    }

    /**
     * Map la période de sortie du film à partir des données de la classe DTO.
     * @param anneeSortieDTO période de sortie du film
     * @return String ?
     */
    // Type en base de données à repréciser
    private String mapAnneeSortieFilm(String anneeSortieDTO) {
        return anneeSortieDTO.trim();
    }

    /**
     * Map les informations des lieux de tournage du film
     * à partir des données de la classe DTO.
     * @param adresseDTO adresse extraite du JSON
     * @param film objet entité film
     */
    // Pour le moment un seul lieu de tournage enregistré dans le JSON,
    // mappé vers une liste de lieux de tournage en DB --> un film
    // peut avoir plusieurs lieux de tournage.
    private void mapLieuTournage(AdresseDTO adresseDTO, Film film) {
        Adresse adresse = mapAdresse(adresseDTO);
        film.addAdresse(adresse);
    }

    /**
     * Map les informations de l'adresse DTO vers un objet Adresse entité.
     * @param adresseDTO adresse extraite du JSON
     * @return objet entité Adresse
     */
    private Adresse mapAdresse(AdresseDTO adresseDTO) {
        Adresse adresseExistante = getAdresseSiExiste(adresseDTO);

        if (adresseExistante != null) {
            return adresseExistante;
        }

        PaysDTO paysDTO = new PaysDTO();
        paysDTO.setNom(adresseDTO.getPays().trim());

        Adresse adresse = new Adresse(adresseDTO.getEtatDept().trim(), adresseDTO.getVille().trim(), mapPays(paysDTO));
        this.listeAdresses.add(adresse);
        return adresse;
    }

    /**
     * Vérifie si une adresse présentant les mêmes informations a déjà été créée.
     * Retourne l'objet Adresse existant si c'est le cas, sinon null.
     * @param adresseDTO adresse extraite du JSON
     * @return un objet entité Adresse, ou null
     */
    private Adresse getAdresseSiExiste(AdresseDTO adresseDTO) {
        for (Adresse adresse : this.listeAdresses) {
            PaysDTO paysDTO = new PaysDTO();
            paysDTO.setNom(adresseDTO.getPays().trim());

            if (adresse.getRegion().equals(adresseDTO.getEtatDept().trim())
                    && adresse.getVille().equals(adresseDTO.getVille().trim())
                    && getPaysSiExiste(paysDTO) != null) {
                return adresse;
            }
        }
        return null;
    }

    /**
     * Map les informations du pays DTO vers un objet Pays entité.
     * @param paysDTO pays extrait du JSON
     */
    private Pays mapPays(PaysDTO paysDTO) {
        Pays paysExistant = getPaysSiExiste(paysDTO);

        if (paysExistant != null) {
            // Complète les informations du pays enregistré
            // si elles étaient incomplètes
            if ((paysExistant.getUrl() == null || paysExistant.getUrl().isEmpty())
                    && paysDTO.getUrl() != null) {
                paysExistant.setUrl(paysDTO.getUrl().trim());
            }
            return paysExistant;
        }

        Pays nouveauPays = new Pays(paysDTO.getNom().trim(), paysDTO.getUrl().trim());
        this.listePays.add(nouveauPays);
        return nouveauPays;
    }

    /**
     * Vérifie si un pays présentant les mêmes informations a déjà été créé.
     * Retourne l'objet Pays existant si c'est le cas, sinon null.
     * @param paysDTO pays extrait du JSON
     * @return un objet entité Pays, ou null
     */
    private Pays getPaysSiExiste(PaysDTO paysDTO) {
        for (Pays pays: this.listePays) {
            if (pays.getNom().equals(paysDTO.getNom().trim())) {
                return pays;
            }
        }
        return null;
    }

    /**
     * Map les genres d'un film à partir des données extraites du JSON.
     * @param genresDTO genres extraits du JSON
     * @param film entité Film
     */
    private void mapGenresFilm(String[] genresDTO, Film film) {
        for (String genreDTO: genresDTO) {
            Genre genre = mapGenre(genreDTO.trim());
            film.addGenre(genre);
        }
    }

    /**
     * Map les informations du genre DTO vers un objet Genre entité.
     * @param genreDTO genre extrait du JSON
     * @return objet entité Genre
     */
    private Genre mapGenre(String genreDTO) {
        Genre genreExistant = getGenreSiExiste(genreDTO.trim());

        if(genreExistant != null) {
            return genreExistant;
        }

        Genre genre = new Genre(genreDTO.trim());
        this.listeGenres.add(genre);
        return genre;
    }

    /**
     * Vérifie si un genre présentant les mêmes informations a déjà été créé.
     * Retourne l'objet Genre existant si c'est le cas, sinon null.
     * @param genreDTO genre extrait du JSON
     * @return un objet entité Genre, ou null
     */
    private Genre getGenreSiExiste(String genreDTO) {
        for (Genre genre: this.listeGenres) {
            if (genreDTO.equals(genre.getNom())) {
                return genre;
            }
        }
        return null;
    }

    /**
     * Map les rôles d'un film à partir des données extraites du JSON.
     * @param rolesDTO objets DTO Role extraits du JSON
     * @param film entité Film
     */
    // A TERMINER
    private void mapRolesFilm(RoleDTO[] rolesDTO, Film film) {
        for (RoleDTO roleDTO: rolesDTO) {
            Role role = mapRole(roleDTO, film);
            // Problème dans les assignations des éléments au sein des classes
            // --> à discuter avec Johan
            film.addActeur(role, role.getActeur());
        }
    }

    /**
     * Map les informations du rôle DTO vers un objet Role entité.
     * @param roleDTO rôle extrait du JSON
     * @return objet entité Role
     */
    private Role mapRole(RoleDTO roleDTO, Film film) {
        Role roleExistant = getRoleSiExiste(roleDTO);

        if (roleExistant != null) {
            return roleExistant;
        }

        return new Role(roleDTO.getPersonnage().trim(), mapPersonne(roleDTO.getActeur()), film, roleDTO.getActeur().getUrl().trim());
    }

    /**
     * Vérifie si un rôle présentant les mêmes informations a déjà été créé.
     * Retourne l'objet Role existant si c'est le cas, sinon null.
     * @param roleDTO rôle extrait du JSON
     * @return un objet entité Role, ou null
     */
    private Role getRoleSiExiste(RoleDTO roleDTO) {
        for (Role role: this.listeRoles) {
            if (roleDTO.getActeur().getId().trim().equals(role.getActeur().getId())
                    && roleDTO.getFilm().trim().equals(role.getFilm().getId())
                    && roleDTO.getPersonnage().trim().equals(role.getId().getNomRole())) {
                return role;
            }
        }
        return null;
    }

    /**
     * Map les informations d'une personne DTO vers un objet entité Personne
     * @param personneDTO personne extraite du JSON
     * @return un objet entité Personne
     */
    private Personne mapPersonne(PersonneDTO personneDTO) {
        Personne personneExistante = getPersonneSiExiste(personneDTO);

        if (personneExistante != null) {
            // Complète les informations de la personne existante si
            // personneDTO en contient plus
            if (personneExistante.getDateNaissance() == null
                    && personneDTO.getNaissance().getDateNaissance() != null) {
                personneExistante.setDateNaissance(LocalDate.parse(personneDTO.getNaissance().getDateNaissance().trim()));
            }
            if (personneExistante.getTaille() == 0.0
                    && personneDTO.getTaille() != 0.0) {
                personneExistante.setTaille((float) personneDTO.getTaille());
            }
            if (personneExistante.getAdresse() == null
                    && personneDTO.getNaissance().getLieuNaissance() != null) {
                personneExistante.setAdresse(parseLieuNaissance(personneDTO.getNaissance().getLieuNaissance().trim()));
            }

            return personneExistante;
        }

        Personne personne = new Personne(personneDTO.getIdentite().trim(), LocalDate.parse(personneDTO.getNaissance().getDateNaissance().trim()), (float) personneDTO.getTaille(), parseLieuNaissance(personneDTO.getNaissance().getLieuNaissance().trim()));

        // Données JSON différentes entre un acteur et un réalisateur
        if (personneDTO.getId() != null) {
            personne.setId(personneDTO.getId().trim());
        }
        else {
            personne.setId(parseIdRealisateur(personneDTO.getUrl().trim()));
        }

        return personne;
    }

    /**
     * Vérifie si une personne présentant les mêmes informations a déjà été créée.
     * Retourne l'objet Personne existant si c'est le cas, sinon null.
     * @param personneDTO personne extraite du JSON
     * @return un objet entité Personne, ou null
     */
    private Personne getPersonneSiExiste(PersonneDTO personneDTO) {
        for (Personne personne: this.listePersonne) {
            if (personne.getId().equals(personneDTO.getId().trim())) {
                return personne;
            }
        }
        return null;
    }

    /**
     * Récupère le lieu de naissance d'une personne et le map dans un objet
     * entité Adresse.
     * @param lieuNaissanceDTO lieu de naissance issu du JSON
     * @return un objet entité Adresse
     */
    private Adresse parseLieuNaissance(String lieuNaissanceDTO) {
        String[] infoAdresse = lieuNaissanceDTO.split(",");

        AdresseDTO adresseDTO = new AdresseDTO(infoAdresse[0].trim(), infoAdresse[1].trim(), infoAdresse[2].trim());

        return mapAdresse(adresseDTO);
    }

    /**
     * Récupère l'identifiant du réalisateur à partir de l'url issue du JSON.
     * @param urlReal url obtenue à partir du JSON
     * @return String
     */
    private String parseIdRealisateur(String urlReal) {
        String[] infosUrlReal = urlReal.split("/");
        return infosUrlReal[1];
    }

    /**
     * Map les réalisateurs d'un film à partir des données extraites du JSON.
     * @param realDTO réalisateur extrait du JSON
     */
    private void mapRealisateursFilm(PersonneDTO[] realDTO, Film film) {
        for (PersonneDTO personneDTO: realDTO) {
//            Realisateur real = mapReal(personneDTO, film);
            // Méthode non existante dans Film --> à ajouter ;
            // le lier à la liste de films réalisés dans Personne
//            film.addRealisateur(realDTO);
        }
    }

    /**
     * Map les informations liées au réalisateur dans un objet entité Realisateur.
     * @param personneDTO réalisateur extrait du JSON
     * @param film film
     */
    private void mapReal(PersonneDTO personneDTO, Film film) {
//        return new Realisateur(mapPersonne(personneDTO), film, personneDTO.getUrl());
    }

    /**
     * Map le casting principal d'un film à partir des données extraites du JSON.
     * @param castingPrincipal casting principal extrait du JSON
     */
    private void mapCastingPrincipalFilm(PersonneDTO[] castingPrincipal, Film film) {
        for (PersonneDTO personneDTO: castingPrincipal) {
            CastingPrincipal cp = mapCastingPrincipal(personneDTO, film);
            film.addActeurCastingPrincipal(cp, cp.getActeur());
        }
    }

    /**
     * Map les informations liées au casting dans un objet entité CastingPrincipal.
     * @param personneDTO acteur
     * @param film film
     * @return un objet entité CastingPrincipal
     */
    private CastingPrincipal mapCastingPrincipal(PersonneDTO personneDTO, Film film) {
        return new CastingPrincipal(mapPersonne(personneDTO), film, personneDTO.getUrl());
    }
}
