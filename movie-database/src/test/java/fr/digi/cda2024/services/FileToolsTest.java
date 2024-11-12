package fr.digi.cda2024.services;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FileToolsTest {

    // Tests méthode isFileExisting()
    @Test
    void isFileExistingSuccess() {
        assertTrue(FileTools.isFileExisting(new File("src\\test\\resources\\donneesTest.json")));
    }

    @Test
    void isFileExistingFail1() {
        assertFalse(FileTools.isFileExisting(new File("src\\test")));
    }

    @Test
    void isFileExistingFail2() {
        assertFalse(FileTools.isFileExisting(new File("src\\test\\coucou.pdf")));
    }

    // Tests méthode isFileJson()
    @Test
    void isFileJsonSuccess() {
        assertTrue(FileTools.isFileJson(new File("src\\test\\resources\\donneesTest.json")));
    }

    @Test
    void isFileJsonFail1() {
        assertFalse(FileTools.isFileJson(new File("src\\test")));
    }

    @Test
    void isFileJsonFail2() {
        assertFalse(FileTools.isFileJson(new File("src\\test\\coucou.pdf")));
    }
}