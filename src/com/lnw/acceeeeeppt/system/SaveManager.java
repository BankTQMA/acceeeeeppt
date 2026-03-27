package com.lnw.acceeeeeppt.system;

import com.lnw.acceeeeeppt.model.PlayerModel;

public class SaveManager {
    private SaveManager() {
        /* This utility class should not be instantiated */
    }

    public static PlayerModel createNewPlayerModel(String saveName) {
        return new PlayerModel(saveName);
    }
}
