package com.lnw.acceeeeeppt.main;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.lnw.acceeeeeppt.scene.MainView;
import com.lnw.acceeeeeppt.scene.menu.MainMenuController;
import com.lnw.acceeeeeppt.scene.menu.MainMenuView;
import com.lnw.acceeeeeppt.scene.menu.NewGameView;
import com.lnw.acceeeeeppt.ui.SceneConstants;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainView mainView = new MainView();
            MainMenuView mainMenuView = new MainMenuView();

            JPanel mainMenuJPanel = mainMenuView.getMainJPanel();
            JPanel newGameJPanel = new NewGameView();
            new MainMenuController(mainMenuView);

            mainView.registerPanel(mainMenuJPanel, SceneConstants.MAINMENU);
            mainView.registerPanel(newGameJPanel, SceneConstants.NEWGAMEMENU);

            mainView.switchPanelCard(SceneConstants.MAINMENU);
            mainView.setFrameVisibility(true);
        });
    }
}
