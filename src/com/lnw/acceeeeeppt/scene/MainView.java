package com.lnw.acceeeeeppt.scene;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.lnw.acceeeeeppt.system.LookAndFeelManager;

public class MainView {
    private JFrame mainJFrame;
    private JPanel mainJPanel;
    private CardLayout cardLayout;

    public MainView() {
        LookAndFeelManager.applySystemLookAndFeel();

        mainJFrame = new JFrame("Acceeeeeppt");
        cardLayout = new CardLayout();
        mainJPanel = new JPanel(cardLayout);

        mainJFrame.setLayout(new BorderLayout());
        mainJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainJFrame.setSize(1080, 640);
        mainJFrame.setResizable(false);

        mainJFrame.add(mainJPanel);
    }

    public void registerPanel(JPanel panel, String cardNameString) {
        mainJPanel.add(panel, cardNameString);
    }

    public void switchPanelCard(String cardNameString) {
        cardLayout.show(mainJPanel, cardNameString);
    }

    public void setFrameVisibility(boolean b) {
        mainJFrame.setVisible(b);
    }

}
