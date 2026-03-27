package com.lnw.acceeeeeppt.main;

import javax.swing.SwingUtilities;

import com.lnw.acceeeeeppt.scene.MainView;
import com.lnw.acceeeeeppt.scene.menu.CreditView;
import com.lnw.acceeeeeppt.scene.menu.MainMenuController;
import com.lnw.acceeeeeppt.scene.menu.MainMenuView;
import com.lnw.acceeeeeppt.scene.menu.NewGameController;
import com.lnw.acceeeeeppt.scene.menu.NewGameView;
import com.lnw.acceeeeeppt.ui.SceneConstants;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainView mainView = new MainView();
            MainMenuView mainMenuView = new MainMenuView();
            NewGameView newGameView = new NewGameView();
            CreditView creditView = new CreditView();

            new MainMenuController(mainMenuView, mainView);
            new NewGameController(mainView, newGameView);

            mainView.registerPanel(mainMenuView, SceneConstants.MAINMENU);
            mainView.registerPanel(newGameView, SceneConstants.NEWGAMEMENU);
            mainView.registerPanel(creditView, SceneConstants.CREDITMENU);

            mainView.switchPanelCard(SceneConstants.MAINMENU);
            mainView.setFrameVisibility(true);
        });
    }
}
