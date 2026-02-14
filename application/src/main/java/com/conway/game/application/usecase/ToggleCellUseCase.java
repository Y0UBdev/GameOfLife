package com.conway.game.application.usecase;

import com.conway.game.application.service.GridStateManager;
import com.conway.game.domain.model.Cell;
import com.conway.game.domain.model.Grid;

/**
 * Cas d'usage: Toggle une cellule sur la grille
 * 
 * Principe SOLID: Single Responsibility - une seule action métier
 */
public class ToggleCellUseCase {
    
    private final GridStateManager gridStateManager;
    
    public ToggleCellUseCase(GridStateManager gridStateManager) {
        this.gridStateManager = gridStateManager;
    }
    
    /**
     * Execute le toggle d'une cellule aux coordonnées pixel spécifiées
     */
    public void execute(int pixelX, int pixelY, int cellSize) {
        int gridX = pixelX / cellSize;
        int gridY = pixelY / cellSize;
        
        Cell cell = Cell.at(gridX, gridY);
        Grid currentGrid = gridStateManager.getCurrentGrid();
        Grid updatedGrid = currentGrid.toggleCell(cell);
        
        gridStateManager.updateGrid(updatedGrid);
    }
}
