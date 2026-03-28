package com.lnw.acceeeeeppt.scene.menu;

import com.lnw.acceeeeeppt.scene.MainView;
import com.lnw.acceeeeeppt.ui.SceneConstants;

public class LoadGameController {
    private MainView mainView;
    private LoadGameView loadGameView;

    public LoadGameController(MainView mainView, LoadGameView loadGameView) {
        this.mainView = mainView;
        this.loadGameView = loadGameView;

        this.loadGameView.addBackButtonActionListener(e -> onBack());
    }

    private void onBack() {
        mainView.switchPanelCard(SceneConstants.MAINMENU);
    }
}
