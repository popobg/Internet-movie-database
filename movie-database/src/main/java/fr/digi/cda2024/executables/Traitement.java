package fr.digi.cda2024.executables;

import java.util.Scanner;

/**
 * Classe menu executable
 * Met en forme l'interface dans le terminal et permet d'exécuter les methodes de requête issue de [PLACEHOLDER_NOMFICHIERREQUETAGE]
 *
 */
public class Traitement {

    /**
     * Creation du scanner "Menu" utilisé pour la premier selection utilisateur
     * Stockage de la variable entrée par le premier scanner "Menu"
     */
    public static void main(String[] args) {

        //Utilisation de la methode affichageMenu() pour afficher les options choisissable
        affichageMenu();

        //Creation du scanner "Menu" utilisé pour taper l'option que l'utilisateur veut utiliser
        Scanner scannerMenu = new Scanner(System.in);
        //Variable qui servira au stockage de la variable entrée par le scanner "Menu"
        int choice = -1;

        while (choice < 1 || choice > 7) {
            //Check si l'input est un intègre
            if (!scannerMenu.hasNextInt()) {
                System.out.println("ERREUR ! Vous venez d'entrée autre chose qu'un nombre entier.\nVeuillez entrée un nombre entre 1 et 6 comme option, ou le nombre 7 pour quittez le programme.");
                scannerMenu.next();
                continue;
            }

            choice = scannerMenu.nextInt();

            //Check si l'input est compris entre 1 et 7
            if (choice < 1 || choice > 7) {
                System.out.println("ERREUR ! Vous venez d'entrée un nombre entier autre que les nombre de 1 à 7.\nVeuillez entrée un nombre entre 1 et 6 comme option, ou le nombre 7 pour quittez le programme.*");
            }

        }

        //switch pour assigner le choix entré par l'utilisateur à une option affiché précédement
        switch (choice) {
            case 1:
                affichageFilmogarphieActeur();
                break;
            case 2:
                affichageCastingFilm();
                break;
            case 3:
                affichageFilmEntre2Annees();
                break;
            case 4:
                affichageFilmPour2Acteurs();
                break;
            case 5:
                affichageActeursPour2Films();
                break;
            case 6:
                affichageFilmEntre2AnneesAvecActeur();
                break;
            case 7:
                System.out.println("Au revoir !");
                break;
            default:
                System.out.println("ERREUR ! Vous venez d'entrée un nombre entier autre que les nombre de 1 à 7.\nVeuillez entrée un nombre entre 1 et 6 comme option, ou le nombre 7 pour quittez le programme.*");
                scannerMenu.next();
        }
        scannerMenu.close();
    }

    /**
     * Affichage des options du menu
     */
    private static void affichageMenu() {
        System.out.println("Menu :");
        System.out.println("1. Affichage de la filmographie d’un acteur donné");
        System.out.println("2. Affichage du casting d’un film donné");
        System.out.println("3. Affichage des films sortis entre 2 années données");
        System.out.println("4. Affichage des films communs à 2 acteurs/actrices donnés");
        System.out.println("5. Affichage des acteurs communs à 2 films donnés");
        System.out.println("6. Affichage des films sortis entre 2 années données et qui ont un acteur/actrice donné au casting");
        System.out.println("7. Fin de l’application");
        System.out.print("Veuillez choisir une option : ");
    }

    /**
     * Methode qui permet de affichage de la filmographie d’un acteur donné
     */
    private static void affichageFilmogarphieActeur() {
        System.out.print("Entrez nom complet de l'acteur/actice : ");
        //Creation du scanner "Filmographie Acteur" ou "FA" utilisé pour taper le nom d'un acteur quand l'option 1 du menu est choisie
        Scanner scannerFA = new Scanner(System.in);
        //Stockage de la variable entrée par le scanner "Filmographie Acteur"
        String acteurNom = scannerFA.nextLine();

        // !!!! PLACEHOLDER_METHODE_TRAITEMENT
        System.out.println("La filmographie de " + acteurNom + " :");
    }

    /**
     * Methode qui permet de affichage du casting d’un film donné
     */
    private static void affichageCastingFilm() {
        System.out.print("Entrez titre du film : ");
        //Creation du scanner "Casting Film" ou "CF" utilisé pour taper le nom d'un film
        Scanner scannerCF = new Scanner(System.in);
        //Stockage de la variable entrée par le scanner "Casting Film"
        String filmNom = scannerCF.nextLine();

        //  !!!! PLACEHOLDER_METHODE_TRAITEMENT
        System.out.println("Le casting pour le film " + filmNom + " :");
    }

    /**
     * Methode qui permet de affichage des films sortis entre 2 années données
     */
    private static void affichageFilmEntre2Annees() {

        int anneeDebut = 0;
        int anneeFin = 0;

        while (true) {
            System.out.print("Entrez année de début : ");
            //Creation du scanner "Film Entre 2 Années 1" ou "FE2A1" utilisé pour taper une première année
            Scanner scannerFE2A1 = new Scanner(System.in);

            //Check si l'input est un intègre
            while (!scannerFE2A1.hasNextInt()) {
                System.out.println("ERREUR ! Vous venez d'entrée autre chose qu'un nombre entier.\nVeuillez entrée une année.");
                scannerFE2A1.next();
            }
            //Stockage de la variable entrée par le scanner "Film Entre 2 Années 1"
            anneeDebut = scannerFE2A1.nextInt();

            System.out.print("Entrez année de fin : ");
            //Creation du scanner "Film Entre 2 Années 2" ou "FE2A2" utilisé pour taper une seconde année
            Scanner scannerFE2A2 = new Scanner(System.in);

            //Check si l'input est un intègre
            while (!scannerFE2A2.hasNextInt()) {
                System.out.println("ERREUR ! Vous venez d'entrée autre chose qu'un nombre entier.\nVeuillez entrée une année.");
                scannerFE2A2.next();
            }
            //Stockage de la variable entrée par le scanner "Film Entre 2 Années 2"
            anneeFin = scannerFE2A2.nextInt();

            //Check si les inputs d'année sont bien début<fin
            if (anneeDebut <= anneeFin) {
                break;
            } else {
                System.out.println("\nErreur ! L'année de début ne peut pas être supérieur ou égale à l'année de fin.\nVeuillez re-entrée des années.");
            }
        }

        //  !!!! PLACEHOLDER_METHODE_TRAITEMENT
        System.out.println("Les films sortis entre " + anneeDebut + " et " + anneeFin + " :");
    }

    /**
     * Methode qui permet de affichage des films communs à 2 acteurs/actrices donnés.
     */
    private static void affichageFilmPour2Acteurs() {
        System.out.print("Entrez nom complet du premier(e) l'acteur/actice : ");
        //Creation du scanner "Film Pour 2 Acteurs 1" ou "FP2A1" utilisé pour taper le nom d'un(e) premiere) acteur/actrice
        Scanner scannerFP2A1 = new Scanner(System.in);
        //Stockage de la variable entrée par le scanner "Film Pour 2 Acteurs 1"
        String acteur1 = scannerFP2A1.nextLine();
        System.out.print("Entrez nom complet du second(e) l'acteur/actice : ");
        //Creation du scanner "Film Pour 2 Acteurs 2" ou "FP2A2" utilisé pour taper le nom d'un(e) second(e) acteur/actrice
        Scanner scannerFP2A2 = new Scanner(System.in);
        //Stockage de la variable entrée par le scanner "Film Pour 2 Acteurs 2"
        String acteur2 = scannerFP2A2.nextLine();

        //  !!!! PLACEHOLDER_METHODE_TRAITEMENT
        System.out.println("Les films communs à " + acteur1 + " et " + acteur2 + " :");
    }

    /**
     * Methode qui permet de affichage des acteurs communs à 2 films donnés
     */
    private static void affichageActeursPour2Films() {
        System.out.print("Entrez titre du premier film : ");
        //Creation du scanner "Acteur Pour 2 Films 1" ou "AP2F1" utilisé pour taper le nom d'un premier film
        Scanner scannerAP2F1 = new Scanner(System.in);
        //Stockage de la variable entrée par le scanner "Acteur Pour 2 Films 1"
        String film1 = scannerAP2F1.nextLine();
        System.out.print("Entrez titre du second film : ");
        //Creation du scanner "Acteur Pour 2 Films 2" ou "AP2F2" utilisé pour taper le nom d'un deuxième film
        Scanner scannerAP2F2 = new Scanner(System.in);
        //Stockage de la variable entrée par le scanner "Acteur Pour 2 Films 2"
        String film2 = scannerAP2F2.nextLine();

        //  !!!! PLACEHOLDER_METHODE_TRAITEMENT
        System.out.println("Les acteurs communs aux films " + film1 + " et " + film2 + " :");
    }

    /**
     * Methode qui permet de affichage des films sortis entre 2 années données et qui ont un acteur/actrice donné au casting
     */
    private static void affichageFilmEntre2AnneesAvecActeur() {
        System.out.print("Entrez nom complet de l'acteur/actice : ");
        //Creation du scanner "Film Entre 2 Années Avec Acteur 1" ou "FE2AAA1" utilisé pour taper le nom d'un acteur/actrice
        Scanner scannerFE2AAA1 = new Scanner(System.in);
        //Stockage de la variable entrée par le scanner "Film Entre 2 Années Avec Acteur 1"
        String acteurNom = scannerFE2AAA1.nextLine();

        int anneeDebut = 0;
        int anneeFin = 0;

        while (true) {
            System.out.print("Entrez année de début : ");
            //Creation du scanner "Film Entre 2 Années Avec Acteur 2" ou "FE2AAA2" utilisé pour taper une première année
            Scanner scannerFE2AAA2 = new Scanner(System.in);

            //Check si l'input est un intègre
            while (!scannerFE2AAA2.hasNextInt()) {
                System.out.println("ERREUR ! Vous venez d'entrée autre chose qu'un nombre entier.\nVeuillez entrée une année.");
                scannerFE2AAA2.next();
            }
            //Stockage de la variable entrée par le scanner "Film Entre 2 Années Avec Acteur 2"
            anneeDebut = scannerFE2AAA2.nextInt();

            System.out.print("Entrez année de fin : ");
            //Creation du scanner "Film Entre 2 Années Avec Acteur 3" ou "FE2AAA3" utilisé pour taper une deuxième année
            Scanner scannerFE2AAA3 = new Scanner(System.in);

            //Check si l'input est un intègre
            while (!scannerFE2AAA3.hasNextInt()) {
                System.out.println("ERREUR ! Vous venez d'entrée autre chose qu'un nombre entier.\nVeuillez entrée une année.");
                scannerFE2AAA3.next();
            }
            //Stockage de la variable entrée par le scanner "Film Entre 2 Années Avec Acteur 3"
            anneeFin = scannerFE2AAA3.nextInt();

            //Check si les inputs d'années sont bien début<fin
            if (anneeDebut <= anneeFin) {
                break;
            } else {
                System.out.println("\nErreur ! L'année de début ne peut pas être supérieur ou égale à l'année de fin.\nVeuillez re-entrée des années.");
            }
        }

        //  !!!! PLACEHOLDER_METHODE_TRAITEMENT
        System.out.println("\nLes films de " + acteurNom + " entre " + anneeDebut + " et " + anneeFin + " :");
    }
}

