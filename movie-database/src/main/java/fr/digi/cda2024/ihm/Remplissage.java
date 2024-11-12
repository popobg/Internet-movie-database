package fr.digi.cda2024.ihm;

import fr.digi.cda2024.dto.FilmDTO;
import fr.digi.cda2024.entite.Film;
import fr.digi.cda2024.services.DtoEntitesMapper;
import fr.digi.cda2024.services.FileTools;
import fr.digi.cda2024.services.JsonParser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Remplissage {
    public static void main(String[] args) {
        // Récupération du chemin vers le fichier JSON auprès de l'utilisateur
        File file = FileTools.getJsonFileFromUser();

        FilmDTO[] filmsDTO = null;
        try {
            filmsDTO = JsonParser.JsonToDotParse(file);
            System.out.println("Parsing JSON OK");
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Set<Film> filmsEntites = new HashSet<>();

        if (filmsDTO != null) {
            DtoEntitesMapper mapper = new DtoEntitesMapper();
            filmsEntites = mapper.mapDtoVersEntites(filmsDTO);
            System.out.println("Mapping Entités OK");
        }

        // Persistance des films en base de données
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("movies");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        for (Film film: filmsEntites) {
            em.persist(film);
        }

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}