package com.lnw.acceeeeeppt.scene.transition;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.lnw.acceeeeeppt.ui.FontPresets;
import com.lnw.acceeeeeppt.ui.MarginConstants;

public class LevelFailView extends JPanel {
    private JPanel buttonRowPanel;
    private JLabel passLabel;
    private JButton retryButton;
    private JButton mainMenuButton;

    public LevelFailView() {
        buttonRowPanel = new JPanel();
        passLabel = new JLabel("FAILED");
        retryButton = createButton("Retry");
        mainMenuButton = createButton("Go Back to Main Menu");

        setBackground(new Color(0xb7142c));

        // Container configuration
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(MarginConstants.GLOBALMARGIN, MarginConstants.GLOBALMARGIN,
                MarginConstants.GLOBALMARGIN, MarginConstants.GLOBALMARGIN));
        buttonRowPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        passLabel.setForeground(new Color(0xFFFFFF));
        passLabel.setFont(FontPresets.PASSFAILFONT);
        passLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(passLabel, BorderLayout.CENTER);

        buttonRowPanel.setBackground(new Color(0x000000, true));
        buttonRowPanel.add(retryButton);
        buttonRowPanel.add(Box.createHorizontalStrut(10));
        buttonRowPanel.add(mainMenuButton);

        add(buttonRowPanel, BorderLayout.SOUTH);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        button.setFont(FontPresets.H2BOLDFONT);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0x6d0b19));
        return button;
    }

    public void addRetryButtonActionListener(ActionListener l) {
        retryButton.addActionListener(l);
    }

    public void addMainMenuButtonActionListener(ActionListener l) {
        mainMenuButton.addActionListener(l);
    }

}
