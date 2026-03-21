package com.lnw.acceeeeeppt.scene.menu;

import java.awt.event.ActionEvent;

public class MainMenuController {
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
