package com.lnw.acceeeeeppt.scene.menu;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.lnw.acceeeeeppt.ui.FontPresets;
import com.lnw.acceeeeeppt.ui.MarginConstants;
import com.lnw.acceeeeeppt.ui.SceneConstants;

public class NewGameView extends JPanel {
    private JPanel topBar;
    private JPanel contentPanel;
    private JButton backButton;
    private JLabel newGameTitle;
    private JTextField saveNameTextField;
    private JLabel pleaseSelectDifficultyLabel;

    public NewGameView() {
        // Container initialisation
        topBar = new JPanel();
        contentPanel = new JPanel();

        // Component initiallisation
        backButton = new JButton("< Back");
        newGameTitle = new JLabel(SceneConstants.NEWGAMEMENU);
        saveNameTextField = new JTextField("");
        pleaseSelectDifficultyLabel = new JLabel("Please Select Difficulty");

        // Container Configuration
        setLayout(new BorderLayout());
        topBar.setLayout(new BorderLayout());
        topBar.setBorder(BorderFactory.createEmptyBorder(MarginConstants.GLOBALMARGIN, MarginConstants.GLOBALMARGIN,
                MarginConstants.GLOBALMARGIN, MarginConstants.GLOBALMARGIN));
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setFont(FontPresets.H2BOLDFONT);
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        newGameTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        newGameTitle.setFont(FontPresets.H1BOLDFONT);

        saveNameTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveNameTextField.setFont(FontPresets.H2PLAINFONT);
        saveNameTextField.setColumns(20);
        saveNameTextField.setMaximumSize(saveNameTextField.getPreferredSize());

        pleaseSelectDifficultyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pleaseSelectDifficultyLabel.setFont(FontPresets.H2BOLDFONT);

        topBar.add(backButton, BorderLayout.WEST);
        contentPanel.add(newGameTitle);
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(saveNameTextField);
        contentPanel.add(Box.createVerticalStrut(25));
        contentPanel.add(pleaseSelectDifficultyLabel);

        add(topBar, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
    }

    public void addBackButtonActionListener(ActionListener l) {
        backButton.addActionListener(l);
    }
}
