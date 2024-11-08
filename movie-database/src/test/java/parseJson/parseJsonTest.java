package parseJson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import parseJson.entitesTest.EntiteActeur;
import parseJson.entitesTest.EntiteFilm;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class parseJsonTest {

    @Test
    void parseJsonToObjectTest() {

        EntiteActeur acteurToto = new EntiteActeur("Toto", "Tata");
        EntiteActeur acteurTiti = new EntiteActeur("Titi", "Tutu");

        EntiteActeur[] acteurs = new EntiteActeur[] {
                acteurToto, acteurTiti
        };

        EntiteFilm film1 = new EntiteFilm("tt1801045", "Cowboy Ninja Viking", acteurs);
        EntiteFilm film2 = new EntiteFilm("tt7766378", "The Electric State", acteurs);

        EntiteFilm[] expected = new EntiteFilm[] {film1, film2};

        ObjectMapper mapper = new ObjectMapper();

        try {
            EntiteFilm[] actual = mapper.readValue(new File("D:\\dev\\CDA JAVA\\15 - Projet JPA\\Internet-movie-database\\movie-database\\src\\test\\resources\\donneeTest.json"), EntiteFilm[].class);

            System.out.println("Films parsés : ");
            for (EntiteFilm film : actual) {
                System.out.println(film);
                System.out.println();
            }

            System.out.println();
            System.out.println("Films attendus : ");
            for (EntiteFilm film : expected) {
                System.out.println(film);
                System.out.println();
            }

            assertArrayEquals(expected, actual);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


    }
}
