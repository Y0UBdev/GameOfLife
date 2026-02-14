package com.conway.game.infrastructure.persistence;

import com.conway.game.domain.model.Grid;
import com.conway.game.domain.port.GridRepository;

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
