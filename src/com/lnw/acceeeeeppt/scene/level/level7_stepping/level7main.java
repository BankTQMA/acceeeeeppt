package com.lnw.acceeeeeppt.scene.level.level7_stepping;

import javax.swing.SwingUtilities;

public class Level7main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Level7GameLogic logic = new Level7GameLogic();
            new Level7GameUI(logic);
        });
    }
}
