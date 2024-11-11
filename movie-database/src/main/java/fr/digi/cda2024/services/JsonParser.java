package fr.digi.cda2024.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.digi.cda2024.dto.FilmDTO;

import java.io.File;
import java.io.IOException;

/**
 * Classe permettant de parser des fichiers JSON
 */
public final class JsonParser {

    /**
     * Parse les informations du fichier JSON donné vers les classes DTO
     * de l'application.
     * @param file fichier JSON
     * @return tableau d'objets FilmDTO
     * @throws IOException en cas d'erreur lors du parsing du JSON (fichier
     *                      introuvable, classes DTO inadéquates...)
     */
    public static FilmDTO[] JsonToDotParse(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        FilmDTO[] films;

        try {
            films = mapper.readValue(file, FilmDTO[].class);
        }
        catch (IOException e) {
            throw new IOException(e);
        }

        return films;
    }
}
