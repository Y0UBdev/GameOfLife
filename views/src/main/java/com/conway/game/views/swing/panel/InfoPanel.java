package com.conway.game.views.swing.panel;

import com.conway.game.application.port.GridObserver;
import com.conway.game.application.service.GridStateManager;
import com.conway.game.domain.model.Grid;

import javax.swing.*;
import java.awt.*;

/**
 * Panel d'affichage des informations (génération, cellules vivantes).
 * 
 * Principe SOLID: Single Responsibility - affichage des infos
 */
public class InfoPanel extends JPanel implements GridObserver {
    
    private final JLabel generationLabel;
    private final JLabel cellCountLabel;
    private final JLabel helpLabel;
    
    public InfoPanel(GridStateManager gridStateManager) {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(new Color(240, 240, 240));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        this.generationLabel = new JLabel("Génération: 0");
        this.cellCountLabel = new JLabel("Cellules: 0");
        this.helpLabel = new JLabel("[ESC] Aide");
        
        Font font = new Font("Arial", Font.PLAIN, 12);
        generationLabel.setFont(font);
        cellCountLabel.setFont(font);
        helpLabel.setFont(font);
        
        add(generationLabel);
        add(new JLabel(" | "));
        add(cellCountLabel);
        add(new JLabel(" | "));
        add(helpLabel);
        
        // S'enregistrer comme observer
        gridStateManager.addObserver(this);
    }
    
    @Override
    public void onGridUpdated(Grid grid) {
        SwingUtilities.invokeLater(() -> {
            generationLabel.setText("Génération: " + grid.generationNumber());
            cellCountLabel.setText("Cellules: " + grid.aliveCount());
        });
    }
}
