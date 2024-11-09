package fr.digi.cda2024.ihm;

import fr.digi.cda2024.dto.FilmDTO;
import fr.digi.cda2024.services.FileTools;
import fr.digi.cda2024.services.JsonParser;

import java.io.File;
import java.io.IOException;

public class Remplissage {
    public static void main(String[] args) {
        // Récupération du chemin vers le fichier JSON auprès de l'utilisateur
        File file = FileTools.getJsonFileFromUser();

        try {
            FilmDTO[] films = JsonParser.JsonToDotParse(file);
            System.out.println("Parsing OK");
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}