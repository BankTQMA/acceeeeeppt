package com.lnw.acceeeeeppt.main;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.lnw.acceeeeeppt.scene.MainView;
import com.lnw.acceeeeeppt.scene.menu.MainMenuView;
import com.lnw.acceeeeeppt.ui.SceneConstants;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainView mainView = new MainView();
            JPanel mainMenuJPanel = new MainMenuView().getMainJPanel();
            mainView.registerPanel(mainMenuJPanel, SceneConstants.MAINMENU);
            mainView.switchPanelCard(SceneConstants.MAINMENU);
            mainView.setFrameVisibility(true);
        });
    }
}
