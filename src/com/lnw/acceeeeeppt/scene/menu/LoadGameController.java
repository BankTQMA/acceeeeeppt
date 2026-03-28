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
        this.loadGameView.addRenameButtonActionListener(e -> onRename());
        this.loadGameView.addDeleteButtonActionListener(e -> onDelete());
        this.loadGameView.addLoadButtonActionListener(e -> onLoad());

        List<PlayerModel> playerModelList = SaveManager.getAllSaves();
        for (PlayerModel p : playerModelList) {
            loadGameView.addSaveEntries(p.getSaveName(), p.getCurrLevel().toString(),
                    p.getCreatedDateTimeInstant().toString());
        }
    }

    public void refreshLoadGameView() {
        loadGameView.refreshContentPanel();
    }

    public void addSaveEntriesView(String worldName, String level, String lastAccessDateTime) {
        loadGameView.addSaveEntries(worldName, level, lastAccessDateTime);
        refreshLoadGameView();
    }

    private void onBack() {
        mainView.switchPanelCard(SceneConstants.MAINMENU);
    }

    private void onRename() {
        String worldName = loadGameView.getSelectedWorldName();
        if (worldName == null) {
            loadGameView.showJOptionPaneInfo("Please select a world before pressing a rename button",
                    "World not Selected");
        }

        try {
            PlayerModel oldPlayerModel = SaveManager.getPlayerModelByName(worldName);

            String newName = null;
            while (true) {
                newName = loadGameView.showJOptionPaneInputDialog("Please new a new name", "Rename");
                if (newName == null)
                    return;
                if (newName.equals(""))
                    loadGameView.showJOptionPaneWarning("Invalid name, please try again.", "Invalid Name");
                else
                    break;
            }
            PlayerModel newPlayerModel = new PlayerModel(oldPlayerModel, newName);
            SaveManager.deleteSave(oldPlayerModel);
            loadGameView.removeSelectedSaveSlotView();
            SaveManager.saveToDisk(newPlayerModel);
            loadGameView.addSaveEntries(newPlayerModel.getSaveName(), newPlayerModel.getCurrLevel().toString(),
                    newPlayerModel.getCreatedDateTimeInstant().toString());
        } catch (ClassNotFoundException _) {
            loadGameView.showJOptionPaneWarning("For some reason, the savefile is corrupted, so... Good luck!",
                    "Save Corrupted");
        } catch (IOException _) {
            loadGameView.showJOptionPaneWarning("Read/Write error, please check your disk and try again later.",
                    "IO Exception");
        }
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
        loadGameView.setSelectedWorldName(null);
    }

    private void onLoad() {

    }
}
