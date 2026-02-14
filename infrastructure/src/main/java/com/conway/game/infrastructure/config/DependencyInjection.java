package com.conway.game.infrastructure.config;

import com.conway.game.application.service.GridStateManager;
import com.conway.game.application.service.SimulationService;
import com.conway.game.application.usecase.*;
import com.conway.game.domain.model.GameRules;
import com.conway.game.domain.model.GenerationComputer;
import com.conway.game.domain.port.GridRepository;
import com.conway.game.infrastructure.persistence.InMemoryGridRepository;

public class DependencyInjection {
    
    private final GridStateManager gridStateManager;

    private final ToggleCellUseCase toggleCellUseCase;
    private final ClearGridUseCase clearGridUseCase;

    private final SimulationService simulationService;
    
    public DependencyInjection() {
        GameRules gameRules = new GameRules();
        GenerationComputer generationComputer = new GenerationComputer(gameRules);

        this.gridStateManager = new GridStateManager();
        
        this.toggleCellUseCase = new ToggleCellUseCase(gridStateManager);
        this.clearGridUseCase = new ClearGridUseCase(gridStateManager);
        ComputeNextGenerationUseCase computeNextGenerationUseCase = new ComputeNextGenerationUseCase(
                gridStateManager,
                generationComputer
        );
        
        this.simulationService = new SimulationService(computeNextGenerationUseCase);
    }
    
    public GridStateManager getGridStateManager() {
        return gridStateManager;
    }
    
    public ToggleCellUseCase getToggleCellUseCase() {
        return toggleCellUseCase;
    }
    
    public ClearGridUseCase getClearGridUseCase() {
        return clearGridUseCase;
    }

    public SimulationService getSimulationService() {
        return simulationService;
    }
    
}
