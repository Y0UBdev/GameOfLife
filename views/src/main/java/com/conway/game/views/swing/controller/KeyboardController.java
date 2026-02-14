package com.conway.game.views.swing.controller;

import com.conway.game.application.service.SimulationService;
import com.conway.game.application.usecase.ClearGridUseCase;
import com.conway.game.views.swing.panel.GridPanel;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
                - ENTER      : Démarrer/Pause simulation \s
                - DELETE     : Effacer la grille         \s
                - ←          : Accélérer                 \s
                - →          : Ralentir                  \s
                - ↑          : Zoomer                    \s
                - ↓          : Dézoomer                  \s
                - ESC        : Afficher cette aide       \s
                - Clic souris : Activer/désactiver cellule
           \s""";
        
        JOptionPane.showMessageDialog(
            null,
            helpMessage,
            "Aide - Raccourcis clavier",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
