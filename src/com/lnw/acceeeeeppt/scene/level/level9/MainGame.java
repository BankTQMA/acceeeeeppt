package com.lnw.acceeeeeppt.scene.level.level9;

import javax.swing.*;
import java.awt.*;

public class MainGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Agreement Idle - Level 9");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 750);
        frame.setLayout(new GridLayout(2, 1)); 

        startStat game = new startStat();
        frame.add(new ToSPage(game));
        frame.add(new idleMain(game));

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}