package fr.digi.cda2024.requetage;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 * Classe d'appel des méthodes de query
 */
public class querysMenu {

    /**
     * Ouverture du EntityManagerfactory
     */
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("movie-database");

    /**
     * Methode de fermeture du EntityManagerfactory à appeler après utilisation des méthodes ci-dessous
     */
    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    /**
     * Méthode pour récupérer la filmographie d'un acteur
     */
    public static List<String> getFilmographieActeur(String acteurNom) {
        EntityManager em = emf.createEntityManager();
        List<String> filmographie = null;

        try {
            TypedQuery<String> query = em.createQuery(
                    "SELECT f.NOM FROM film f " +
                            "JOIN role r ON r.ID_FILM = f.ID_FILM " +
                            "JOIN personne p ON p.ID_PERSONNE = r.ID_PERSONNE " +
                            "WHERE p.IDENTITE = :nomActeur", String.class);
            query.setParameter("nomActeur", acteurNom);
            filmographie = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return filmographie;

    }

    /**
     * Méthode pour récupérer le casting d’un film donné
     */
    public static List<String> getCastingFilm(String filmNom) {
        EntityManager em = emf.createEntityManager();
        List<String> casting = null;

        try {
            TypedQuery<String> query = em.createQuery(
                    "SELECT p.IDENTITE FROM PERSONNE p " +
                            "JOIN role r ON r.ID_PERSONNE = p.ID_PERSONNE " +
                            "JOIN film f ON f.ID_FILM = r.ID_FILM " +
                            "WHERE f.NOM = :nomFilm", String.class);
            query.setParameter("nomFilm", filmNom);
            casting = query.getResultList();
        } finally {
            em.close();
        }
        return casting;
    }

    /**
     * Méthode pour récupérer les films sortis entre deux années données
     */
    public static List<String> getFilmsEntreDeuxAnnees(int anneeDebut, int anneeFin) {
        EntityManager em = emf.createEntityManager();
        List<String> filmsEntre = null;

        try {
            TypedQuery<String> query = em.createQuery(
                    "SELECT f.NOM FROM film f " +
                            "WHERE f.ANNEE_SORTIE BETWEEN :anneeDebut AND :anneeFin", String.class);
            query.setParameter("anneeDebut", anneeDebut);
            query.setParameter("anneeFin", anneeFin);
            filmsEntre = query.getResultList();
        } finally {
            em.close();
        }
        return filmsEntre;
    }

    /**
     * Méthode pour récupérer les films communs à deux acteurs donnés
     */
    public static List<String> getFilmsCommunsPourDeuxActeurs(String acteurNom1, String acteurNom2) {
        EntityManager em = emf.createEntityManager();
        List<String> filmsCommuns = null;

        try {
            TypedQuery<String> query = em.createQuery(
                    "SELECT f.nom FROM Film f " +
                            "JOIN role r1 ON r1.ID_FILM = f.ID_FILM " +
                            "JOIN personne p1 ON p1.ID_PERSONNE = r1.ID_PERSONNE " +
                            "JOIN role r2 ON r2.ID_FILM = f.ID_FILM " +
                            "JOIN personne p2 ON p2.ID_PERSONNE = r2.ID_PERSONNE " +
                            "WHERE p1.IDENTITE = :acteur1 AND p2.IDENTITE = :acteur2", String.class);
            query.setParameter("acteur1", acteurNom1);
            query.setParameter("acteur2", acteurNom2);
            filmsCommuns = query.getResultList();
        } finally {
            em.close();
        }
        return filmsCommuns;
    }

    /**
     * Méthode pour récupérer les acteurs communs à deux films donnés
     */
    public static List<String> getActeursCommunsPourDeuxFilms(String film1, String film2) {
        EntityManager em = emf.createEntityManager();
        List<String> acteursCommuns = null;

        try {
            TypedQuery<String> query = em.createQuery(
                    "SELECT p.IDENTITE FROM personne p " +
                            "JOIN role r1 ON r1.ID_PERSONNE = p.ID_PERSONNE " +
                            "JOIN film f1 ON f1.ID_FILM = r1.ID_FILM " +
                            "JOIN role r2 ON r2.ID_PERSONNE = p.ID_PERSONNE " +
                            "JOIN film f2 ON f2.f1.ID_FILM = r2.f1.ID_FILM " +
                            "WHERE f1.NOM = :film1 AND f2.NOM = :film2", String.class);
            query.setParameter("film1", film1);
            query.setParameter("film2", film2);
            acteursCommuns = query.getResultList();
        } finally {
            em.close();
        }
        return acteursCommuns;
    }

    /**
     * Méthode pour récupérer les films sortis entre deux années et ayant un acteur donné
     */
    public static List<String> getFilmsEntreDeuxAnneesAvecActeur(int anneeDebut, int anneeFin, String acteurNom) {
        EntityManager em = emf.createEntityManager();
        List<String> filmsEntreAvec = null;

        try {
            TypedQuery<String> query = em.createQuery(
                    "SELECT f.NOM FROM film f " +
                            "JOIN role r ON r.ID_FILM = f.ID_FILM " +
                            "JOIN personne p ON p.ID_PERSONNE = r.ID_PERSONNE " +
                            "WHERE f.ANNEE_SORTIE BETWEEN :anneeDebut AND :anneeFin " +
                            "AND p.IDENTITE = :acteurNom", String.class);
            query.setParameter("anneeDebut", anneeDebut);
            query.setParameter("anneeFin", anneeFin);
            query.setParameter("acteurNom", acteurNom);
            filmsEntreAvec = query.getResultList();
        } finally {
            em.close();
        }
        return filmsEntreAvec;
    }
}
