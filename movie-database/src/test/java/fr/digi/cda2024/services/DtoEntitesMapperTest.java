package fr.digi.cda2024.services;

import fr.digi.cda2024.dto.FilmDTO;
import fr.digi.cda2024.entite.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DtoEntitesMapperTest {
    List<Film> expectedFilms;

    // Construction manuelle des films à partir des Entites
    // et des données du JSON test
//    @BeforeAll
//    static void constructionFilmsAttendus() {
//        // Construction du premier film
//        Pays paysFilm1 = new Pays("United States", "/search/title/?country_of_origin=US&ref_=tt_dt_cn");
//
//
//    }

    @Test
    void mapDtoVersEntitesTest() {
        FilmDTO[] filmsDTO = new FilmDTO[1];
        try {
            filmsDTO = JsonParser.JsonToDotParse(new File("src\\test\\resources\\donneesTest.json"));
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            fail("Erreur parsing DTO vers Entites");
        }

        DtoEntitesMapper mapper = new DtoEntitesMapper();
        List<Film> actualFilms = mapper.mapDtoVersEntites(filmsDTO);

        assertEquals(expectedFilms, actualFilms);
    }
}