package fr.digi.cda2024.requetage;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Classe d'appel des méthodes de query
 */
public class QuerysMenu {

    /**
     * Ouverture du EntityManagerfactory
     */
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-tp-group");

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
        List<String> filmographie = new ArrayList<>();

        try {
            TypedQuery<String> query = em.createQuery(
                    "SELECT f.nom " +
                            "FROM Film f " +
                            "JOIN Role r ON r.film = f " +
                            "JOIN Personne p ON r.acteur = p " +
                            "WHERE p.identite = :nomActeur", String.class);
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
        //ici on fait un Set<> pour ne pas qu'il y est deux fois le nom d'un même acteur qui aurait 2 rôle dans un film
        Set<String> casting = new HashSet<>();

        try {
            TypedQuery<String> query = em.createQuery(
                    "SELECT p.identite " +
                            "FROM Personne p " +
                            "JOIN Role r ON r.acteur = p " +
                            "JOIN Film f ON f = r.film " +
                            "WHERE f.nom = :nomFilm", String.class);
            query.setParameter("nomFilm", filmNom);

            List<String> resultList = query.getResultList();
            casting.addAll(resultList);

        } finally {
            em.close();
        }
        return new ArrayList<>((casting));
    }

    /**
     * Méthode pour récupérer les films sortis entre deux années données
     */
    public static List<String> getFilmsEntreDeuxAnnees(int anneeDebut, int anneeFin) {
        LocalDate debutDate = LocalDate.of(anneeDebut, 1, 1);
        LocalDate finDate = LocalDate.of(anneeFin, 12, 31);

        EntityManager em = emf.createEntityManager();
        List<String> filmsEntre = new ArrayList<>();

        try {
            TypedQuery<String> query = em.createQuery(
                    "SELECT f.nom " +
                            "FROM Film f " +
                            "WHERE f.anneeSortie BETWEEN :anneeDebut AND :anneeFin", String.class);
            query.setParameter("anneeDebut", debutDate);
            query.setParameter("anneeFin", finDate);
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
        List<String> filmsCommuns = new ArrayList<>();

        try {
            TypedQuery<String> query = em.createQuery(
                    "SELECT f.nom " +
                            "FROM Film f " +
                            "JOIN Role r1 ON r1.film = f " +
                            "JOIN Personne p1 ON p1 = r1.acteur " +
                            "JOIN Role r2 ON r2.film = f " +
                            "JOIN Personne p2 ON p2 = r2.acteur " +
                            "WHERE p1.identite = :acteur1 AND p2.identite = :acteur2", String.class);
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
        List<String> acteursCommuns = new ArrayList<>();

        try {
            TypedQuery<String> query = em.createQuery(
                    "SELECT p.identite " +
                            "FROM Personne p " +
                            "JOIN Role r1 ON r1.acteur = p " +
                            "JOIN Film f1 ON f1 = r1.film " +
                            "JOIN Role r2 ON r2.acteur = p " +
                            "JOIN Film f2 ON f2 = r2.film " +
                            "WHERE f1.nom = :film1 AND f2.nom = :film2", String.class);
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
        LocalDate debutDate = LocalDate.of(anneeDebut, 1, 1);
        LocalDate finDate = LocalDate.of(anneeFin, 12, 31);

        EntityManager em = emf.createEntityManager();
        List<String> filmsEntreAvec = new ArrayList<>();

        try {
            TypedQuery<String> query = em.createQuery(
                    "SELECT f.nom " +
                            "FROM Film f " +
                            "JOIN Role r ON r.film = f " +
                            "JOIN Personne p ON r.acteur = p " +
                            "WHERE f.anneeSortie BETWEEN :anneeDebut AND :anneeFin " +
                            "AND p.identite = :acteurNom", String.class);
            query.setParameter("anneeDebut", debutDate);
            query.setParameter("anneeFin", finDate);
            query.setParameter("acteurNom", acteurNom);
            filmsEntreAvec = query.getResultList();
        } finally {
            em.close();
        }
        return filmsEntreAvec;
    }
}
