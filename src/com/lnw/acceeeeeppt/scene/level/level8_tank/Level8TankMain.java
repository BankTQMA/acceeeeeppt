package com.lnw.acceeeeeppt.scene.level.level8_tank;

import javax.swing.SwingUtilities;
public class Level8TankMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Level8MainFrame();
            }
        });
}
}