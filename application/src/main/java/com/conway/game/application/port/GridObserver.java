package com.conway.game.application.port;

import com.conway.game.domain.model.Grid;

/**
 * Port d'entrée: Observer des changements de grille
 * 
 * Principe SOLID:
 * - Interface Segregation: interface minimale
 * - Dependency Inversion: l'application définit son besoin
 */
@FunctionalInterface
public interface GridObserver {
    
    /**
     * Appelé quand la grille est mise à jour
     */
    void onGridUpdated(Grid grid);
}
