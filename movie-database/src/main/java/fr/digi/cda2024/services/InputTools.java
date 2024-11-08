package fr.digi.cda2024.services;

import java.util.Scanner;

public final class InputTools {
    private InputTools() {}

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
