package com.conway.game.application.port;

import com.conway.game.domain.model.Grid;

@FunctionalInterface
public interface GridObserver {
    void onGridUpdated(Grid grid);
}
