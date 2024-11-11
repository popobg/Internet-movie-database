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
    private final List<Film> listeFilms;
    private List<Pays> listePays;
    private List<Genre> listeGenres;
    private List<Adresse> listeAdresses;
    private List<Personne> listePersonne;
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
            // voir comment le stocker en DB, et donc comment le parser :
            // LocalDate actuel dans Film pose problème
            film.setAnneeSortie(mapAnneeSortieFilm(filmDTO.getAnneeSortie()));
            film.setUrl(filmDTO.getUrl());

            // Une fois l'attribut ajouté dans la classe Film
            film.setPays(mapPays(filmDTO.getPays()));

            mapLieuTournage(filmDTO.getLieuTournage(), film);

            // MAP ROLES --> A TERMINER
            mapRolesFilm(filmDTO.getRoles(), film);

            // MAP CASTING PRINCIPAL --> classe Film

            // map genres A TERMINER --> classe Film
            mapGenresFilm(filmDTO.getGenres(), film);

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
        return anneeSortieDTO;
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

        Adresse adresse = new Adresse(adresseDTO.getEtatDept(), adresseDTO.getVille(), mapPays(adresseDTO.getPays()));
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
            if (adresse.getRegion().equals(adresseDTO.getEtatDept())
                    && adresse.getVille().equals(adresseDTO.getVille())
                    && getPaysSiExiste(adresseDTO.getPays()) != null) {
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
                paysExistant.setUrl(paysDTO.getUrl());
            }
            return paysExistant;
        }

        Pays nouveauPays = new Pays(paysDTO.getNom(), paysDTO.getUrl());
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
            if (pays.getNom().equals(paysDTO.getNom())) {
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
    // A TERMINER
    private void mapGenresFilm(String[] genresDTO, Film film) {
        for (String genreDTO: genresDTO) {
            Genre genre = mapGenre(genreDTO);
            // film.addGenre(genre);
        }
    }

    /**
     * Map les informations du genre DTO vers un objet Genre entité.
     * @param genreDTO genre extrait du JSON
     * @return objet entité Genre
     */
    private Genre mapGenre(String genreDTO) {
        Genre genreExistant = getGenreSiExiste(genreDTO);

        if(genreExistant != null) {
            return genreExistant;
        }

        Genre genre = new Genre(genreDTO);
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
            Role role = new Role(roleDTO.getFilm(), mapPersonne(roleDTO.getActeur()), roleDTO.getPersonnage(), roleDTO.getActeur().getUrl());

            // Problème dans les assignations des éléments au sein des classes
            // --> à discuter avec Johan
            film.addActeur(role);
        }
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
                personneExistante.setDateNaissance(LocalDate.parse(personneDTO.getNaissance().getDateNaissance()));
            }
            if (personneExistante.getTaille() == 0.0
                    && personneDTO.getTaille() != 0.0) {
                personneExistante.setTaille((float) personneDTO.getTaille());
            }
            if (personneExistante.getAdresse() == null
                    && personneDTO.getNaissance().getLieuNaissance() != null) {
                personneExistante.setAdresse(parseLieuNaissance(personneDTO.getNaissance().getLieuNaissance()));
            }

            return personneExistante;
        }

        Personne personne = new Personne(personneDTO.getIdentite(), LocalDate.parse(personneDTO.getNaissance().getDateNaissance()), (float) personneDTO.getTaille(), parseLieuNaissance(personneDTO.getNaissance().getLieuNaissance()));

        // Données JSON différentes entre un acteur et un réalisateur
        if (personneDTO.getId() != null) {
            personne.setId(personneDTO.getId());
        }
        else {
            personne.setId(parseIdRealisateur(personneDTO.getUrl()));
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
            if (personne.getId().equals(personneDTO.getId())) {
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

        AdresseDTO adresseDTO = new AdresseDTO(infoAdresse[0].trim(), infoAdresse[1].trim(), new PaysDTO(infoAdresse[2].trim(), null));

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
}
