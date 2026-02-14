package com.conway.game.views;

import com.conway.game.infrastructure.config.DependencyInjection;
import com.conway.game.views.swing.GameWindow;

import javax.swing.SwingUtilities;

public class Main {
    
    public static void main(String[] args) {
        DependencyInjection di = new DependencyInjection();
        
        SwingUtilities.invokeLater(() -> {
            GameWindow window = new GameWindow(di);
            window.show();
        });
    }
}
