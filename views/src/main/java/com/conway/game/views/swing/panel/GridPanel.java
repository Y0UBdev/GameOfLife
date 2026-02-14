package com.conway.game.views.swing.panel;

import com.conway.game.application.port.GridObserver;
import com.conway.game.application.service.GridStateManager;
import com.conway.game.application.usecase.ToggleCellUseCase;
import com.conway.game.domain.model.Cell;
import com.conway.game.domain.model.Grid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GridPanel extends JPanel implements GridObserver {
    
    private int cellSize;
    private Grid currentGrid;

    private final ToggleCellUseCase toggleCellUseCase;
    
    public GridPanel(
        int initialCellSize,
        GridStateManager gridStateManager,
        ToggleCellUseCase toggleCellUseCase
    ) {
        
        this.cellSize = initialCellSize;
        this.currentGrid = Grid.empty();
        this.toggleCellUseCase = toggleCellUseCase;
        
        gridStateManager.addObserver(this);
        
        setupMouseListener();
        
        setBackground(Color.WHITE);
    }
    
    private void setupMouseListener() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            toggleCellUseCase.execute(e.getX(), e.getY(), cellSize);
            }
        });
    }
    
    @Override
    public void onGridUpdated(Grid grid) {
        this.currentGrid = grid;
        SwingUtilities.invokeLater(this::repaint);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );
        
        drawGrid(g2d);
        drawCells(g2d);
    }
    
    private void drawGrid(Graphics2D g) {
        g.setColor(new Color(220, 220, 220));
        
        int width = getWidth();
        int height = getHeight();
        
        for (int x = 0; x < width; x += cellSize) {
            g.drawLine(x, 0, x, height);
        }
        
        for (int y = 0; y < height; y += cellSize) {
            g.drawLine(0, y, width, y);
        }
    }
    
    private void drawCells(Graphics2D g) {
        g.setColor(Color.BLACK);
        
        for (Cell cell : currentGrid.aliveCells()) {
            int x = cell.x() * cellSize;
            int y = cell.y() * cellSize;
            g.fillRect(x, y, cellSize, cellSize);
        }
    }
    
    public void zoomIn() {
        if (cellSize < 70) {
            cellSize += 10;
            repaint();
        }
    }
    
    public void zoomOut() {
        if (cellSize > 10) {
            cellSize -= 10;
            repaint();
        }
    }
}