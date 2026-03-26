package com.lnw.acceeeeeppt.scene.menu;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class NewGameView extends JPanel {
    private JPanel topBar;
    private JPanel contentPanel;

    public NewGameView() {
        topBar = new JPanel();
        contentPanel = new JPanel();

        setLayout(new BorderLayout());
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        add(topBar, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
    }
}
