package fr.digi.cda2024.services;

import fr.digi.cda2024.dto.AdresseDTO;
import fr.digi.cda2024.dto.FilmDTO;
import fr.digi.cda2024.dto.PaysDTO;
import fr.digi.cda2024.entite.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Classe permettant de mapper les données contenues dans les classes DTO
 * vers les classes entités utilisées en base de données
 */
public class DtoEntitesMapper {
    private List<Adresse> adresses;
    private List<Film> films;
    private List<Genre> genres;
    private List<Pays> pays;
    private List<Personne> personnes;
    private List<Role> roles;

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
            // voir comment le stocker en DB, et donc comment le parser
            film.setAnneeSortie(mapAnneeSortieFilm(filmDTO.getAnneeSortie()));
            film.setUrl(filmDTO.getUrl());

            // MAP PAYS DU FILM A FAIRE --> classe Film

            mapLieuTournage(filmDTO.getLieuTournage(), film);

            // MAP ROLES
            mapRoles(filmDTO.getRoles(), film);

            // MAP CASTING PRINCIPAL --> classe Film

            // map genres A TERMINER --> classe Film
            mapGenresFilm(filmDTO.getGenres(), film);
        }

        return films;
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
        Adresse adresse = new Adresse();
        adresse.setVille(adresseDTO.getVille());
        adresse.setRegion(adresseDTO.getEtatDept());
        adresse.setPays(mapPays(adresseDTO.getPays()));

        return adresse;
    }

    /**
     * Map les informations du pays DTO vers un objet Pays entité.
     * @param paysDTO pays extrait du JSON
     */
    private Pays mapPays(PaysDTO paysDTO) {
        Pays pays = new Pays();
        pays.setNom(paysDTO.getNom());
        pays.setUrl(paysDTO.getUrl());

        return pays;
    }

    /**
     * Map les genres d'un film à partir des données extraites du JSON.
     * @param genresDTO genres extraits du JSON
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
        Genre genre = new Genre();
        genre.setNom(genreDTO);
        return genre;
    }
}
