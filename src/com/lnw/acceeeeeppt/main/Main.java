package com.lnw.acceeeeeppt.main;

import javax.swing.SwingUtilities;

import com.lnw.acceeeeeppt.scene.MainView;
import com.lnw.acceeeeeppt.scene.menu.CreditController;
import com.lnw.acceeeeeppt.scene.menu.CreditView;
import com.lnw.acceeeeeppt.scene.menu.LoadGameController;
import com.lnw.acceeeeeppt.scene.menu.LoadGameView;
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
            LoadGameView loadGameView = new LoadGameView();
            CreditView creditView = new CreditView();

            LoadGameController loadGameController = new LoadGameController(mainView, loadGameView);
            new MainMenuController(mainMenuView, mainView, loadGameController);
            new NewGameController(mainView, newGameView, loadGameController);
            new CreditController(mainView, creditView);

            mainView.registerPanel(mainMenuView, SceneConstants.MAINMENU);
            mainView.registerPanel(newGameView, SceneConstants.NEWGAMEMENU);
            mainView.registerPanel(loadGameView, SceneConstants.LOADGAMEMENU);
            mainView.registerPanel(creditView, SceneConstants.CREDITMENU);

            mainView.switchPanelCard(SceneConstants.MAINMENU);
            mainView.setFrameVisibility(true);
        });
    }
}
