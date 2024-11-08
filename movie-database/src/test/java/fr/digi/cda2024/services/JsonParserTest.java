package fr.digi.cda2024.services;

import fr.digi.cda2024.dto.FilmDTO;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonParserTest {
    // Création de la collection d'objets attendue après parsing des données de test


    @Test
    void jsonToDOTParse() {
        try {
            FilmDTO[] films = JsonParser.JsonToDOTParse(new File("D:\\dev\\CDA JAVA\\15 - Projet JPA\\Internet-movie-database\\movie-database\\src\\test\\resources\\donneesTest.json"));

            System.out.println("Tout est ok !");
        }
        catch (IOException e) {
            fail(e.getMessage());
        }
    }
}