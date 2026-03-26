package com.lnw.acceeeeeppt.scene.level.level9;

import javax.swing.*;
import java.awt.*;

public class idleUI {

    public static JButton createButton(String name, int cost, String effect){
        JButton btn = new JButton();
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setMargin(new Insets(10,10,10,10));

        btn.setBackground(Color.WHITE);
        btn.setOpaque(true);

        updateButton(btn, name, cost, effect);
        return btn;
    }

    public static void updateButton(JButton btn, String name, int cost, String effect){
        btn.setText("<html><div style='text-align:left;'>"
                + name + " (Cost : " + cost + ")<br>"
                + effect + "</div></html>");
    }
}