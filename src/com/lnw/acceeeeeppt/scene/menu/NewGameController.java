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
    }

    public void onBackButton() {
        mainView.switchPanelCard(SceneConstants.MAINMENU);
    }
}
