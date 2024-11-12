package fr.digi.cda2024.services;

import java.util.Scanner;

/**
 * Classe de traitement et gestion des cas d'erreurs de saisie utilisateur
 */
public final class InputTools {
    private InputTools() {}

    /**
     * Demande à l'utilisateur de saisir des données dans la console.
     * @param message chaîne de caractères affichées dans la console
     *                avant de récupérer l'input.
     * @return la chaîne de caractères saisie par l'utilisateur.
     */
    public static String getStringInput(String message) {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        System.out.println();
        System.out.println(message);
        while (userInput.isEmpty()) {
            userInput = scanner.nextLine();
        }

        return userInput;
    }
}
