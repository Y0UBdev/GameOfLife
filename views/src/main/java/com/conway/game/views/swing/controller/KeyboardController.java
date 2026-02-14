package com.conway.game.views.swing.controller;

import com.conway.game.application.service.SimulationService;
import com.conway.game.application.usecase.ClearGridUseCase;
import com.conway.game.views.swing.panel.GridPanel;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Contrôleur gérant les événements clavier.
 * 
 * Principe SOLID:
 * - Single Responsibility: gestion clavier uniquement
 * - Dependency Inversion: dépend des use cases
 */
public class KeyboardController extends KeyAdapter {
    
    private final SimulationService simulationService;
    private final ClearGridUseCase clearGridUseCase;
    private final GridPanel gridPanel;
    
    public KeyboardController(
            SimulationService simulationService,
            ClearGridUseCase clearGridUseCase,
            GridPanel gridPanel) {
        
        this.simulationService = simulationService;
        this.clearGridUseCase = clearGridUseCase;
        this.gridPanel = gridPanel;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER -> simulationService.toggle();
            case KeyEvent.VK_DELETE, KeyEvent.VK_BACK_SPACE -> clearGridUseCase.execute();
            case KeyEvent.VK_LEFT -> simulationService.increaseSpeed();
            case KeyEvent.VK_RIGHT -> simulationService.decreaseSpeed();
            case KeyEvent.VK_UP -> gridPanel.zoomIn();
            case KeyEvent.VK_DOWN -> gridPanel.zoomOut();
            case KeyEvent.VK_ESCAPE -> showHelp();
        }
    }
    
    private void showHelp() {
        String helpMessage = """
            ╔══════════════════════════════════════════╗
            ║   Jeu de la Vie de Conway - Aide        ║
            ╠══════════════════════════════════════════╣
            ║                                          ║
            ║  ENTER      : Démarrer/Pause simulation  ║
            ║  DELETE     : Effacer la grille          ║
            ║  ←          : Accélérer                  ║
            ║  →          : Ralentir                   ║
            ║  ↑          : Zoomer                     ║
            ║  ↓          : Dézoomer                   ║
            ║  ESC        : Afficher cette aide        ║
            ║                                          ║
            ║  Clic souris : Activer/désactiver cellule║
            ║                                          ║
            ╚══════════════════════════════════════════╝
            """;
        
        JOptionPane.showMessageDialog(
            null,
            helpMessage,
            "Aide - Raccourcis clavier",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
