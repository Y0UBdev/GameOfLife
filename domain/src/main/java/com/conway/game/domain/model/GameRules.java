package com.conway.game.domain.model;

public class GameRules {
    
    private static final int MIN_NEIGHBORS_TO_SURVIVE = 2;
    private static final int MAX_NEIGHBORS_TO_SURVIVE = 3;
    private static final int NEIGHBORS_TO_BIRTH = 3;

    public boolean shouldSurvive(int aliveNeighborsCount) {
        return aliveNeighborsCount >= MIN_NEIGHBORS_TO_SURVIVE 
            && aliveNeighborsCount <= MAX_NEIGHBORS_TO_SURVIVE;
    }

    public boolean shouldBirth(int aliveNeighborsCount) {
        return aliveNeighborsCount == NEIGHBORS_TO_BIRTH;
    }

    public int countAliveNeighbors(Cell cell, Grid grid) {
        return (int) cell.neighbors()
            .stream()
            .filter(grid::isAlive)
            .count();
    }
}
