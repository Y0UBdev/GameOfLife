package com.conway.game.application.service;

import com.conway.game.domain.model.Grid;
import com.conway.game.application.port.GridObserver;

import java.util.ArrayList;
import java.util.List;

public class GridStateManager {
    
    private Grid currentGrid;
    private final List<GridObserver> observers;
    
    public GridStateManager() {
        this.currentGrid = Grid.empty();
        this.observers = new ArrayList<>();
    }
    
    public void updateGrid(Grid newGrid) {
        this.currentGrid = newGrid;
        notifyObservers();
    }
    
    public Grid getCurrentGrid() {
        return currentGrid;
    }
    
    public void addObserver(GridObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (GridObserver observer : observers) {
            observer.onGridUpdated(currentGrid);
        }
    }
}
