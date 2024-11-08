package fr.digi.cda2024.services;

import java.io.File;

public class FileTools {

    /**
     * Demande à l'utilisateur de donner le path absolu vers le fichier
     * contenant les données.
     * Réitère la demande tant que le fichier passé n'est pas un fichier existant
     * sur la machine de l'utilisateur.
     * @return File, le fichier obtenu à partir du path donné par l'utilisateur
     */
    public static File getJsonFileFromUser() {
        File file;

        do {
            String pathfile = InputTools.getStringInput("Entrez le path absolu vers le fichier JSON 'films.json' installé sur votre machine :");
            file = new File(pathfile);
        } while (!(isFileExisting(file) && isFileJson(file)));

        return file;
    }

    /**
     * Vérifie que le fichier existe sur la machine, et que ce n'est pas un dossier.
     * @param file, fichier à tester
     * @return boolean, 'true' si le fichier existe et n'est pas un dossier,
     *          'false' sinon
     */
    public static boolean isFileExisting(File file) {
        return file.exists() && !file.isDirectory();
    }

    /**
     *
     * @param file
     * @return
     */
    public static boolean isFileJson(File file) {
        return file.getName().endsWith(".json");
    }
}
