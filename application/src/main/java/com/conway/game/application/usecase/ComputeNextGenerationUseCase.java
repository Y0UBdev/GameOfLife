package com.conway.game.application.usecase;

import com.conway.game.application.service.GridStateManager;
import com.conway.game.domain.model.GenerationComputer;
import com.conway.game.domain.model.Grid;

public class ComputeNextGenerationUseCase {
    
    private final GridStateManager gridStateManager;
    private final GenerationComputer generationComputer;
    
    public ComputeNextGenerationUseCase(
            GridStateManager gridStateManager,
            GenerationComputer generationComputer) {
        this.gridStateManager = gridStateManager;
        this.generationComputer = generationComputer;
    }
    
    public void execute() {
        Grid currentGrid = gridStateManager.getCurrentGrid();
        Grid nextGrid = generationComputer.computeNextGeneration(currentGrid);
        gridStateManager.updateGrid(nextGrid);
    }
}
