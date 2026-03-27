package com.lnw.acceeeeeppt.scene.menu;

import com.lnw.acceeeeeppt.scene.MainView;
import com.lnw.acceeeeeppt.ui.SceneConstants;

public class NewGameController {
    private MainView mainView;
    private NewGameView newGameView;

    public NewGameController(MainView mainView, NewGameView view) {
        this.newGameView = view;
        this.mainView = mainView;
        this.newGameView.addBackButtonActionListener(e -> onBackButton());
        this.newGameView.addStandardButtonActionListener(e -> onStandardDifficulty());
        this.newGameView.addHardcoreButtonActionListener(e -> onHardcoreDifficulty());
    }

    public void onBackButton() {
        mainView.switchPanelCard(SceneConstants.MAINMENU);
    }

    public void onStandardDifficulty() {
        newGameView.setStandardButtonEnabled(false);
        newGameView.setHardcoreButtonEnabled(true);
    }

    public void onHardcoreDifficulty() {
        newGameView.setHardcoreButtonEnabled(false);
        newGameView.setStandardButtonEnabled(true);
    }
}
