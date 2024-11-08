package fr.digi.cda2024.services;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FileToolsTest {

    // Tests méthode isFileExisting()
    @Test
    void isFileExistingSuccess1() {
        assertTrue(FileTools.isFileExisting(new File("D:\\dev\\CDA JAVA\\15 - Projet JPA\\Internet Movie DataBase - Difficile\\films.json")));
    }

    @Test
    void isFileExistingSuccess2() {
        assertTrue(FileTools.isFileExisting(new File("D:\\dev\\CDA JAVA\\15 - Projet JPA\\Internet-movie-database\\movie-database\\src\\test\\resources\\donneesTest.json")));
    }

    @Test
    void isFileExistingFail1() {
        assertFalse(FileTools.isFileExisting(new File("D:\\dev\\CDA JAVA\\15 - Projet JPA\\Internet Movie DataBase - Difficile")));
    }

    @Test
    void isFileExistingFail2() {
        assertFalse(FileTools.isFileExisting(new File("D:\\dev\\CDA JAVA\\15 - Projet JPA\\Internet Movie DataBase - Difficile\\coucou.pdf")));
    }

    // Tests méthode isFileJson()
    @Test
    void isFileJsonSuccess1() {
        assertTrue(FileTools.isFileJson(new File("D:\\dev\\CDA JAVA\\15 - Projet JPA\\Internet Movie DataBase - Difficile\\films.json")));
    }

    @Test
    void isFileJsonSuccess2() {
        assertTrue(FileTools.isFileJson(new File("D:\\dev\\CDA JAVA\\15 - Projet JPA\\Internet-movie-database\\movie-database\\src\\test\\resources\\donneesTest.json")));
    }

    @Test
    void isFileJsonFail1() {
        assertFalse(FileTools.isFileJson(new File("D:\\dev\\CDA JAVA\\15 - Projet JPA\\Internet Movie DataBase - Difficile")));
    }

    @Test
    void isFileJsonFail2() {
        assertFalse(FileTools.isFileJson(new File("D:\\dev\\CDA JAVA\\15 - Projet JPA\\Internet Movie DataBase - Difficile\\coucou.pdf")));
    }
}