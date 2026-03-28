package com.lnw.acceeeeeppt.scene.menu;

import java.util.List;

import com.lnw.acceeeeeppt.model.PlayerModel;
import com.lnw.acceeeeeppt.scene.MainView;
import com.lnw.acceeeeeppt.system.SaveManager;
import com.lnw.acceeeeeppt.ui.SceneConstants;

public class LoadGameController {
    private MainView mainView;
    private LoadGameView loadGameView;

    public LoadGameController(MainView mainView, LoadGameView loadGameView) {
        this.mainView = mainView;
        this.loadGameView = loadGameView;

        this.loadGameView.addBackButtonActionListener(e -> onBack());

        List<PlayerModel> playerModelList = SaveManager.getAllSaves();
        for (PlayerModel p : playerModelList) {
            loadGameView.addSaveEntries(p.getSaveName(), p.getCurrLevel().toString(), "WIP");
        }
    }

    private void onBack() {
        mainView.switchPanelCard(SceneConstants.MAINMENU);
    }
}
