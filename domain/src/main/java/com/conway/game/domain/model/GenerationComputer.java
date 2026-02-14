package com.conway.game.domain.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Service de domaine responsable du calcul de la prochaine génération.
 * Applique les règles du jeu à l'état actuel pour produire le nouvel état.
 * 
 * Principe SOLID: 
 * - Single Responsibility: calcule uniquement la prochaine génération
 * - Dependency Inversion: dépend de l'abstraction GameRules
 */
public class GenerationComputer {
    
    private final GameRules gameRules;
    
    public GenerationComputer(GameRules gameRules) {
        this.gameRules = gameRules;
    }
    
    /**
     * Calcule la génération suivante basée sur l'état actuel
     */
    public Grid computeNextGeneration(Grid currentGrid) {
        Set<Cell> nextAliveCells = new HashSet<>();
        Set<Cell> cellsToCheck = getCellsToCheck(currentGrid);
        
        for (Cell cell : cellsToCheck) {
            int aliveNeighbors = gameRules.countAliveNeighbors(cell, currentGrid);
            
            if (currentGrid.isAlive(cell)) {
                if (gameRules.shouldSurvive(aliveNeighbors)) {
                    nextAliveCells.add(cell);
                }
            } else {
                if (gameRules.shouldBirth(aliveNeighbors)) {
                    nextAliveCells.add(cell);
                }
            }
        }
        
        return currentGrid.nextGeneration(nextAliveCells);
    }
    
    /**
     * Obtient toutes les cellules à vérifier:
     * - Cellules vivantes actuelles
     * - Tous leurs voisins (candidats à la naissance)
     */
    private Set<Cell> getCellsToCheck(Grid grid) {
        Set<Cell> cellsToCheck = new HashSet<>(grid.aliveCells());
        
        for (Cell aliveCell : grid.aliveCells()) {
            cellsToCheck.addAll(aliveCell.neighbors());
        }
        
        return cellsToCheck;
    }
}
