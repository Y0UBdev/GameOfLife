package Main;

import Grid.GridPanel;

import javax.swing.*;
import java.awt.*;

public class MessageManager {
    private final JFrame frame;
    private final GridPanel gridPanel;

    // SIMULATION DEMARRER
    private boolean started;

    private JLabel zoomMessage;
    private JLabel speedMessage;
    private JLabel helpLabel;
    private JLabel helpSimulationLabel;

    // CONSTRUCTEUR
    public MessageManager(JFrame frame, GridPanel gridPanel) {
        this.frame = frame;
        this.gridPanel = gridPanel;
    }

    /**
     * <h1>Rassemblements des messages</h1>
     */
    public void setupMessages() {
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel helpPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Ajouter le message d'aide
        helpLabel = new JLabel("[ESC] - HelpDesk ");
        helpLabel.setHorizontalAlignment(SwingConstants.LEFT);
        helpPanel.add(helpLabel);

        // Ajouter message de Simulation
        helpSimulationLabel = new JLabel("|  [ENTER] - Simulation");
        helpSimulationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        helpPanel.add(helpSimulationLabel);

        // Message Zoom
        zoomMessage = new JLabel("Zoom : " + zoom(gridPanel.getCellsSize()));
        zoomMessage.setHorizontalAlignment(SwingConstants.RIGHT);
        infoPanel.add(zoomMessage);

        // Message Vitesse
        speedMessage = new JLabel("Vitesse : " + speed(gridPanel.getPause()));
        speedMessage.setHorizontalAlignment(SwingConstants.LEFT);
        infoPanel.add(speedMessage);

        // Ajout des panels
        frame.add(helpPanel, BorderLayout.NORTH);
        frame.add(infoPanel, BorderLayout.SOUTH);
    }

    /**
     * <h1>Mettre à jour les messages</h1>
     */
    public void updateMessages() {
        if (zoomMessage != null && speedMessage != null) {
            zoomMessage.setText("Zoom : " + zoom(gridPanel.getCellsSize()));
            speedMessage.setText("Vitesse : " + speed(gridPanel.getPause()));
            if (started) {
                helpLabel.setText("[ESC] - HelpDesk ");
                helpSimulationLabel.setText("|  [ENTER] - Simulation");
            } else {
                helpLabel.setText("Génération n°" + gridPanel.getGeneration());
                helpSimulationLabel.setText("|  [ENTER] - Pause");
            }
        }
    }

    // FONCTIONS UTILITAIRES
    private String speed(int pause) {
        if (pause > 100) return "x" + (1000 - pause) / 100;
        if (pause >= 0) return "x" + (float) (1000 - pause) / 100;
        return "";
    }
    private String zoom(int zoom) {
        return "x" + (zoom / 10);
    }

    /**
     * <h1>Fenêtre d'aide</h1>
     * Fenêtre répertoriant les différents raccourcis clavier existant
     */
    public void helpDesk() {
        String helpMessage = """
            Raccourcis clavier :
                * ENTER : Démarrer/Pause la simulation
                * DELETE/BACK_SPACE : Supprimer tout le panel
                * LEFT : Accélérer la simulation
                * RIGHT : Ralentir la simulation
                * UP : Zoomer
                * DOWN : Dézoomer
                * ESC : Afficher cette fenêtre d'aide
        """;

        JOptionPane.showMessageDialog(null, helpMessage, "Aide - Raccourcis clavier", JOptionPane.INFORMATION_MESSAGE);
    }

    // SETTEUR
    public void setStarted(boolean started) {
        this.started = started;
    }
}
