package com.lnw.acceeeeeppt.scene.menu;

public class NewGameController {
    private NewGameView view;

    public NewGameController(NewGameView view) {
        this.view = view;
        this.view.addBackButtonActionListener(e -> onBackButton());
    }

    public void onBackButton() {
        System.exit(0);
    }
}
