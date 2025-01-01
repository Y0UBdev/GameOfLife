# Conway's Game of Life

Conway's Game of Life est un automate cellulaire qui simule des phénomènes biologiques à l'aide de règles mathématiques simples. Il est représenté par une grille bidimensionnelle, théoriquement infinie. Les cases blanches représentent des cellules mortes, tandis que les cases noires représentent des cellules vivantes. *(Inspiré par Wikipédia)*

## 🎮 Un Jeu Complexe ?

Le Game of Life est défini par deux règles simples 📜 :

1. Une cellule morte ☠️ devient vivante si elle a exactement trois voisins vivants.
2. Une cellule vivante 💕 meurt si elle a moins de deux voisins (sous-population) ou plus de trois voisins (surpopulation).

Les cellules vivantes sont représentées par des cases noires, tandis que les cellules mortes sont représentées par des cases blanches.

## 🛠️ Mon Programme

### **Main.java**
- Utilise `JFrame` pour l'affichage de la fenêtre.
- Gère les interactions via `MessageManager`.
- Utilise `GridPanel` pour dessiner et mettre à jour la grille de cellules.

### **GridPanel.java**
- Génère une matrice de cellules avec une taille de départ modifiable pour permettre le zoom.
- Affiche et gère l'état des cellules, en mettant à jour la grille à chaque nouvelle génération.
  
### **Système Utilitaire**
- **Zoom** : Ajustez la taille des cellules dans la grille.
- **Vitesse de la simulation** : Réglez la durée de la pause entre chaque génération.
- **Réinitialisation** : Effacez la grille pour repartir de zéro.
- **Gestion des générations** : Utilise la classe `Generation` pour suivre et gérer chaque génération de cellules.

## ⌨️ Raccourcis Clavier

- **ENTER** : Démarrer / Mettre en pause la simulation.
- **DELETE / BACK_SPACE** : Effacer tout le panel.
- **LEFT** : Accélérer la simulation.
- **RIGHT** : Ralentir la simulation.
- **UP** : Zoomer.
- **DOWN** : Dézoomer.
- **ESC** : Afficher la fenêtre d'aide.
