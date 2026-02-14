package com.conway.game.domain.port;

import com.conway.game.domain.model.Grid;

/**
 * Port de sortie définissant le contrat pour la persistance de la grille.
 * 
 * Principe SOLID:
 * - Interface Segregation: interface minimale et ciblée
 * - Dependency Inversion: le domaine définit ce dont il a besoin
 */
public interface GridRepository {
    
    /**
     * Sauvegarde l'état actuel de la grille
     */
    void save(Grid grid);
    
    /**
     * Récupère le dernier état sauvegardé
     */
    Grid load();
    
    /**
     * Vérifie si un état sauvegardé existe
     */
    boolean exists();
}
