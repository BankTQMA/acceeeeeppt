package com.lnw.acceeeeeppt.scene.menu;

import java.awt.event.ActionEvent;

public class MainMenuController {
    private MainMenuView mainMenuView;

    public MainMenuController(MainMenuView mainMenuView) {
        this.mainMenuView = mainMenuView;
        setupButtonsActionHandler();
    }

    private void setupButtonsActionHandler() {
        mainMenuView.addNewGameButtonActionHandler(this::onNewGame);
        mainMenuView.addLoadGameButtonActionHandler(this::onLoadGame);
        mainMenuView.addOptionButtonActionHandler(this::onOption);
        mainMenuView.addCreditButtonActionHandler(this::onCredit);
        mainMenuView.addExitButtonActionHandler(this::onExit);
    }

    // TODO: To be implemented later when these page are created
    public void onNewGame(ActionEvent e) {
        System.out.println("New Game");
    }

    public void onLoadGame(ActionEvent e) {
        System.out.println("Load");
    }

    public void onOption(ActionEvent e) {
        System.out.println("Option");
    }

    public void onCredit(ActionEvent e) {
        System.out.println("Credit");
    }

    public void onExit(ActionEvent e) {
        System.out.println("Exit");
        System.exit(0);
    }
}
