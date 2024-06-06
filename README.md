# Jakarta_Project 

Ce projet est une application de gestion de bibliothèque développée avec React pour le frontend et Spring Boot/Jakarta pour le backend. Les instructions ci-dessous vous guideront pour cloner le projet, configurer et lancer l'application.

Realized By Hamzaoui Amine, Tikoue Tikoue Brand, Mammou Lisa, Malon Abdoul Wahab Traore Yaye

## Prérequis

- Git
- Docker Engine
- IDE Java (IntelliJ IDEA, Eclipse, etc.)
- Node.js et npm

## Installation

### Cloner le projet

1. Clonez le dépôt GitHub sur votre machine locale :

    ```bash
    git clone https://github.com/YOUR_USERNAME/Jakarta_Project.git
    cd Jakarta_Project
    ```

### Backend

2. Assurez-vous que Docker Engine est en cours d'exécution sur votre machine.

3. Naviguez jusqu'au dossier contenant le fichier `docker-compose.yml` :

    ```bash
    cd Jakarta_Project/Back/bookstoreapi/src/main/resources
    ```

4. Exécutez `docker-compose` pour lancer les conteneurs nécessaires (base de données, phpMyAdmin, etc.) :

    ```bash
    docker-compose up -d
    ```

5. Connectez-vous à votre base de données via phpMyAdmin en accédant à l'URL suivante dans votre navigateur :

    ```
    http://localhost:8080
    ```

6. Ouvrez votre IDE Java et exécutez le fichier `BookstoreapiApplication.java` situé dans :

    ```
    Jakarta_Project/Back/bookstoreapi/src/main/java/com/boolstore/bookstoreapi/BookstoreapiApplication.java
    ```

### Frontend

7. Ouvrez un terminal, naviguez jusqu'au dossier du frontend et installez les dépendances :

    ```bash
    cd Jakarta_Project/Front/library-management
    npm install
    ```

8. Démarrez l'application React :

    ```bash
    npm start
    ```

## Accéder à l'application

L'application React sera disponible à l'adresse suivante :

- http://localhost:3000

