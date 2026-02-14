package com.conway.game.domain.port;

import com.conway.game.domain.model.Grid;

public interface GridRepository {

    void save(Grid grid);

    Grid load();

    boolean exists();
}
