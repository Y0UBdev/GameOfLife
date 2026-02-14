package com.conway.game.domain.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Agrégat représentant l'état complet de la grille du jeu.
 * Encapsule les cellules vivantes et garantit l'invariance.
 * 
 * Principe SOLID: Single Responsibility - gère uniquement l'état de la grille
 */
public class Grid {
    
    private final Set<Cell> aliveCells;
    private final int generationNumber;
    
    private Grid(Set<Cell> aliveCells, int generationNumber) {
        this.aliveCells = new HashSet<>(aliveCells);
        this.generationNumber = generationNumber;
    }
    
    /**
     * Crée une grille vide (génération 0)
     */
    public static Grid empty() {
        return new Grid(Collections.emptySet(), 0);
    }
    
    /**
     * Crée une grille avec des cellules initiales
     */
    public static Grid withCells(Set<Cell> cells) {
        return new Grid(cells, 0);
    }
    
    /**
     * Crée une nouvelle génération avec les cellules spécifiées
     */
    public Grid nextGeneration(Set<Cell> newAliveCells) {
        return new Grid(newAliveCells, generationNumber + 1);
    }
    
    /**
     * Toggle une cellule (vivante -> morte ou morte -> vivante)
     */
    public Grid toggleCell(Cell cell) {
        Set<Cell> newCells = new HashSet<>(aliveCells);
        
        if (newCells.contains(cell)) {
            newCells.remove(cell);
        } else {
            newCells.add(cell);
        }
        
        return new Grid(newCells, generationNumber);
    }
    
    /**
     * Efface toutes les cellules et remet la génération à 0
     */
    public Grid clear() {
        return Grid.empty();
    }
    
    public boolean isAlive(Cell cell) {
        return aliveCells.contains(cell);
    }
    
    public Set<Cell> aliveCells() {
        return Collections.unmodifiableSet(aliveCells);
    }
    
    public int generationNumber() {
        return generationNumber;
    }
    
    public int aliveCount() {
        return aliveCells.size();
    }
    
    public boolean isEmpty() {
        return aliveCells.isEmpty();
    }
}
