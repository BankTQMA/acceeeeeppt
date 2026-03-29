package com.lnw.acceeeeeppt.scene.transition;

import com.lnw.acceeeeeppt.scene.MainView;
import com.lnw.acceeeeeppt.ui.SceneConstants;

public class LevelPassController {
    LevelPassView levelPassView;
    MainView mainView;
    String nextScene;

    public LevelPassController(LevelPassView levelPassView, MainView mainView, String nextScene) {
        this.levelPassView = levelPassView;
        this.mainView = mainView;
        this.nextScene = nextScene;

        this.levelPassView.addMainMenuButtonActionListener(e -> onMainMenu());
        this.levelPassView.addSaveAndContinueButtonActionListener(e -> onSaveAndContinue());
    }

    private void onMainMenu() {
        mainView.switchPanelCard(SceneConstants.MAINMENU);
        dispose();
    }

    private void onSaveAndContinue() {
        // TODO: Saving system
        mainView.switchPanelCard(nextScene);
        dispose();
    }

    private void dispose() {
        this.levelPassView = null;
    }

}
