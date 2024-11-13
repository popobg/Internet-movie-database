# MOVIES IMDb DATABASE
Projet de conception et développement d'une application console Java de création et requêtage d'une base de données MariaDB avec l'aide de l'ORM Hibernate.

## SOMMAIRE
1. [Contexte](#1-Contexte)
2. [Conception](#2-Conception)
3. [Fonctionnement](#3-Fonctionnement)\
    3.1. [Parsing du JSON et mapping des données](#31-Parsing-et-mapping)\
    3.2. [Création des entités](#32-Création-des-entités)\
    3.3. [Application de requêtage](#33-Application-de-requêtage)
4. [Pré-requis](#4-Pré-requis)\
    4.1. [Dépendances](#41-Dépendances)\
    4.2. [Installation](#42-Installation)
5. [Collaborateurs du projet](#5-Collaborateurs)
6. [Finalité](#6-Finalité)


## 1. Contexte
A partir d'un fichier JSON contenant des informations sur des films, nous devons extraire ces données, les écrire en base de données et permettre le requêtage.

## 2. Conception

Modèle conceptuel de Données (MCD) :

![MCD](Conception/MCD_database.jpg)

Unified Modeling Language (UML) :

![UML](Conception/UML_database.jpg)

Modèle Logique de Données (MLD) :

![MLD](Conception/MLD_database.jpg)

Diagramme de classe :

![Diagramme de classes](Conception/diagramme_classe.png)

Diagramme schématique des classes Traitement et QuerysMenu :

![Diagramme schématique des classes Traitement et QuerysMenu](Conception/diagramme_traitement-menu.png)


## 3. Fonctionnement
### 3.1. Parsing et mapping
### Récupération du fichier de données
Au lancement du programme de création de la base de données ([classe Remplissage](https://github.com/popobg/Internet-movie-database/blob/main/movie-database/src/main/java/fr/digi/cda2024/ihm/Remplissage.java)), la console demande à l'utilisateur de lui communiquer le chemin vers son fichier json contenant les données du film.\
Il est recommandé d'utiliser le chemin absolu pour éviter des erreurs, comme par exemple : *D:/dev/JPA/films.json*.

La classe service *FileTools* gère la récupération du chemin et la conversion en objet File.

### Parsing
Le fichier JSON contient un tableau d'objets représentant des informations liées à des films, contenant lui-même d'autres objets, tels que le pays de provenance du film, la date de naissance, les acteurs.\
Le parsing du JSON a été réalisé à l'aide de la **bibliothèque Jackson** (cf [dépendance](#41-Dépendances)). Elle permet de mapper les objets extraits de la base de données dans des classes intermédiaires, dites "DTO" (*Data Transfer Object*), dont la structure correspond à celles des objets du JSON.

La classe service *JsonParser* gère le parsing du JSON et renvoie un tableau d'objets FilmDTO.

### Mapping
Le mapping des classes DTO vers les classes entités POJO utilisées en base de données est géré par la classe service *DtoEntitesMapper*. Cette classe présente plusieurs méthodes permettant de mapper chaque objet vers son objet entité avec ses relations, et renvoie un Set d'objets Film.

Ce set est ensuite utilisé pour persister les données en base de données, grâce à la configuration de la persistance en classe dans les classes entités.

### Limites
- Les classes DTO sont propres au schéma d'organisation de ce fichier json. L'application ne fonctionnera pas avec un autre schéma.
- La classe *DtoEntitesMapper* mériterait un refactoring avec un découpage des méthodes en plusieurs classes éventuellement. L'algorithme pourrait aussi être amélioré, notamment en exploitant davantage les Set afin de gagner en performance.

### 3.2. Création des entités

1. Création du modèle des classes et de leurs interactions via un diagramme.
2. Création des entités.
3. Etude des relations rôle, castingPrincipal, realisateur et de leur fonctionnement spécifique.
4. Répartition des tâches :
    - Johan Guillen sur les relations filme_dans, situe_dans, nait et role.
    - Arnaud Clavier sur les relations integre, nationalite, casting_principal et realise.
5. Création de la classe cleRole utilisée comme méthode pour fusionner les ID de personnes, film, personnage et attribuer cet ID fusionné à la classe role.\
Création de la classe cleDeuxFacteur utilisée pour fusionner les ID de personnes et film et attribuer cet ID fusionné aux classes casting_principal et realise.
6. Mise en place de la Javadoc et homogénéisation des classes.

### 3.3. Application de requêtage
Menu basique sous terminal de commande, qui s'exécute avec la classe "Traitement".

Le menu est utilisé en deux temps avec deux types d'input :
- un premier input pour choisir quel type de recherche à faire dans la base de données, avec un choix de 6 options + 1 choix pour quitter le menu.
- un second input pour rentrer les paramètres de recherche relatifs au choix (en fonction du choix peut-être "nom d'actreur/actrice", "nom film" ou "date").\
Les paramètres sont ensuite utilisés par la méthode, appelés de la classe "QuerysMenu", qui correspond au choix fait dans le premier input. Elle fait le requêtage correspondant à la base de données et renvoie les données à la classe "Traitement" qui les affiche.

Le code est fonctionnel mais pas très DRY ni très optimisé.
Manque aussi peut-être d'un menu sur-jacent qui permet en une seule classe exécutable d'utiliser le remplissage (classe "Remplissage") de la base de données et la recherche (classe "Traitement").

On note que les inputs de "nom d'acteur/actice" et de "nom de film" sont de type String (pour les choix 1, 2, 4, 5 et 6). Ici, elles sont sensibles aux espaces en trop et aux fautes d'orthographe. De façon générale, les inputs entrés doivent être strictement égaux au nommage dans la base de données (à la colonne IDENTITE de la table personne pour le nom d'acteur/actice ; à la colonne NOM de la table film pour "nom de film").

## 4. Pré-requis
### 4.1. Dépendances
- **[JDK Java >= 17.0](https://www.oracle.com/java/technologies/downloads/)**
- Gestionnaire de projet **[Maven](https://maven.apache.org/)**
- Bibliothèque Java associée au Système de Gestion de Base de Données Relationnelles (SGBDR), par exemple [mariadb-java-client](https://mvnrepository.com/artifact/org.mariadb.jdbc/mariadb-java-client) (version 3.5.0) pour MariaDB
- Bibliothèque de l'**[ORM Hibernate](https://mvnrepository.com/artifact/org.hibernate.orm/hibernate-core)** (version 6.6.1.Final)
- Bibliothèque de gestion des tests unitaires **[Junit](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api)** (version 5.11.0) et **[TestNG](https://mvnrepository.com/artifact/org.testng/testng)** (version 7.10.2)
- Bibliothèques de création de bases de données test **[Mockito](https://mvnrepository.com/artifact/org.mockito/mockito-core)** (version 5.14.2)
- Bibliothèque parsing JSON-objets JAVA **[Jackson](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind)** (version 2.18.1)

### 4.2. Installation
Vous devez créer une base de données dans votre SGBD qui porte le nom "**movies**".\
Si vous utilisez MariaDB la commande de création sera la suivante :\
`CREATE DATABASE petstore CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';`.

Vous pouvez lui donner un autre nom si vous le souhaitez, il faudra alors modifier la ligne de connexion à la base de données dans le fichier *[persistence.xml](https://github.com/popobg/Internet-movie-database/blob/main/movie-database/src/main/resources/META-INF/persistence.xml)* : il faut modifier la ligne `<property name="jakarta.persistence.jdbc.url" value="jdbc:mariadb://localhost:3307/???"/>` dans les deux persistence-unit, en remplaçant les "???" par le nom de votre base de données.

Si vous utilisez un autre SGBD que MariaDB et que le port d'écoute de votre base de données n'est pas le port 3307, il faudra également modifier cette ligne du fichier de `persistence.xml`.\
Voici le template à suivre pour les lignes de connexion à la base de données, en remplaçant les valeurs terminant par "??" avec vos valeurs :

```XML
<persistence-unit name="persistence_unit_name??">
        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>

        <properties>
            <!-- configurations relatives à la connexion à la base de données -->
            <property name="jakarta.persistence.jdbc.url" value="protocole_jdbc:sous-protocole_sgbd://adresse_du_serveur:port_d'écoute/nom_base_de_données??"/>
            <property name="jakarta.persistence.jdbc.user" value="username??"/>
            <property name="jakarta.persistence.jdbc.password" value="password??"/>
            <!-- facultatif -->
            <property name="jakarta.persistence.jdbc.driver" value="sgbd_driver??"/>

            ...
        </properties>
</persistence-unit>
```

## 5. Collaborateurs
Camarades de classe dans la promotion Concepteur Développeur d'Applications dispensé par Diginamic (septembre 2024), une belle collaboration est née autour de ce projet.

- [Abel Correia](https://github.com/Erico-Labare)
- [Arnaud Clavier](https://github.com/Arnaud-C18)
- [Johan Guillen](https://github.com/sioupe)
- [Pauline Bouyssou](https://github.com/popobg)

## 6. Finalité :

Le programme est fonctionnel et respect les consignes. Le rendu est très satisfaisant (bien qu'on pense à plusieurs changements et optimisation possible). 

La répartition des tâches, en rétrospective, aurait pu être conceptualisée différemment.

Pour autant, le projet s'est très bien passé dans son ensemble et a été très formateur sur la dynamique de travail en groupe (organisation, utilisation de git en groupe, méthodologie type agile ...etc).
