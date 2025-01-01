package Main;

import Grid.GridPanel;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main {
    private static final int WINDOW_SIZE = 500;
    private static final int GRID_SIZE = 20;

    private static MessageManager messageManager;

    public static void main(String[] args) {
        // Creation de la Fenêtre
        JFrame frame = new JFrame("Jeu de la Vie de Conway");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Initialiser Taille
        frame.setSize(WINDOW_SIZE, WINDOW_SIZE);
        // Redimensionnable ?
        frame.setResizable(false);

        // Division de la fenêtre en GRILLE
        GridPanel gridPanel = new GridPanel(GRID_SIZE);
        // Ajouter la GRILLE à la Fenêtre
        frame.add(gridPanel);

        // Ajouter message d'information
        messageManager = new MessageManager(frame, gridPanel);
        messageManager.setupMessages();

        gridPanel.setMessageManager(messageManager);

        // Ajouter évenements Clavier
        keyPressed(frame, gridPanel);

        // Rendre Visible
        frame.setVisible(true);
    }

    /**
     * <h1>Pression sur une touche</h1>
     * @param frame Fenêtre
     * @param gridPanel Grille
     */
    private static void keyPressed(JFrame frame, GridPanel gridPanel) {
        // Ajouter Evenement
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                keyBoardManagement(e, gridPanel);
            }
        });
    }

    /**
     * <h1>Gestionnaire des Raccourcis Clavier</h1>
     * @param e Listes des Evenements de Touches
     * @param gridPanel Grilles
     *         switch (e.getKeyCode()) {
     *             // DEMARRER SIMULATION
     *             case KeyEvent.VK_ENTER :
     *                 gridPanel.setSimulationRunning(!gridPanel.isSimulationRunning());
     *                 messageManager.setStarted(!gridPanel.isSimulationRunning());
     *                 gridPanel.startSimulation();
     *                 break;
     *             // EFFACER PANEL
     *             case KeyEvent.VK_DELETE, KeyEvent.VK_BACK_SPACE : gridPanel.clearPanel();
     *                 break;
     *             // GERER LA VITESSE [LEFT|RIGHT]
     *             case KeyEvent.VK_LEFT : gridPanel.slower();
     *                 break;
     *             case KeyEvent.VK_RIGHT : gridPanel.faster();
     *                 break;
     *             // GERER LE ZOOM
     *             case KeyEvent.VK_UP : gridPanel.zoom();
     *                 break;
     *             case KeyEvent.VK_DOWN : gridPanel.dezoom();
     *                 break;
     *             // MESSAGE D'AIDE
     *             case KeyEvent.VK_ESCAPE : messageManager.helpDesk();
     *                 break;
     *         }
     */
    private static void keyBoardManagement(KeyEvent e, GridPanel gridPanel) {
        // DEMARRER SIMULATION
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            gridPanel.setSimulationRunning(gridPanel.isSimulationRunning());
            messageManager.setStarted(gridPanel.isSimulationRunning());
            gridPanel.startSimulation();
        }
        // EFFACER PANEL
        if (e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
            gridPanel.clearPanel();
        // GERER LA VITESSE [LEFT|RIGHT]
        if (e.getKeyCode() == KeyEvent.VK_LEFT) gridPanel.slower();
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) gridPanel.faster();
        // GERER LE ZOOM
        if (e.getKeyCode() == KeyEvent.VK_UP) gridPanel.zoom();
        if (e.getKeyCode() == KeyEvent.VK_DOWN) gridPanel.dezoom();
        // MESSAGE D'AIDE
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) messageManager.helpDesk();

        // Mettre à jour les messages
        update();
    }

    private static void update() {
        messageManager.updateMessages();
    }

}