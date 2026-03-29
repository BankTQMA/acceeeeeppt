package com.lnw.acceeeeeppt.scene.transition;

import com.lnw.acceeeeeppt.scene.MainView;
import com.lnw.acceeeeeppt.ui.SceneConstants;

public class LevelFailController {
    LevelFailView levelFailView;
    MainView mainView;
    String currSceneIdentifier;

    public LevelFailController(LevelFailView levelFailView, MainView mainView, String currSceneIdentifier) {
        this.levelFailView = levelFailView;
        this.mainView = mainView;
        this.currSceneIdentifier = currSceneIdentifier;

        this.levelFailView.addRetryButtonActionListener(e -> onRetry());
        this.levelFailView.addMainMenuButtonActionListener(e -> onMainMenu());
    }

    private void onRetry() {
        // TODO: Add method in old controller to dispose old M,V,C
        // mainView.restart(currSceneIdentifier);
        dispose();
    }

    private void onMainMenu() {
        mainView.switchPanelCard(SceneConstants.MAINMENU);
        dispose();
    }

    private void dispose() {
        this.levelFailView = null;
    }
}
