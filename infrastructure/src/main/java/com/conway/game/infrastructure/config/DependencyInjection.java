package com.conway.game.infrastructure.config;

import com.conway.game.application.service.GridStateManager;
import com.conway.game.application.service.SimulationService;
import com.conway.game.application.usecase.*;
import com.conway.game.domain.model.GameRules;
import com.conway.game.domain.model.GenerationComputer;
import com.conway.game.domain.port.GridRepository;
import com.conway.game.infrastructure.persistence.InMemoryGridRepository;

/**
 * Configuration centralisée de l'injection de dépendances.
 * Pattern Factory pour créer le graphe de dépendances.
 * 
 * Principe SOLID:
 * - Dependency Inversion: assemble les dépendances
 * - Single Responsibility: configuration uniquement
 */
public class DependencyInjection {
    
    // Singletons
    private final GridStateManager gridStateManager;
    private final GameRules gameRules;
    private final GenerationComputer generationComputer;
    private final GridRepository gridRepository;
    
    // Use Cases
    private final ToggleCellUseCase toggleCellUseCase;
    private final ClearGridUseCase clearGridUseCase;
    private final ComputeNextGenerationUseCase computeNextGenerationUseCase;
    
    // Services
    private final SimulationService simulationService;
    
    public DependencyInjection() {
        // Domain
        this.gameRules = new GameRules();
        this.generationComputer = new GenerationComputer(gameRules);
        this.gridRepository = new InMemoryGridRepository();
        
        // Application
        this.gridStateManager = new GridStateManager();
        
        this.toggleCellUseCase = new ToggleCellUseCase(gridStateManager);
        this.clearGridUseCase = new ClearGridUseCase(gridStateManager);
        this.computeNextGenerationUseCase = new ComputeNextGenerationUseCase(
            gridStateManager,
            generationComputer
        );
        
        this.simulationService = new SimulationService(computeNextGenerationUseCase);
    }
    
    // Getters
    public GridStateManager getGridStateManager() {
        return gridStateManager;
    }
    
    public ToggleCellUseCase getToggleCellUseCase() {
        return toggleCellUseCase;
    }
    
    public ClearGridUseCase getClearGridUseCase() {
        return clearGridUseCase;
    }
    
    public ComputeNextGenerationUseCase getComputeNextGenerationUseCase() {
        return computeNextGenerationUseCase;
    }
    
    public SimulationService getSimulationService() {
        return simulationService;
    }
    
    public GridRepository getGridRepository() {
        return gridRepository;
    }
}
