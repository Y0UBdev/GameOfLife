package com.conway.game.domain.model;

import java.util.HashSet;
import java.util.Set;

public record Cell(int x, int y) {

    public static Cell at(int x, int y) {
        return new Cell(x, y);
    }

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
    public String toString() {
        return String.format("Cell(%d,%d)", x, y);
    }
}
