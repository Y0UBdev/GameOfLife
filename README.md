# Jeu de la Vie de Conway - Clean Architecture

Application Java implémentant le Jeu de la Vie de Conway avec une architecture propre et modulaire.

## 🏗️ Architecture

Ce projet suit les principes de Clean Architecture avec 4 modules Gradle:

```
conway-game-of-life/
├── domain/         # Logique métier pure (règles du jeu)
├── application/    # Cas d'usage et orchestration
├── infrastructure/ # Implémentations techniques
└── views/         # Interface utilisateur Swing
```

## 🎯 Principes SOLID Appliqués

- **Single Responsibility**: Chaque classe a une seule responsabilité
- **Open/Closed**: Extensible sans modification
- **Liskov Substitution**: Les abstractions sont substituables
- **Interface Segregation**: Interfaces ciblées et minimales
- **Dependency Inversion**: Dépendance vers les abstractions

## 🚀 Compilation et Exécution

```bash
# Compiler le projet
./gradlew build

# Exécuter l'application
./gradlew :views:run

# Exécuter les tests
./gradlew test
```

## 🎮 Utilisation

### Raccourcis Clavier

- **ENTER**: Démarrer/Pause la simulation
- **DELETE/BACKSPACE**: Effacer la grille
- **←**: Accélérer la simulation
- **→**: Ralentir la simulation
- **↑**: Zoomer
- **↓**: Dézoomer
- **ESC**: Afficher l'aide

### Souris

- **Clic gauche**: Activer/désactiver une cellule

## 📦 Modules

### Domain
Contient la logique métier pure:
- `Cell`: Value Object représentant une cellule
- `Grid`: Agrégat gérant l'état de la grille
- `GameRules`: Règles du Jeu de la Vie
- `GenerationComputer`: Calcul des générations

### Application
Contient les cas d'usage:
- `ToggleCellUseCase`: Activer/désactiver une cellule
- `ClearGridUseCase`: Effacer la grille
- `ComputeNextGenerationUseCase`: Calculer la génération suivante
- `SimulationService`: Gestion de l'exécution automatique

### Infrastructure
Implémentations techniques:
- `InMemoryGridRepository`: Persistance en mémoire
- `DependencyInjection`: Configuration des dépendances

### Views
Interface utilisateur Swing:
- `GameWindow`: Fenêtre principale
- `GridPanel`: Affichage de la grille
- `InfoPanel`: Informations (génération, cellules)
- `KeyboardController`: Gestion du clavier

## 🧪 Tests

Les tests unitaires sont organisés par module:

```bash
# Tester tout
./gradlew test

# Tester un module spécifique
./gradlew :domain:test
./gradlew :application:test
```

## 📝 Améliorations Futures

- [ ] Patterns prédéfinis (Glider, Blinker, etc.)
- [ ] Sauvegarde/Chargement de grilles
- [ ] Interface Web
- [ ] API REST
- [ ] Statistiques avancées
- [ ] Grille infinie avec viewport
- [ ] Thèmes personnalisables

## 📄 Licence

Ce projet est un exemple éducatif d'architecture logicielle propre.
