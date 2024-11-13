package fr.digi.cda2024.services;

import fr.digi.cda2024.dto.*;
import fr.digi.cda2024.entite.*;

import java.time.LocalDate;
import java.util.*;

/**
 * Classe permettant de mapper les données contenues dans les classes DTO
 * vers les classes entités utilisées en base de données
 */
public class DtoEntitesMapper {
    /** Liste de films */
    private final Set<Film> setFilms;
    /** Liste de pays */
    private final Set<Pays> setPays;
    /** Liste de genres cinématographiques */
    private final Set<Genre> setGenres;
    /** Liste d'adresses */
    private final Set<Adresse> setAdresses;
    // acteurs et réalisateurs sont des personnes,
    // on les retrouve donc dans la même liste
    /** Liste de personnes (acteurs ou réalisateurs) */
    private final Set<Personne> setPersonnes;
    /** Liste de rôles */
    private final Set<Role> setRoles;
    /** Liste de casting principal */
    private final Set<CastingPrincipal> setCastingsPrincipaux;
    /** Liste de réalisateurs */
    private final Set<Realisateur> setRealisateurs;

    {
        this.setFilms = new HashSet<>();
        this.setPays = new HashSet<>();
        this.setGenres = new HashSet<>();
        this.setAdresses = new HashSet<>();
        this.setPersonnes = new HashSet<>();
        this.setRoles = new HashSet<>();
        this.setCastingsPrincipaux = new HashSet<>();
        this.setRealisateurs = new HashSet<>();
    }

    /**
     * Constructeur vide
     */
    public DtoEntitesMapper() {}

    /**
     * Méthode permettant de mapper les classes DTO vers les classes entités.
     * @param filmsJson objets FilmDTO extraits du JSON
     * @return liste d'objets entités Film
     */
    public Set<Film> mapDtoVersEntites(FilmDTO[] filmsJson) {
        for (FilmDTO filmDTO : filmsJson) {
            Film film = new Film();

            film.setId(trimString(filmDTO.getId()));

            boolean flag = false;
            for (Film f: this.setFilms) {
                if (f.getId().equals(film.getId())) {
                    flag = true;
                    break;
                }
            }

            if (flag) {
                continue;
            }

            film.setNom(trimString(filmDTO.getNom()));
            film.setResume(trimString(filmDTO.getResume()));
            film.setLangue(trimString(filmDTO.getLangue()));
            film.setPays(mapPays(filmDTO.getPays()));
            film.setUrl(trimString(filmDTO.getUrl()));
            film.setAnneeSortie(trimString(filmDTO.getAnneeSortie()));
            mapLieuTournage(filmDTO.getLieuTournage(), film);
            mapGenresFilm(filmDTO.getGenres(), film);
            mapRolesFilm(filmDTO.getRoles(), film);
            mapCastingPrincipalFilm(filmDTO.getCastingPrincipal(), film);
            mapRealisateursFilm(filmDTO.getRealisateurs(), film);

            this.setFilms.add(film);
        }

        return this.setFilms;
    }

    /**
     * Retourne la chaîne de caractères sans caractères espace/sauts de ligne
     * en début et fin de chaîne.
     * @param elementDTO String à traiter
     * @return String traitée
     */
    private String trimString(String elementDTO) {
        if (elementDTO == null
                || elementDTO.isEmpty()) {
            return null;
        }
        return elementDTO.trim();
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
        if (adresseDTO == null) {
            return null;
        }

        Adresse adresseExistante = getAdresseSiExiste(adresseDTO);

        if (adresseExistante != null) {
            return adresseExistante;
        }

        PaysDTO paysDTO = new PaysDTO();
        paysDTO.setNom(trimString(adresseDTO.getPays()));

        Adresse adresse = new Adresse(trimString(adresseDTO.getEtatDept()), trimString(adresseDTO.getVille()), mapPays(paysDTO));
        this.setAdresses.add(adresse);
        return adresse;
    }

    /**
     * Vérifie si une adresse présentant les mêmes informations a déjà été créée.
     * Retourne l'objet Adresse existant si c'est le cas, sinon null.
     * @param adresseDTO adresse extraite du JSON
     * @return un objet entité Adresse, ou null
     */
    private Adresse getAdresseSiExiste(AdresseDTO adresseDTO) {
        for (Adresse adresse : this.setAdresses) {
            PaysDTO paysDTO = new PaysDTO();
            paysDTO.setNom(trimString(adresseDTO.getPays()));

            if (Objects.equals(adresse.getRegion(), trimString(adresseDTO.getEtatDept()))
                    && Objects.equals(adresse.getVille(), trimString(adresseDTO.getVille()))
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
        if (paysDTO == null) {
            return null;
        }

        Pays paysExistant = getPaysSiExiste(paysDTO);

        if (paysExistant != null) {
            // Complète les informations du pays enregistré
            // si elles étaient incomplètes
            if ((paysExistant.getUrl() == null || paysExistant.getUrl().isEmpty()) && paysDTO.getUrl() != null) {
                paysExistant.setUrl(paysDTO.getUrl().trim());
            }
            return paysExistant;
        }

        Pays nouveauPays = new Pays();
        nouveauPays.setNom(trimString(paysDTO.getNom()));
        nouveauPays.setUrl(trimString(paysDTO.getUrl()));
        this.setPays.add(nouveauPays);
        return nouveauPays;
    }

    /**
     * Vérifie si un pays présentant les mêmes informations a déjà été créé.
     * Retourne l'objet Pays existant si c'est le cas, sinon null.
     * @param paysDTO pays extrait du JSON
     * @return un objet entité Pays, ou null
     */
    private Pays getPaysSiExiste(PaysDTO paysDTO) {
        for (Pays pays: this.setPays) {
            if (pays.getNom().equals(trimString(paysDTO.getNom()))) {
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
            Genre genre = mapGenre(trimString(genreDTO));
            film.addGenre(genre);
        }
    }

    /**
     * Map les informations du genre DTO vers un objet Genre entité.
     * @param genreDTO genre extrait du JSON
     * @return objet entité Genre
     */
    private Genre mapGenre(String genreDTO) {
        if (genreDTO == null
                || genreDTO.isEmpty()) {
            return null;
        }

        Genre genreExistant = getGenreSiExiste(genreDTO);

        if(genreExistant != null) {
            return genreExistant;
        }

        Genre genre = new Genre(genreDTO);
        this.setGenres.add(genre);
        return genre;
    }

    /**
     * Vérifie si un genre présentant les mêmes informations a déjà été créé.
     * Retourne l'objet Genre existant si c'est le cas, sinon null.
     * @param genreDTO genre extrait du JSON
     * @return un objet entité Genre, ou null
     */
    private Genre getGenreSiExiste(String genreDTO) {
        for (Genre genre: this.setGenres) {
            if (genreDTO.equals(genre.getNom().trim())) {
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
            mapRole(roleDTO, film);
        }
    }

    /**
     * Map les informations du rôle DTO vers un objet Role entité.
     * @param roleDTO rôle extrait du JSON
     */
    private void mapRole(RoleDTO roleDTO, Film film) {
        if (roleDTO == null) {
            return;
        }

        Role roleExistant = getRoleSiExiste(roleDTO);

        if (roleExistant == null) {
            Personne acteur = mapPersonne(roleDTO.getActeur());

            if (acteur != null) {
                Role nouveauRole = new Role(roleDTO.getPersonnage().trim(), acteur, film, trimString(roleDTO.getActeur().getUrl()));
                this.setRoles.add(nouveauRole);
            }
        }
    }

    /**
     * Vérifie si un rôle présentant les mêmes informations a déjà été créé.
     * Retourne l'objet Role existant si c'est le cas, sinon null.
     * @param roleDTO rôle extrait du JSON
     * @return un objet entité Role, ou null
     */
    private Role getRoleSiExiste(RoleDTO roleDTO) {
        for (Role role: this.setRoles) {
            if (trimString(roleDTO.getActeur().getId()).equals(role.getActeur().getId())
                    && trimString(roleDTO.getFilm()).equals(role.getFilm().getId())
                    && trimString(roleDTO.getPersonnage()).equals(role.getId().getNomRole())) {
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
        if (personneDTO == null) {
            return null;
        }

        // Ajoute un ID aux réalisateurs qui n'ont pas d'ID par défaut
        if (personneDTO.getId() == null
        && personneDTO.getUrl() != null) {
            String idReal = parseIdRealisateur(trimString(personneDTO.getUrl()));

            // Cas où l'id ne correspond pas à un id de personne
            // commençant par "nm"
            if (!idReal.startsWith("nm")) {
                return null;
            }
            else {
                personneDTO.setId(idReal);
            }
        }

        Personne personneExistante = getPersonneSiExiste(personneDTO);

        if (personneExistante != null) {
            // Complète les informations de la personne existante si
            // personneDTO en contient plus
            if (personneExistante.getDateNaissance() == null
                    && personneDTO.getNaissance() != null
                    && personneDTO.getNaissance().getDateNaissance() != null) {
                personneExistante.setDateNaissance(parseDateNaissance(trimString(personneDTO.getNaissance().getDateNaissance())));
            }
            if (personneExistante.getTaille() == 0.0
                    && personneDTO.getTaille() != 0.0) {
                personneExistante.setTaille((float) personneDTO.getTaille());
            }
            if (personneExistante.getAdresse() == null
                    && personneDTO.getNaissance() != null
                    && personneDTO.getNaissance().getLieuNaissance() != null) {
                personneExistante.setAdresse(parseLieuNaissance(trimString(personneDTO.getNaissance().getLieuNaissance())));
            }

            return personneExistante;
        }

        Personne personne;

        if (personneDTO.getNaissance() == null) {
            personne = new Personne(trimString(personneDTO.getId()),
                    trimString(personneDTO.getIdentite()),
                    null,
                    (float) personneDTO.getTaille(),
                    null);
        }
        else {
            personne = new Personne(trimString(personneDTO.getId()),
                    trimString(personneDTO.getIdentite()),
                    parseDateNaissance(trimString(personneDTO.getNaissance().getDateNaissance())),
                    (float) personneDTO.getTaille(),
                    parseLieuNaissance(trimString(personneDTO.getNaissance().getLieuNaissance())));
        }

        this.setPersonnes.add(personne);
        return personne;
    }

    /**
     * Vérifie si une personne présentant les mêmes informations a déjà été créée.
     * Retourne l'objet Personne existant si c'est le cas, sinon null.
     * @param personneDTO personne extraite du JSON
     * @return un objet entité Personne, ou null
     */
    private Personne getPersonneSiExiste(PersonneDTO personneDTO) {
        for (Personne personne: this.setPersonnes) {
            if (personne.getId().equals(trimString(personneDTO.getId()))) {
                return personne;
            }
        }
        return null;
    }

    /**
     * récupère la date de naissance d'une personne.
     * @param dateNaissance date de naissance
     * @return LocalDate
     */
    private LocalDate parseDateNaissance(String dateNaissance) {
        if (dateNaissance == null
                || dateNaissance.isEmpty()) {
            return null;
        }

        if (dateNaissance.length() < 8
                || dateNaissance.length() > 10) {
            return null;
        }

        // Si le format est "yyyy-m-dd" --> transforme en "yyyy-mm-dd"
        if(dateNaissance.length() == 9
                || dateNaissance.length() == 8) {
            String[] dates = dateNaissance.split("-");

            if(dates.length < 3) {
                return null;
            }

            // Corrige le mois : "m" --> "mm"
            if (dates[1].length() == 1) {
                dates[1] = "0" + dates[1];
            }
            // Corrige le jour : "d" --> "dd"
            if (dates[2].length() == 1) {
                dates[2] = "0" + dates[2];
            }

            dateNaissance = String.join("-", dates);

            if (dateNaissance.length() < 10) {
                return null;
            }
        }

        if (dateNaissance.substring(5,7).equals("00") || dateNaissance.substring(8,10).equals("00")) {
            return null;
        }

        return LocalDate.parse(dateNaissance);
    }

    /**
     * Récupère le lieu de naissance d'une personne et le map dans un objet
     * entité Adresse.
     * @param lieuNaissanceDTO lieu de naissance issu du JSON
     * @return un objet entité Adresse
     */
    private Adresse parseLieuNaissance(String lieuNaissanceDTO) {
        if (lieuNaissanceDTO == null
        || lieuNaissanceDTO.isEmpty()) {
            return null;
        }

        List<String> infoAdresse = Arrays.asList(lieuNaissanceDTO.split(","));

        if (infoAdresse.size() < 2) {
            return null;
        }

        // Pas de région disponible, seulement ville et pays
        if (infoAdresse.size() == 2) {
            AdresseDTO adresseDTO = new AdresseDTO(infoAdresse.get(0).trim(), null, infoAdresse.get(1).trim());
            return mapAdresse(adresseDTO);
        }

        // Traite le seul cas dans lequel on a deux infos sur le pays
        if (infoAdresse.size() > 3
                && (infoAdresse.getLast().equals("UK")
                && infoAdresse.get(infoAdresse.size() - 2).equals("England"))) {
            String pays = infoAdresse.getLast() + ", " + infoAdresse.get(infoAdresse.size() - 2);
            infoAdresse.removeLast();
            infoAdresse.set((infoAdresse.size() - 1), pays);
        }

        // On prend la première info comme ville, la dernière comme pays
        // et l'avant-dernière comme région
        if (infoAdresse.size() > 3) {
            AdresseDTO adresseDTO = new AdresseDTO(infoAdresse.getFirst().trim(), infoAdresse.get(infoAdresse.size() - 2).trim(), infoAdresse.getLast().trim());
            return mapAdresse(adresseDTO);
        }

        // Traitement classique ville-région-pays
        AdresseDTO adresseDTO = new AdresseDTO(infoAdresse.get(0).trim(), infoAdresse.get(1).trim(), infoAdresse.get(2).trim());
        return mapAdresse(adresseDTO);
    }

    /**
     * Récupère l'identifiant du réalisateur à partir de l'url issue du JSON.
     * @param urlReal url obtenue à partir du JSON
     * @return String
     */
    private String parseIdRealisateur(String urlReal) {
        String[] infosUrlReal = urlReal.split("/");
        return trimString(infosUrlReal[2]);
    }

    /**
     * Map les réalisateurs d'un film à partir des données extraites du JSON.
     * @param realDTO réalisateur extrait du JSON
     */
    private void mapRealisateursFilm(PersonneDTO[] realDTO, Film film) {
        for (PersonneDTO personneDTO: realDTO) {
            mapReal(personneDTO, film);
        }
    }

    /**
     * Map les informations liées au réalisateur dans un objet entité Realisateur.
     * @param personneDTO réalisateur extrait du JSON
     * @param film film
     */
    private void mapReal(PersonneDTO personneDTO, Film film) {
        Realisateur realExistant = getRealSiExiste(personneDTO, film);

        if (realExistant == null) {
            Personne realisateur = mapPersonne(personneDTO);

            if (realisateur != null) {
                // Relations entre les tables créées en base de données
                Realisateur real = new Realisateur(realisateur, film, trimString(personneDTO.getUrl()));
                this.setRealisateurs.add(real);
            }
        }
    }

    /**
     * Vérifie si les informations liées au réalisateur existent déjà.
     * @param personneDTO réalisateur
     * @param film film
     * @return Realisateur ou null
     */
    private Realisateur getRealSiExiste(PersonneDTO personneDTO, Film film) {
        for (Realisateur real: this.setRealisateurs) {
            if (real.getRealisateur().getId().equals(trimString(personneDTO.getId()))
            && real.getFilm().getId().equals(trimString(film.getId()))) {
                return real;
            }
        }
        return null;
    }

    /**
     * Map le casting principal d'un film à partir des données extraites du JSON.
     * @param castingPrincipal casting principal extrait du JSON
     */
    private void mapCastingPrincipalFilm(PersonneDTO[] castingPrincipal, Film film) {
        for (PersonneDTO personneDTO: castingPrincipal) {
            mapCastingPrincipal(personneDTO, film);
        }
    }

    /**
     * Map les informations liées au casting dans un objet entité CastingPrincipal.
     * @param personneDTO acteur
     * @param film film
     * @return un objet entité CastingPrincipal
     */
    private void mapCastingPrincipal(PersonneDTO personneDTO, Film film) {
        CastingPrincipal castingExistant = getCastingSiExiste(personneDTO, film);

        if (castingExistant == null) {
            Personne acteur = mapPersonne(personneDTO);

            if (acteur != null) {
                CastingPrincipal casting = new CastingPrincipal(acteur, film, trimString(personneDTO.getUrl()));
                this.setCastingsPrincipaux.add(casting);
            }
        }
    }

    /**
     * Vérifie si les informations liées au casting principal existent déjà.
     * @param personneDTO acteur
     * @param film film
     * @return CastingPrincipal ou null
     */
    private CastingPrincipal getCastingSiExiste(PersonneDTO personneDTO, Film film) {
        for (CastingPrincipal cp: this.setCastingsPrincipaux) {
            if (cp.getActeur().getId().equals(trimString(personneDTO.getId()))
                    && cp.getFilm().getId().equals(trimString(film.getId()))) {
                return cp;
            }
        }
        return null;
    }
}
