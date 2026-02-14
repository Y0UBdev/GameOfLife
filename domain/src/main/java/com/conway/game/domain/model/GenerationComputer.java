package com.conway.game.domain.model;

import java.util.HashSet;
import java.util.Set;

public record GenerationComputer(GameRules gameRules) {

    public Grid computeNextGeneration(Grid currentGrid) {
        Set<Cell> nextAliveCells = new HashSet<>();
        Set<Cell> cellsToCheck = getCellsToCheck(currentGrid);

        for (Cell cell : cellsToCheck) {
            int aliveNeighbors = gameRules.countAliveNeighbors(cell, currentGrid);

            if (currentGrid.isAlive(cell)) {
                if (gameRules.shouldSurvive(aliveNeighbors)) nextAliveCells.add(cell);
            } else {
                if (gameRules.shouldBirth(aliveNeighbors)) nextAliveCells.add(cell);
            }
        }

        return currentGrid.nextGeneration(nextAliveCells);
    }

    private Set<Cell> getCellsToCheck(Grid grid) {
        Set<Cell> cellsToCheck = new HashSet<>(grid.aliveCells());

        for (Cell aliveCell : grid.aliveCells()) {
            cellsToCheck.addAll(aliveCell.neighbors());
        }

        return cellsToCheck;
    }
}
