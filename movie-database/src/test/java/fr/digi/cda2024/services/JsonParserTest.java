package fr.digi.cda2024.services;

import fr.digi.cda2024.dto.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class JsonParserTest {

    @Test
    void jsonToDotParseNoException() {
        try {
            FilmDTO[] films = JsonParser.JsonToDotParse(new File("src\\test\\resources\\donneesTest.json"));

            assertTrue(true);
        }
        catch (IOException e) {
            fail(e.getMessage());
        }
    }
}