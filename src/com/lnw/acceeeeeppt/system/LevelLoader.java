package com.lnw.acceeeeeppt.system;

import com.lnw.acceeeeeppt.scene.MainView;
import com.lnw.acceeeeeppt.scene.level.level2_term_of_investment.Level2InvestController;
import com.lnw.acceeeeeppt.scene.level.level2_term_of_investment.Level2InvestIntegration;
import com.lnw.acceeeeeppt.scene.level.level2_term_of_investment.Level2InvestModel;
import com.lnw.acceeeeeppt.scene.level.level2_term_of_investment.Level2InvestView;
import com.lnw.acceeeeeppt.scene.transition.LevelPassController;
import com.lnw.acceeeeeppt.scene.transition.LevelPassView;
import com.lnw.acceeeeeppt.ui.SceneConstants;

public class LevelLoader {
    private LevelLoader() {
        /* This utility class should not be instantiated */
    }

    public static void createLevel2(MainView mainView) {
        Level2InvestModel model = new Level2InvestModel(15);
        Level2InvestView view = new Level2InvestView();

        Level2InvestIntegration integration = new Level2InvestIntegration() {
            public void navigateToMenu() {
                mainView.switchPanelCard(SceneConstants.MAINMENU);
            }

            public void unlockNextStage() {
                LevelPassView levelPassView = new LevelPassView();
                new LevelPassController(levelPassView, mainView, SceneConstants.MAINMENU);
                mainView.registerPanel(levelPassView, SceneConstants.LEVELPASS);
                mainView.switchPanelCard(SceneConstants.LEVELPASS);
            }
        };
        new Level2InvestController(model, view, integration);

        mainView.registerPanel(view, SceneConstants.LEVEL1);
    }

    public static void loadLevel2(MainView mainView) {
        mainView.switchPanelCard(SceneConstants.LEVEL1);
    }
}
