package com.lnw.acceeeeeppt.scene.level.level7_stepping;

import javax.swing.SwingUtilities;

public class level7main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            level7GameLogic logic = new level7GameLogic();
            new level7GameUI(logic);
        });
    }
}
