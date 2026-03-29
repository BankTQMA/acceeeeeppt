package com.lnw.acceeeeeppt.scene.level.level9_idle_game;

import javax.swing.*;
import java.awt.*;

public class idleUI {
    public static JButton createButton(String name, int cost, String effect) {
        JButton btn = new JButton();
        btn.setBackground(new Color(240, 240, 240));
        btn.setFocusPainted(false);
        updateButton(btn, name, cost, effect);
        return btn;
    }

    public static void updateButton(JButton btn, String name, int cost, String effect) {
        btn.setText("<html><center><b>" + name + "</b><br>Cost: " + cost + "<br><font size='2'>" + effect
                + "</font></center></html>");
    }
}
