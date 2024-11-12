package fr.digi.cda2024.ihm;

import fr.digi.cda2024.dto.FilmDTO;
import fr.digi.cda2024.entite.Film;
import fr.digi.cda2024.services.DtoEntitesMapper;
import fr.digi.cda2024.services.FileTools;
import fr.digi.cda2024.services.JsonParser;

import java.io.File;
import java.io.IOException;
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

        Set<Film> filmsEntites;

        if (filmsDTO != null) {
            DtoEntitesMapper mapper = new DtoEntitesMapper();
            filmsEntites = mapper.mapDtoVersEntites(filmsDTO);
            System.out.println("Mapping Entités OK");
        }

        // Persistance des films en base de données
    }
}