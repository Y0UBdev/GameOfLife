package com.conway.game.views.swing;

import com.conway.game.infrastructure.config.DependencyInjection;
import com.conway.game.views.swing.panel.GridPanel;
import com.conway.game.views.swing.panel.InfoPanel;
import com.conway.game.views.swing.controller.KeyboardController;

import javax.swing.*;
import java.awt.*;

public class GameWindow {
    
    private static final int WINDOW_SIZE = 600;
    private static final int INITIAL_CELL_SIZE = 20;
    
    private final JFrame frame;
    private final GridPanel gridPanel;
    private final InfoPanel infoPanel;
    private final KeyboardController keyboardController;
    
    public GameWindow(DependencyInjection di) {
        this.frame = new JFrame("Jeu de la Vie de Conway");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(WINDOW_SIZE, WINDOW_SIZE);
        this.frame.setResizable(false);
        
        this.gridPanel = new GridPanel(
            INITIAL_CELL_SIZE,
            di.getGridStateManager(),
            di.getToggleCellUseCase()
        );
        
        this.infoPanel = new InfoPanel(di.getGridStateManager());
        
        this.keyboardController = new KeyboardController(
            di.getSimulationService(),
            di.getClearGridUseCase(),
            gridPanel
        );
        
        setupLayout();
        setupKeyboardListener();
    }
    
    private void setupLayout() {
        frame.setLayout(new BorderLayout());
        frame.add(gridPanel, BorderLayout.CENTER);
        frame.add(infoPanel, BorderLayout.SOUTH);
    }
    
    private void setupKeyboardListener() {
        frame.addKeyListener(keyboardController);
    }
    
    public void show() {
        frame.setVisible(true);
        frame.requestFocus();
    }
}
