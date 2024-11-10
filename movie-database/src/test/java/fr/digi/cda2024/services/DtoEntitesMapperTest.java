package fr.digi.cda2024.services;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DtoEntitesMapperTest {

    @Test
    void parseAnneeSortieFilmTest() {
        DtoEntitesMapper map = new DtoEntitesMapper();

        LocalDate actual = map.parseAnneeSortieFilm("2014");

        assertEquals(LocalDate.of(2014, 1, 1), actual);
    }
}