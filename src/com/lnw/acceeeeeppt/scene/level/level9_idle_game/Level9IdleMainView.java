package com.lnw.acceeeeeppt.scene.level.level9_idle_game;

import javax.swing.*;
import java.awt.*;

public class Level9IdleMainView {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Level 9 Idle Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 750);
        frame.setLayout(new GridLayout(2, 1));

        Level9IdleModel game = new Level9IdleModel();
        frame.add(new Level9IdleTosView(game));
        frame.add(new Level9IdleGameView(game));

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
