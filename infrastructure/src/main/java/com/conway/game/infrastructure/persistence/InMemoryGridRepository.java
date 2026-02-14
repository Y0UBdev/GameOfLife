package com.conway.game.infrastructure.persistence;

import com.conway.game.domain.model.Grid;
import com.conway.game.domain.port.GridRepository;

/**
 * Implémentation en mémoire du repository.
 * Utile pour les tests et le développement.
 * 
 * Principe SOLID: 
 * - Liskov Substitution: respecte le contrat de GridRepository
 * - Dependency Inversion: implémente l'abstraction du domaine
 */
public class InMemoryGridRepository implements GridRepository {
    
    private Grid savedGrid;
    
    @Override
    public void save(Grid grid) {
        this.savedGrid = grid;
    }
    
    @Override
    public Grid load() {
        return savedGrid != null ? savedGrid : Grid.empty();
    }
    
    @Override
    public boolean exists() {
        return savedGrid != null;
    }
}
