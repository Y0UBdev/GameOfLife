package com.conway.game.application.usecase;

import com.conway.game.application.service.GridStateManager;
import com.conway.game.domain.model.Grid;

/**
 * Cas d'usage: Efface toutes les cellules de la grille
 * 
 * Principe SOLID: Single Responsibility
 */
public class ClearGridUseCase {
    
    private final GridStateManager gridStateManager;
    
    public ClearGridUseCase(GridStateManager gridStateManager) {
        this.gridStateManager = gridStateManager;
    }
    
    public void execute() {
        Grid clearedGrid = gridStateManager.getCurrentGrid().clear();
        gridStateManager.updateGrid(clearedGrid);
    }
}
