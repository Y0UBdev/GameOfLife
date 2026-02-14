package com.conway.game.domain.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Value Object représentant une cellule dans la grille.
 * Immuable et contient uniquement ses coordonnées.
 * 
 * Principe SOLID: Single Responsibility - représente uniquement une position
 */
public final class Cell {
    
    private final int x;
    private final int y;
    
    private Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Factory method pour créer une cellule
     */
    public static Cell at(int x, int y) {
        return new Cell(x, y);
    }
    
    public int x() {
        return x;
    }
    
    public int y() {
        return y;
    }
    
    /**
     * Calcule tous les voisins de cette cellule (8 directions).
     * @return Set de 8 cellules voisines
     */
    public Set<Cell> neighbors() {
        Set<Cell> neighbors = new HashSet<>(8);
        
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;
                neighbors.add(Cell.at(x + dx, y + dy));
            }
        }
        
        return neighbors;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Cell other)) return false;
        return x == other.x && y == other.y;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    
    @Override
    public String toString() {
        return String.format("Cell(%d,%d)", x, y);
    }
}
