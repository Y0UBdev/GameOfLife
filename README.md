# Jeu de la Vie de Conway

Application Java implémentant l'automate de John Conway avec une architecture Clean et modulaire.

## Table des Matières

- [Description](#description)
- [Prérequis](#prérequis)
- [Installation](#installation)
- [Compilation](#compilation)
- [Exécution](#exécution)
- [Utilisation](#utilisation)
- [Architecture](#architecture)
- [Développement](#développement)
- [Troubleshooting](#troubleshooting)

---

## Description

Le Jeu de la Vie est un automate cellulaire imaginé par le mathématicien britannique John Conway en 1970. Il s'agit d'un jeu à zéro joueur, ce qui signifie que son évolution est déterminée uniquement par son état initial.

### Règles du jeu

Le jeu se déroule sur une grille bidimensionnelle où chaque cellule peut être vivante ou morte.

**Règles d'évolution :**
- Une cellule vivante avec 2 ou 3 voisins vivants survit à la génération suivante
- Une cellule vivante avec moins de 2 voisins meurt (sous-population)
- Une cellule vivante avec plus de 3 voisins meurt (surpopulation)
- Une cellule morte avec exactement 3 voisins vivants devient vivante (reproduction)

### Caractéristiques de cette implémentation

- Interface graphique Swing responsive
- Simulation en temps réel avec contrôle de la vitesse
- Mode pause et reprise
- Zoom dynamique sur la grille
- Compteur de générations
- Compteur de cellules vivantes
- Architecture modulaire et testable

---

## Prérequis

### Obligatoire

- Java Development Kit (JDK) 17 ou supérieur
- Système d'exploitation : Windows, Linux ou macOS

### Optionnel

- Gradle 8.5+ (si vous souhaitez compiler sans l'IDE)
- IntelliJ IDEA (recommandé pour le développement)

### Vérifier l'installation de Java

```bash
java -version
```

Vous devriez voir une sortie similaire à :
```
java version "17.0.x" 2023-xx-xx LTS
Java(TM) SE Runtime Environment (build 17.0.x+xx)
```

Si Java n'est pas installé, téléchargez-le depuis : https://www.oracle.com/java/technologies/downloads/

---

## Installation

### Télécharger le projet

Téléchargez et extrayez l'archive du projet dans un répertoire de votre choix.

### Structure du projet

```
conway-refactored/
├── domain/              Logique métier (règles du jeu, entités)
├── application/         Cas d'usage et services
├── infrastructure/      Configuration et implémentations techniques
├── views/              Interface utilisateur Swing
├── build.gradle        Configuration Gradle racine
├── settings.gradle     Configuration multi-modules
└── README.md           Ce fichier
```

---

## Compilation

### Option 1 : Avec IntelliJ IDEA (Recommandé)

1. Ouvrez IntelliJ IDEA
2. Fichier > Ouvrir
3. Sélectionnez le dossier `conway-refactored`
4. Attendez que Gradle synchronise le projet (icône d'éléphant en bas à droite)
5. Le projet est prêt

IntelliJ téléchargera automatiquement toutes les dépendances nécessaires.

### Option 2 : Avec Gradle en ligne de commande

#### Si Gradle est installé

```bash
# Compiler tout le projet
gradle build

# Compiler sans les tests
gradle build -x test
```

#### Si Gradle n'est pas installé

Installez Gradle :

**Windows (avec Chocolatey) :**
```powershell
choco install gradle
```

**Linux (avec SDKMAN) :**
```bash
curl -s "https://get.sdkman.io" | bash
sdk install gradle 8.5
```

**macOS (avec Homebrew) :**
```bash
brew install gradle
```

**Installation manuelle :**
1. Téléchargez Gradle depuis https://gradle.org/releases/
2. Extrayez l'archive dans un répertoire (ex: C:\Gradle ou /opt/gradle)
3. Ajoutez le répertoire bin au PATH système

---

## Exécution

### Méthode 1 : Avec IntelliJ IDEA (Plus simple)

1. Dans l'explorateur de projet, naviguez vers :
   ```
   views > src > main > java > com.conway.game.views > Main.java
   ```

2. Faites un clic droit sur le fichier `Main.java`

3. Cliquez sur "Run 'Main.main()'"

Le jeu démarre immédiatement dans une nouvelle fenêtre.

### Méthode 2 : Avec Gradle

Dans le terminal IntelliJ ou dans un terminal où Gradle est disponible :

```bash
gradle :views:run
```

### Méthode 3 : Avec le JAR exécutable

#### Étape 1 : Créer le JAR

Ajoutez cette tâche au fichier `views/build.gradle`, si elle ne s'y trouve pas :

```gradle
tasks.register('fatJar', Jar) {
    archiveBaseName = 'ConwayGame'
    archiveVersion = '1.0'
    archiveClassifier = 'all'

    manifest {
        attributes 'Main-Class': 'com.conway.game.views.Main'
    }

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    from {
        configurations.runtimeClasspath.collect {
            it.isDirectory() ? it : zipTree(it)
        }
    }

    with jar
}
```

#### Étape 2 : Compiler le JAR

Dans le terminal IntelliJ :

```bash
gradle fatJar
```
ou via l'icône de gradle dans la barre des tâches sur la droite d'IntelliJ, dans la section views/Tasks/other/fatJar

Le JAR sera créé dans : `views/build/libs/ConwayGame-1.0-executable.jar`

#### Étape 3 : Exécuter le JAR

**Méthode A : Double-clic**
- Naviguez vers `views/build/libs/`
- Double-cliquez sur `ConwayGame-1.0-executable.jar`

**Méthode B : Ligne de commande**
```bash
java -jar views/build/libs/ConwayGame-1.0-executable.jar
```

---

## Utilisation

### Interface

L'application affiche une fenêtre de 600x600 pixels contenant :
- Une grille centrale où les cellules peuvent être activées/désactivées
- Un panneau d'information en bas affichant :
    - Le numéro de la génération actuelle
    - Le nombre de cellules vivantes
    - Un rappel du raccourci d'aide (ESC)

### Interaction à la souris

**Clic gauche sur une cellule :**
- Active une cellule morte (la rend noire)
- Désactive une cellule vivante (la rend blanche)

Utilisez la souris pour dessiner des motifs initiaux avant de lancer la simulation.

### Raccourcis clavier

#### Contrôle de la simulation

| Touche | Action |
|--------|--------|
| **ENTREE** | Démarrer ou mettre en pause la simulation |
| **SUPPR** ou **RETOUR ARRIERE** | Effacer toutes les cellules et réinitialiser |

#### Contrôle de la vitesse

| Touche | Action |
|--------|--------|
| **FLECHE GAUCHE** | Accélérer la simulation (diminuer le délai) |
| **FLECHE DROITE** | Ralentir la simulation (augmenter le délai) |

La vitesse par défaut est de 1 génération par seconde. Vous pouvez accélérer jusqu'à environ 10 générations par seconde ou ralentir jusqu'à 1 génération toutes les 2 secondes.

#### Contrôle du zoom

| Touche | Action |
|--------|--------|
| **FLECHE HAUT** | Zoomer (agrandir les cellules) |
| **FLECHE BAS** | Dézoomer (réduire les cellules) |

La taille des cellules varie de 10 à 70 pixels. Le zoom permet d'observer plus finement les motifs ou d'avoir une vue d'ensemble.

#### Aide

| Touche | Action |
|--------|--------|
| **ECHAP** ou **ESC** | Afficher la fenêtre d'aide avec tous les raccourcis |

### Exemple d'utilisation

1. Lancez l'application
2. Dessinez un motif en cliquant sur des cellules
3. Appuyez sur ENTREE pour démarrer la simulation
4. Observez l'évolution du motif
5. Ajustez la vitesse avec les flèches gauche/droite si nécessaire
6. Appuyez sur ENTREE pour mettre en pause
7. Appuyez sur SUPPR pour effacer et recommencer

### Motifs classiques à essayer

#### Blinker (oscillateur période 2)
```
XXX
```
Se transforme en :
```
 X
 X
 X
```
Et alterne indéfiniment.

#### Glider (vaisseau spatial)
```
 X
  X
XXX
```
Se déplace en diagonale à travers la grille.

#### Block (motif stable)
```
XX
XX
```
Reste inchangé à chaque génération.

---

## Architecture

Le projet suit les principes de Clean Architecture et SOLID.

### Modules Gradle

**domain/** - Logique métier pure
- `Cell` : Représente une cellule avec ses coordonnées
- `Grid` : Gère l'état de la grille (cellules vivantes, génération)
- `GameRules` : Implémente les règles du Jeu de la Vie
- `GenerationComputer` : Calcule la génération suivante

**application/** - Cas d'usage
- `ToggleCellUseCase` : Active/désactive une cellule
- `ClearGridUseCase` : Efface la grille
- `ComputeNextGenerationUseCase` : Calcule la prochaine génération
- `SimulationService` : Gère l'exécution automatique
- `GridStateManager` : Gère l'état et notifie les observateurs

**infrastructure/** - Implémentations techniques
- `DependencyInjection` : Configuration et câblage des dépendances
- `InMemoryGridRepository` : Persistance en mémoire (extensible)

**views/** - Interface utilisateur
- `Main` : Point d'entrée de l'application
- `GameWindow` : Fenêtre principale
- `GridPanel` : Affichage de la grille
- `InfoPanel` : Affichage des informations
- `KeyboardController` : Gestion des événements clavier

### Principes appliqués

**SOLID :**
- Single Responsibility : Chaque classe a une seule responsabilité
- Open/Closed : Extensible sans modification du code existant
- Liskov Substitution : Les abstractions sont respectées
- Interface Segregation : Interfaces spécifiques et ciblées
- Dependency Inversion : Dépendances vers les abstractions

**Clean Architecture :**
- Indépendance des frameworks
- Testabilité maximale
- Indépendance de l'interface utilisateur
- Séparation claire des couches

---

## Développement

### Standards de code

- Langage : Java 17
- Style : Suivre les conventions Java standard
- Nommage : Anglais uniquement
- Documentation : Javadoc pour toutes les classes publiques
- Tests : Tests unitaires avec JUnit 5 et AssertJ

---

## Troubleshooting

### L'application ne démarre pas

**Vérifiez votre version de Java :**
```bash
java -version
```
Assurez-vous d'avoir Java 17 ou supérieur.

**Erreur "Unable to access jarfile" :**
Vérifiez que le chemin vers le JAR est correct et que le fichier existe.

### La fenêtre est vide ou les cellules ne s'affichent pas

Essayez de redimensionner légèrement la fenêtre. Si le problème persiste, vérifiez que les pilotes graphiques sont à jour.

### La simulation est trop lente

Appuyez sur la flèche gauche plusieurs fois pour accélérer la vitesse de simulation.

### Les raccourcis clavier ne fonctionnent pas

Assurez-vous que la fenêtre du jeu a le focus (cliquez dessus). Les raccourcis ne fonctionnent que lorsque la fenêtre est active.

### Gradle ne compile pas

**Erreur "Could not resolve dependencies" :**
Vérifiez votre connexion internet. Gradle doit télécharger les dépendances.

**Erreur de version Java :**
```bash
# Définir JAVA_HOME correctement
export JAVA_HOME=/chemin/vers/jdk-17  # Linux/Mac
set JAVA_HOME=C:\Path\To\jdk-17       # Windows
```

### Gradle n'est pas reconnu en ligne de commande

Si vous obtenez l'erreur "gradle n'est pas reconnu", cela signifie que Gradle n'est pas installé sur votre système ou n'est pas dans le PATH.

**Solution recommandée :** Utilisez IntelliJ IDEA qui gère Gradle automatiquement.

**Alternative :** Installez Gradle manuellement (voir section Compilation).

---

## Auteur

- Projet refactorisé selon les principes Clean Architecture et SOLID.
- Architecture originale par Robert C. Martin (Uncle Bob).
- Implémentation du Jeu de la Vie par John Conway (1970).