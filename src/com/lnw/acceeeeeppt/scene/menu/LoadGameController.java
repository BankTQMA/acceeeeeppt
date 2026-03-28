package com.lnw.acceeeeeppt.scene.menu;

import java.io.IOException;
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
        this.loadGameView.addDeleteButtonActionListener(e -> onDelete());

        List<PlayerModel> playerModelList = SaveManager.getAllSaves();
        for (PlayerModel p : playerModelList) {
            loadGameView.addSaveEntries(p.getSaveName(), p.getCurrLevel().toString(),
                    p.getCreatedDateTimeInstant().toString());
        }
    }

    private void onBack() {
        mainView.switchPanelCard(SceneConstants.MAINMENU);
    }

    private void onDelete() {
        String worldName = loadGameView.getSelectedWorldName();
        if (worldName == null) {
            loadGameView.showJOptionPaneInfo("Please select a world before pressing a delete button",
                    "World Not Selected");
            return;
        }
        try {
            SaveManager.deleteSave(loadGameView.getSelectedWorldName());
        } catch (IOException _) {
            loadGameView.showJOptionPaneWarning("Unable to Delete a save file (IOException)", "Unable to Delete");
        }
        loadGameView.showJOptionPaneInfo("World deletion success.", "Deletion Success");
        loadGameView.removeSelectedSaveSlotView();
    }
}
