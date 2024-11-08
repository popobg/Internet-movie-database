package fr.digi.cda2024.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.digi.cda2024.dto.FilmDTO;

import java.io.File;
import java.io.IOException;

public final class JsonParser {
    public static FilmDTO[] JsonToDOTParse(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        FilmDTO[] films;

        try {
            films = mapper.readValue(file, FilmDTO[].class);
        }
        catch (IOException e) {
            throw new IOException("Erreur lors du parsing du fichier JSON vers les classes DTO");
        }

        return films;
    }
}
