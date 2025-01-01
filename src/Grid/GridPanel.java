package Grid;

import Coordinate.GridCoordinate;
import Main.MessageManager;
import Simulation.Generation;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class GridPanel extends JPanel {
    // DIMENSION DE LA GRILLE
    private int cellsSize;

    // NUMERO GENERATION
    private int generation;

    // LISTE DES CELLULES VIVANTES
    private final Set<GridCoordinate> cellsInLife;

    // DEMARRER SIMULATION
    private boolean simulationRunning = false;

    // GESTIONNAIRE DE MESSAGE
    private MessageManager messageManager;

    // TEMPS DE PAUSE
    private int pause;

    /**
     * <h1>Constructeur GRILLE</h1>
     * @param cellsSize Taille des Case
     */
    public GridPanel(int cellsSize) {
        // Affectation Attributs
        this.cellsSize = cellsSize;
        generation = 0;
        cellsInLife = Collections.synchronizedSet(new HashSet<>());
        pause = 1000;
        // Ajouter Evenement pour la souris
        mouseEvent();
    }

    /**
     * <h1>Ajout d'Evenement pour la Souris</h1>
     */
    private void mouseEvent() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Capturer Position [x,y]
                GridCoordinate coordinate = new GridCoordinate(e.getX(), e.getY(), cellsSize);
                // Mettre à jour la Matrice
                if (!cellsInLife.add(coordinate)) cellsInLife.remove(coordinate);
                repaint(); // Redessine le panneau
            }
        });
    }

    /**
     * <h1>Démarrer la Simulation</h1>
     */
    public void startSimulation() {
        new Thread(() -> {
            // Traitement de la Simulation
            while (simulationRunning) {
                try {
                    // Nouvelle Génération
                    startGeneration();
                    // Temps de pause entre les générations
                    Thread.sleep(pause);
                } catch (InterruptedException e) {throw new RuntimeException(e);}
                repaint();
            }
        }).start();
    }
    /**
     * <h1>Gestion de la Nouvelle Génération</h1>
     */
    private synchronized void startGeneration() {
        // Nouvelle Génération
        Generation newGeneration = new Generation(cellsInLife);
        // Incrémenter le numéro de la génération suivante
        nextGeneration();
        // Mettre à jour la collection des cellules en Vie
        updateCellsInLife(newGeneration.getLIFE(), newGeneration.getDEATH());
        // Mettre à jour les messages
        if (messageManager != null) messageManager.updateMessages();
        // Repeindre tout le panel
        repaint();
    }

    /**
     * <h1>Mettre à jour les cellules vivantes</h1>
     * Ajouter/Supprimer les cellules à partir du résultat la génération actuelle
     * @param life Cellules Vivantes
     * @param death Cellules Mortes
     */
    private synchronized void updateCellsInLife(Set<GridCoordinate> life, Set<GridCoordinate> death) {
        synchronized(cellsInLife) {
            cellsInLife.addAll(life);
            cellsInLife.removeAll(death);
        }
    }

    /**
     * <h1>Incrémenter Génération Suivante</h1>
     */
    public void nextGeneration() {
        generation+=1;
        if (messageManager != null) messageManager.updateMessages();
    }

    // DRAWING
    /**
     * <h1>Redéfinition - Peindre le composant</h1>
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dessiner la grille
        drawGrid(g);
        // Dessiner la Case
        drawCells(g);
    }
    /**
     * <h1>Redéfinition - Peindre le composant</h1>
     */
    public synchronized void clearPanel() {
        // Vider l'ensemble des cellules vivantes
        synchronized(cellsInLife) {cellsInLife.clear();}
        // Réinistialiser le numéro de la génération
        generation = 0;
        // Redessiner la grille
        repaint();
    }
    /**
     * <h1>Dessiner la Grille</h1>
     * @param g Fenêtre d'affichage
     */
    private void drawGrid(Graphics g) {
        // Mettre à jour la couleur
        g.setColor(Color.BLACK);
        // Dessiner les lignes
        int width = getWidth();
        int height = getHeight();
        // Traitement des lignes et des colonnes
        for (int i = 0; i < width; i += cellsSize)
            g.drawLine(i, 0, i, height);
        for (int j = 0; j < height; j += cellsSize)
            g.drawLine(0, j, width, j);
    }
    /**
     * <h1>Dessiner les Cellules</h1>
     * @param g Fenêtre d'Affichage
     */
    private void drawCells(Graphics g) {
        // Mettre à jour la couleur
        g.setColor(Color.BLACK);
        // Traitement des Cellules
        synchronized(cellsInLife) {
            for (GridCoordinate coordinate : cellsInLife) {
                int x = coordinate.getX() * cellsSize;
                int y = coordinate.getY() * cellsSize;
                g.fillRect(x, y, cellsSize, cellsSize);
            }
        }
    }

    // GESTION DE LA VITESSE
    public void faster () {
        if (pause > 100) setPause(pause-100);
        if (pause <= 100 && pause > 0) setPause(pause-10);
    }
    public void slower () {
        if (pause < 1000) setPause(pause+100);
    }

    // GESTION DU ZOOM
    public void zoom () {
        if (cellsSize < 70) {
            setCellsSize(cellsSize + 10);
            repaint();
        }
    }
    public void dezoom () {
        if (cellsSize > 10) {
            setCellsSize(cellsSize - 10);
            repaint();
        }
    }

    // BOOLEAN
    public boolean isSimulationRunning() {
        return !simulationRunning;
    }

    // GETTEUR
    public int getCellsSize() {
        return cellsSize;
    }
    public int getPause() {
        return pause;
    }
    public int getGeneration() {
        return generation;
    }

    // SETTEUR
    public void setMessageManager(MessageManager messageManager) {
        this.messageManager = messageManager;
    }
    public void setSimulationRunning(boolean simulationRunning) {
        this.simulationRunning = simulationRunning;
    }
    public void setCellsSize(int cellsSize) {
        this.cellsSize = cellsSize;
    }
    public void setPause(int pause) {
        this.pause = pause;
    }
}