package com.lnw.acceeeeeppt.scene.menu;

import java.awt.event.ActionEvent;

import com.lnw.acceeeeeppt.scene.MainView;
import com.lnw.acceeeeeppt.ui.SceneConstants;

public class MainMenuController {
    private MainMenuView mainMenuView;
    private MainView mainView;
    private LoadGameController loadGameController;

    public MainMenuController(MainMenuView mainMenuView, MainView mainView, LoadGameController loadGameController) {
        this.mainMenuView = mainMenuView;
        this.mainView = mainView;
        this.loadGameController = loadGameController;

        setupButtonsActionHandler();
    }

    private void setupButtonsActionHandler() {
        mainMenuView.addNewGameButtonActionHandler(this::onNewGame);
        mainMenuView.addLoadGameButtonActionHandler(this::onLoadGame);
        mainMenuView.addOptionButtonActionHandler(this::onOption);
        mainMenuView.addCreditButtonActionHandler(this::onCredit);
        mainMenuView.addExitButtonActionHandler(this::onExit);
    }

    public void onNewGame(ActionEvent e) {
        mainView.switchPanelCard(SceneConstants.NEWGAMEMENU);
    }

    public void onLoadGame(ActionEvent e) {
        mainView.switchPanelCard(SceneConstants.LOADGAMEMENU);
    }

    public void onOption(ActionEvent e) {
        System.out.println("Option");
    }

    public void onCredit(ActionEvent e) {
        mainView.switchPanelCard(SceneConstants.CREDITMENU);
    }

    public void onExit(ActionEvent e) {
        System.out.println("Exit");
        System.exit(0);
    }
}
