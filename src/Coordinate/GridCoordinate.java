package Coordinate;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class GridCoordinate {
    // COORDONNEES
    private final int x;
    private final int y;

    // CONSTRUCTEUR
    public GridCoordinate(int x, int y, int size) {
        this.x = x / size;
        this.y = y / size;}
    public GridCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * <h1>Trouver les voisins de la coordonnées</h1>
     * @return Set(GridCoordinate)
     */
    private Set<GridCoordinate> findNeighbors() {
        // Initialisation
        Set<GridCoordinate> neighbors = new HashSet<>();
        // Traitement des voisins
        for (int dx = -1; dx <= 1; dx++)
            for (int dy = -1; dy <= 1; dy++)
                if (dx != 0 || dy != 0) neighbors.add(new GridCoordinate(x + dx, y + dy));
        return new HashSet<>(neighbors);
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Set<GridCoordinate> getNeighbors() {
        return findNeighbors();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GridCoordinate that)) return false;
        return x == that.x && y == that.y;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }

}
