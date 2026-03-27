package com.lnw.acceeeeeppt.system;

import com.lnw.acceeeeeppt.model.PlayerModel;

public class SaveManager {
    private SaveManager() {
        /* This utility class should not be instantiated */
    }

    public static String toHexString(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static PlayerModel createNewPlayerModel(String saveName) {
        return new PlayerModel(saveName);
    }
}
