package com.lnw.acceeeeeppt.scene.menu;

import com.lnw.acceeeeeppt.model.Difficulty;
import com.lnw.acceeeeeppt.model.PlayerModel;
import com.lnw.acceeeeeppt.scene.MainView;
import com.lnw.acceeeeeppt.system.SaveManager;
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
        this.newGameView.addAcceptButtonActionListener(e -> onAccept());
    }

    public void onBackButton() {
        mainView.switchPanelCard(SceneConstants.MAINMENU);
    }

    public void onStandardDifficulty() {
        newGameView.setStandardButtonEnabled(false);
        newGameView.setHardcoreButtonEnabled(true);
        newGameView.setDifficultyDescriptionType(true);
    }

    public void onHardcoreDifficulty() {
        newGameView.setHardcoreButtonEnabled(false);
        newGameView.setStandardButtonEnabled(true);
        newGameView.setDifficultyDescriptionType(false);
    }

    public void onAccept() {
        System.out.println("Acceeeeeppt Button Clicked! (Placeholder Action)");
        if (newGameView.getDifficulty() == Difficulty.HARDCORE)
            return;
        PlayerModel playerModel = SaveManager.createNewPlayerModel(newGameView.getSaveNameText());
        SaveManager.saveToDisk(playerModel);
    }
}
