package com.lnw.acceeeeeppt.scene.transition;

import javax.swing.JPanel;

import com.lnw.acceeeeeppt.scene.MainView;
import com.lnw.acceeeeeppt.ui.SceneConstants;

public class LevelFailController {
    LevelFailView levelFailView;
    MainView mainView;
    String currSceneIdentifier;
    JPanel currentScenePanel;

    public LevelFailController(LevelFailView levelFailView, MainView mainView, String currSceneIdentifier,
            JPanel currScenePanel) {
        this.levelFailView = levelFailView;
        this.mainView = mainView;
        this.currSceneIdentifier = currSceneIdentifier;
        this.currentScenePanel = currScenePanel;

        this.levelFailView.addRetryButtonActionListener(e -> onRetry());
        this.levelFailView.addMainMenuButtonActionListener(e -> onMainMenu());
    }

    private void onRetry() {

    }

    private void onMainMenu() {
        mainView.switchPanelCard(SceneConstants.MAINMENU);
        dispose();
    }

    private void dispose() {
        this.levelFailView = null;
    }
}
