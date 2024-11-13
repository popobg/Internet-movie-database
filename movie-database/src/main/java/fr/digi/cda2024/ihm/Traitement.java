package fr.digi.cda2024.ihm;

import fr.digi.cda2024.dal.QuerysMenu;

import java.util.List;
import java.util.Scanner;

/**
 * Classe menu executable
 * Met en forme l'interface dans le terminal et permet d'exécuter les methodes de requête issue de queryMenu
 */
public class Traitement {

    /**
     * Creation du scanner "Menu" utilisé pour la premier selection utilisateur
     * Stockage de la variable entrée par le premier scanner "Menu"
     */
    public static void main(String[] args) {

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

        QuerysMenu.getEntityManagerFactory("movies-jpa-update");

        //switch pour assigner le choix entré par l'utilisateur à une option affiché précédement
        switch (choice) {
            case 1:
                affichageFilmogarphieActeur();
                break;
            case 2:
                affichageCastingFilm();
                break;
            case 3:
                affichageFilmsEntreDeuxAnnees();
                break;
            case 4:
                affichageCommunsPourDeuxActeurs();
                break;
            case 5:
                affichageActeursCommunsPourDeuxFilms();
                break;
            case 6:
                affichageFilmEntreDeuxAnneesAvecActeur();
                break;
            case 7:
                System.out.println("Au revoir !");
                break;
        }
        scannerMenu.close();
        QuerysMenu.closeEntityManagerFactory();
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
        //Creation du scanner "Filmographie Acteur" utilisé pour taper le nom d'un acteur quand l'option 1 du menu est choisie
        Scanner scannerFilmogarphieActeur = new Scanner(System.in);
        //Stockage de la variable entrée par le scanner "Filmographie Acteur"
        String acteurNom = scannerFilmogarphieActeur.nextLine();

        //Requêtage et affichage des résultats
        List<String> filmographieActeur = QuerysMenu.getFilmographieActeur(acteurNom);
        if (filmographieActeur.isEmpty()) {
            System.out.println("Aucun film trouvé dans la base de données pour l'acteur " + acteurNom + ".");
        } else {
            System.out.println("La filmographieActeur de " + acteurNom + " :");
            filmographieActeur.forEach(System.out::println);
        }
        //Fermeture du EMF ouvert dans la methode queryMenu
//        QuerysMenu.closeEntityManagerFactory();
        //Fermeture scanner ci-dessus
        scannerFilmogarphieActeur.close();
    }

    /**
     * Methode qui permet de affichage du casting d’un film donné
     */
    private static void affichageCastingFilm() {
        System.out.print("Entrez titre du film : ");
        //Creation du scanner "Casting Film" ou "CF" utilisé pour taper le nom d'un film
        Scanner scannerCastingFilm = new Scanner(System.in);
        //Stockage de la variable entrée par le scanner "Casting Film"
        String filmNom = scannerCastingFilm.nextLine();

        //Requêtage et affichage des résultats
        List<String> castingFilm = QuerysMenu.getCastingFilm(filmNom);
        if (castingFilm.isEmpty()) {
            System.out.println("Aucun acteur/actrice trouvé(e) dans la base de données pour le film " + filmNom + ".");
        } else {
            System.out.println("Le casting pour le film " + filmNom + " :");
            castingFilm.forEach(System.out::println);
        }
        //Fermeture scanner ci-dessus
        scannerCastingFilm.close();

    }

    /**
     * Methode qui permet de affichage des films sortis entre 2 années données
     */
    private static void affichageFilmsEntreDeuxAnnees() {

        int anneeDebut = 0;
        int anneeFin = 0;

        //Scanner initaliser ici pour pouvoir les fermer
        Scanner scannerfilmsEntreDeuxAnnees1;
        Scanner scannerfilmsEntreDeuxAnnees2;

        while (true) {
            System.out.print("Entrez année de début : ");
            //Creation du scanner "Film Entre 2 Années 1" ou "FE2A1" utilisé pour taper une première année
            scannerfilmsEntreDeuxAnnees1 = new Scanner(System.in);

            //Check si l'input est un intègre
            while (!scannerfilmsEntreDeuxAnnees1.hasNextInt()) {
                System.out.println("ERREUR ! Vous venez d'entrée autre chose qu'un nombre entier.\nVeuillez entrée une année.");
                scannerfilmsEntreDeuxAnnees1.next();
            }
            //Stockage de la variable entrée par le scanner "Film Entre 2 Années 1"
            anneeDebut = scannerfilmsEntreDeuxAnnees1.nextInt();

            System.out.print("Entrez année de fin : ");
            //Creation du scanner "Film Entre 2 Années 2" ou "FE2A2" utilisé pour taper une seconde année
            scannerfilmsEntreDeuxAnnees2 = new Scanner(System.in);

            //Check si l'input est un intègre
            while (!scannerfilmsEntreDeuxAnnees2.hasNextInt()) {
                System.out.println("ERREUR ! Vous venez d'entrée autre chose qu'un nombre entier.\nVeuillez entrée une année.");
                scannerfilmsEntreDeuxAnnees2.next();
            }
            //Stockage de la variable entrée par le scanner "Film Entre 2 Années 2"
            anneeFin = scannerfilmsEntreDeuxAnnees2.nextInt();

            //Check si les inputs d'année sont bien début<fin
            if (anneeDebut <= anneeFin) {
                break;
            } else {
                System.out.println("\nErreur ! L'année de début ne peut pas être supérieur ou égale à l'année de fin.\nVeuillez re-entrée des années.");
            }
        }

        //Requêtage et affichage des résultats
        List<String> filmsEntreDeuxAnnees = QuerysMenu.getFilmsEntreDeuxAnnees(anneeDebut, anneeFin);
        if (filmsEntreDeuxAnnees.isEmpty()) {
            System.out.println("Aucun film trouvé dans la base de données pour entre les années " + anneeDebut + " et " + anneeFin + ".");
        } else {
            System.out.println("Les films sortis entre " + anneeDebut + " et " + anneeFin + " :");
            filmsEntreDeuxAnnees.forEach(System.out::println);
        }
        //Fermeture scanner ci-dessus
        scannerfilmsEntreDeuxAnnees1.close();
        scannerfilmsEntreDeuxAnnees2.close();
    }

    /**
     * Methode qui permet de affichage des films communs à 2 acteurs/actrices donnés.
     */
    private static void affichageCommunsPourDeuxActeurs() {
        System.out.print("Entrez nom complet du premier(e) l'acteur/actice : ");
        //Creation du scanner "Film Pour 2 Acteurs 1" ou "FP2A1" utilisé pour taper le nom d'un(e) premiere) acteur/actrice
        Scanner scannerCommunsPourDeuxActeurs1 = new Scanner(System.in);
        //Stockage de la variable entrée par le scanner "Film Pour 2 Acteurs 1"
        String acteurNom1 = scannerCommunsPourDeuxActeurs1.nextLine();
        System.out.print("Entrez nom complet du second(e) l'acteur/actice : ");
        //Creation du scanner "Film Pour 2 Acteurs 2" ou "FP2A2" utilisé pour taper le nom d'un(e) second(e) acteur/actrice
        Scanner scannerCommunsPourDeuxActeurs2 = new Scanner(System.in);
        //Stockage de la variable entrée par le scanner "Film Pour 2 Acteurs 2"
        String acteurNom2 = scannerCommunsPourDeuxActeurs2.nextLine();

        //Requêtage et affichage des résultats
        List<String> filmsCommunsPourDeuxActeurs = QuerysMenu.getFilmsCommunsPourDeuxActeurs(acteurNom1, acteurNom2);
        if (filmsCommunsPourDeuxActeurs.isEmpty()) {
            System.out.println("Aucun film commun trouvé dans la base de données pour " + acteurNom1 + " et " + acteurNom2 + ".");
        } else {
            System.out.println("Les films communs à " + acteurNom1 + " et " + acteurNom2 + " :");
            filmsCommunsPourDeuxActeurs.forEach(System.out::println);
        }
        //Fermeture scanner ci-dessus
        scannerCommunsPourDeuxActeurs1.close();
        scannerCommunsPourDeuxActeurs2.close();
    }

    /**
     * Methode qui permet de affichage des acteurs communs à 2 films donnés
     */
    private static void affichageActeursCommunsPourDeuxFilms() {
        System.out.print("Entrez titre du premier film : ");
        //Creation du scanner "Acteur Pour 2 Films 1" ou "AP2F1" utilisé pour taper le nom d'un premier film
        Scanner scannerActeursCommunsPourDeuxFilms1 = new Scanner(System.in);
        //Stockage de la variable entrée par le scanner "Acteur Pour 2 Films 1"
        String film1 = scannerActeursCommunsPourDeuxFilms1.nextLine();
        System.out.print("Entrez titre du second film : ");
        //Creation du scanner "Acteur Pour 2 Films 2" ou "AP2F2" utilisé pour taper le nom d'un deuxième film
        Scanner scannerActeursCommunsPourDeuxFilms2 = new Scanner(System.in);
        //Stockage de la variable entrée par le scanner "Acteur Pour 2 Films 2"
        String film2 = scannerActeursCommunsPourDeuxFilms2.nextLine();

        //Requêtage et affichage des résultats
        List<String> acteursCommunsPourDeuxFilms = QuerysMenu.getActeursCommunsPourDeuxFilms(film1, film2);
        if (acteursCommunsPourDeuxFilms.isEmpty()) {
            System.out.println("Aucun acteur/actrice trouvé(e) dans la base de données pour les films " + film1 + " et " + film2 + ".");
        } else {
            System.out.println("Le casting en commun pour les films " + film1 + "et " + film2 + " :");
            acteursCommunsPourDeuxFilms.forEach(System.out::println);
        }
        //Fermeture scanner ci-dessus
        scannerActeursCommunsPourDeuxFilms1.close();
        scannerActeursCommunsPourDeuxFilms2.close();
    }

    /**
     * Methode qui permet de affichage des films sortis entre 2 années données et qui ont un acteur/actrice donné au casting
     */
    private static void affichageFilmEntreDeuxAnneesAvecActeur() {
        System.out.print("Entrez nom complet de l'acteur/actice : ");
        //Creation du scanner "Film Entre 2 Années Avec Acteur 1" ou "FE2AAA1" utilisé pour taper le nom d'un acteur/actrice
        Scanner scannerFilmEntreDeuxAnneesAvecActeur1 = new Scanner(System.in);
        //Stockage de la variable entrée par le scanner "Film Entre 2 Années Avec Acteur 1"
        String acteurNom = scannerFilmEntreDeuxAnneesAvecActeur1.nextLine();

        int anneeDebut = 0;
        int anneeFin = 0;

        //Scanner initaliser ici pour pouvoir les fermer
        Scanner scannerFilmEntreDeuxAnneesAvecActeur2;
        Scanner scannerFilmEntreDeuxAnneesAvecActeur3;

        while (true) {
            System.out.print("Entrez année de début : ");
            //Creation du scanner "Film Entre 2 Années Avec Acteur 2" ou "FE2AAA2" utilisé pour taper une première année
            scannerFilmEntreDeuxAnneesAvecActeur2 = new Scanner(System.in);

            //Check si l'input est un intègre
            while (!scannerFilmEntreDeuxAnneesAvecActeur2.hasNextInt()) {
                System.out.println("ERREUR ! Vous venez d'entrée autre chose qu'un nombre entier.\nVeuillez entrée une année.");
                scannerFilmEntreDeuxAnneesAvecActeur2.next();
            }
            //Stockage de la variable entrée par le scanner "Film Entre 2 Années Avec Acteur 2"
            anneeDebut = scannerFilmEntreDeuxAnneesAvecActeur2.nextInt();

            System.out.print("Entrez année de fin : ");
            //Creation du scanner "Film Entre 2 Années Avec Acteur 3" ou "FE2AAA3" utilisé pour taper une deuxième année
            scannerFilmEntreDeuxAnneesAvecActeur3 = new Scanner(System.in);

            //Check si l'input est un intègre
            while (!scannerFilmEntreDeuxAnneesAvecActeur3.hasNextInt()) {
                System.out.println("ERREUR ! Vous venez d'entrée autre chose qu'un nombre entier.\nVeuillez entrée une année.");
                scannerFilmEntreDeuxAnneesAvecActeur3.next();
            }
            //Stockage de la variable entrée par le scanner "Film Entre 2 Années Avec Acteur 3"
            anneeFin = scannerFilmEntreDeuxAnneesAvecActeur3.nextInt();

            //Check si les inputs d'années sont bien début<fin
            if (anneeDebut <= anneeFin) {
                break;
            } else {
                System.out.println("\nErreur ! L'année de début ne peut pas être supérieur ou égale à l'année de fin.\nVeuillez re-entrée des années.");
            }
        }

        //Requêtage et affichage des résultats
        List<String> filmsEntreDeuxAnneesAvecActeur = QuerysMenu.getFilmsEntreDeuxAnneesAvecActeur(anneeDebut, anneeFin, acteurNom);
        if (filmsEntreDeuxAnneesAvecActeur.isEmpty()) {
            System.out.println("Aucun film trouvé dans la base de données pour " + acteurNom + " entre les années " + anneeDebut + " et " + anneeFin + ".");
        } else {
            System.out.println("\nLes films de " + acteurNom + " entre " + anneeDebut + " et " + anneeFin + " :");
            filmsEntreDeuxAnneesAvecActeur.forEach(System.out::println);
        }
        //Fermeture scanner ci-dessus
        scannerFilmEntreDeuxAnneesAvecActeur1.close();
        scannerFilmEntreDeuxAnneesAvecActeur2.close();
        scannerFilmEntreDeuxAnneesAvecActeur3.close();
    }
}

