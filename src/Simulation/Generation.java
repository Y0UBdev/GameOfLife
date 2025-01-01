package Simulation;

import Coordinate.GridCoordinate;

import java.util.HashSet;
import java.util.Set;

public class Generation {

    // CELLULE EN VIE
    private final Set<GridCoordinate> CELLS_IN_LIFE;

    // GESTION NOUVELLES CELLULES
    private final Set<GridCoordinate> LIFE; //Cellules qui naîtrons dans cette génération
    private final Set<GridCoordinate> DEATH; //Cellules qui mourront dans cette génération

    // CONSTRUCTEUR
    public Generation(Set<GridCoordinate> cellsInLife) {
        this.CELLS_IN_LIFE = cellsInLife;
        this.LIFE = new HashSet<>();
        this.DEATH = new HashSet<>();
        management();
    }

    /**
     * <h1>Gestion de la Generation</h1>
     * Gestion des cellules mortes et vivantes.
     */
    public void management() {
        // Traitement des cellules Vivantes
        for (GridCoordinate cell : CELLS_IN_LIFE) {
            checkLife(cell);
            checkDeath(cell);
        }
    }

    /**
     * <h1>Vérifier les cellules qui vont vivre</h1>
     * Itére sur chaque voisins de la cellules vivante et
     * vérifie si ses voisins sont peuvent basculer d'un
     * état à un autre.
     * @param cell GridCoordinate Cellule Vivante
     */
    private void checkLife(GridCoordinate cell) {
        // Traitement des voisins de la cellule
        for (GridCoordinate neighbor : cell.getNeighbors())
            // Verifie si une coordonnée à 3 voisins vivants
            if (ThreeLivingNeighbors(neighbor)) LIFE.add(neighbor);
    }
    /**
     * <h1>Vérifier les cellules qui vont mourrir</h1>
     * Itére sur chaque voisins de la cellules vivante et
     * vérifie si ses voisins sont en vie.
     * S'il y'a au moins 2 en vie, il vie, sinon il meurt.
     * @param cell GridCoordinate Cellule Vivante
     */
    private void checkDeath(GridCoordinate cell) {
        int index = 0;
        // Traitement des voisins de la cellule
        for (GridCoordinate neighbor : cell.getNeighbors())
            // Verifie si une coordonnée à 3 voisins vivants
            if (livingNeighbors(neighbor)) index++;
        if (index < 2 || index > 3) DEATH.add(cell);
    }

    /**
     * <h1>Voisins en Vie</h1>
     * @param coordinate Coordonnée Principale à Vérifier
     * @return boolean
     */
    private boolean ThreeLivingNeighbors(GridCoordinate coordinate) {
        int index = 0;
        for (GridCoordinate neighbor : coordinate.getNeighbors())
            if (CELLS_IN_LIFE.contains(neighbor)) index++;
        return index == 3;
    }
    /**
     * <h1>Voisins en Vie</h1>
     * @param coordinate Coordonnée Principale à Vérifier
     * @return boolean
     */
    private boolean livingNeighbors(GridCoordinate coordinate) {
        return CELLS_IN_LIFE.contains(coordinate);
    }

    // GETTEUR
    public Set<GridCoordinate> getLIFE() {
        return LIFE;
    }
    public Set<GridCoordinate> getDEATH() {
        return DEATH;
    }
}
