package fr.digi.cda2024.services;

import fr.digi.cda2024.dto.FilmDTO;
import fr.digi.cda2024.entite.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DtoEntitesMapperTest {
    @Test
    void mapDtoVersEntitesTestNoException() {
        FilmDTO[] filmsDTO = new FilmDTO[1];
        try {
            filmsDTO = JsonParser.JsonToDotParse(new File("src\\test\\resources\\donneesTest.json"));
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            fail("Erreur parsing JSON vers DTO");
        }

        DtoEntitesMapper mapper = new DtoEntitesMapper();

        try {
            Set<Film> actualFilms = mapper.mapDtoVersEntites(filmsDTO);
            System.out.println("Tout est ok");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            fail("Erreur parsing DTO vers Entites");
        }
    }
}