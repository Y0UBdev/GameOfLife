package com.conway.game.domain.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public record Grid(Set<Cell> aliveCells, int generationNumber) {

    public Grid(Set<Cell> aliveCells, int generationNumber) {
        this.aliveCells = new HashSet<>(aliveCells);
        this.generationNumber = generationNumber;
    }

    public static Grid empty() {
        return new Grid(Collections.emptySet(), 0);
    }

    public static Grid withCells(Set<Cell> cells) {
        return new Grid(cells, 0);
    }

    public Grid nextGeneration(Set<Cell> newAliveCells) {
        return new Grid(newAliveCells, generationNumber + 1);
    }

    public Grid toggleCell(Cell cell) {
        Set<Cell> newCells = new HashSet<>(aliveCells);

        if (newCells.contains(cell)) newCells.remove(cell);
            else newCells.add(cell);

        return new Grid(newCells, generationNumber);
    }

    public Grid clear() {
        return Grid.empty();
    }

    public boolean isAlive(Cell cell) {
        return aliveCells.contains(cell);
    }

    @Override
    public Set<Cell> aliveCells() {
        return Collections.unmodifiableSet(aliveCells);
    }

    public int aliveCount() {
        return aliveCells.size();
    }

}
