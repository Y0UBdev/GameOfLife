package com.conway.game.domain.model;

/**
 * Service de domaine contenant les règles du Jeu de la Vie de Conway.
 * Stateless et pur - aucun état, uniquement de la logique métier.
 * 
 * Règles:
 * - Une cellule vivante avec 2-3 voisins vivants survit
 * - Une cellule vivante avec <2 ou >3 voisins meurt
 * - Une cellule morte avec exactement 3 voisins naît
 * 
 * Principe SOLID: Single Responsibility - calcule uniquement les règles du jeu
 */
public class GameRules {
    
    private static final int MIN_NEIGHBORS_TO_SURVIVE = 2;
    private static final int MAX_NEIGHBORS_TO_SURVIVE = 3;
    private static final int NEIGHBORS_TO_BIRTH = 3;
    
    /**
     * Détermine si une cellule vivante doit survivre
     */
    public boolean shouldSurvive(int aliveNeighborsCount) {
        return aliveNeighborsCount >= MIN_NEIGHBORS_TO_SURVIVE 
            && aliveNeighborsCount <= MAX_NEIGHBORS_TO_SURVIVE;
    }
    
    /**
     * Détermine si une cellule morte doit naître
     */
    public boolean shouldBirth(int aliveNeighborsCount) {
        return aliveNeighborsCount == NEIGHBORS_TO_BIRTH;
    }
    
    /**
     * Compte le nombre de voisins vivants d'une cellule
     */
    public int countAliveNeighbors(Cell cell, Grid grid) {
        return (int) cell.neighbors()
            .stream()
            .filter(grid::isAlive)
            .count();
    }
}
