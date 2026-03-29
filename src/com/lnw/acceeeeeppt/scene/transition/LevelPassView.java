package com.lnw.acceeeeeppt.scene.transition;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.lnw.acceeeeeppt.ui.FontPresets;
import com.lnw.acceeeeeppt.ui.MarginConstants;

public class LevelPassView extends JPanel {
    private JPanel topPanel;
    private JPanel buttonRowPanel;
    private JLabel passLabel;
    private JButton mainMenuButton;
    private JButton saveAndContinueButton;

    public LevelPassView() {
        topPanel = new JPanel();
        buttonRowPanel = new JPanel();
        passLabel = new JLabel("PASSED");
        mainMenuButton = new JButton("Go Back to Main Menu");
        saveAndContinueButton = new JButton("Save & Continue");

        setBackground(new Color(0x2CB714));

        // Container configuration
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(MarginConstants.GLOBALMARGIN, MarginConstants.GLOBALMARGIN,
                MarginConstants.GLOBALMARGIN, MarginConstants.GLOBALMARGIN));
        buttonRowPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        passLabel.setForeground(new Color(0xFFFFFF));
        passLabel.setFont(FontPresets.PASSFAILFONT);
        passLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(passLabel, BorderLayout.CENTER);
    }

    public void addMainMenuButtonActionListener(ActionListener l) {
        mainMenuButton.addActionListener(l);
    }

    public void addSaveAndContinueButtonActionListener(ActionListener l) {
        saveAndContinueButton.addActionListener(l);
    }
}
