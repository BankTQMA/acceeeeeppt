package com.lnw.acceeeeeppt.scene.menu;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
    private final String standardDifficultyDescription;
    private final String hardcoreDifficultyDescription;
    private JPanel topBar;
    private JPanel contentPanel;
    private JPanel difficultyButtonPanel;
    private JPanel bottomBar;
    private JButton backButton;
    private JLabel newGameTitle;
    private JTextField saveNameTextField;
    private JLabel pleaseSelectDifficultyLabel;
    private JButton standardDifficultyButton;
    private JButton hardcoreDifficultyButton;
    private JLabel difficultyDesciptionLabel;
    private JButton acceptButton;
    private JLabel madeWithHeartLabel;

    public NewGameView() {
        // Container initialisation
        topBar = new JPanel();
        contentPanel = new JPanel();
        difficultyButtonPanel = new JPanel();
        bottomBar = new JPanel();

        // Component initiallisation
        standardDifficultyDescription = "Standard Difficulty Description Placeholder Text";
        hardcoreDifficultyDescription = "Hardcore Difficulty Description Placeholder Text";
        backButton = new JButton("< Back");
        newGameTitle = new JLabel(SceneConstants.NEWGAMEMENU);
        saveNameTextField = new JTextField("Untitled Savefile");
        pleaseSelectDifficultyLabel = new JLabel("Please Select Difficulty");
        standardDifficultyButton = new JButton("Standard");
        hardcoreDifficultyButton = new JButton("Hardcore");
        difficultyDesciptionLabel = new JLabel(standardDifficultyDescription);
        acceptButton = new JButton("Acceeeeeppt");
        madeWithHeartLabel = new JLabel("Made with ♥ for your potato PC.");

        // Container Configuration
        setLayout(new BorderLayout());
        topBar.setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(MarginConstants.GLOBALMARGIN, MarginConstants.GLOBALMARGIN,
                MarginConstants.GLOBALMARGIN, MarginConstants.GLOBALMARGIN));
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        difficultyButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomBar.setLayout(new FlowLayout(FlowLayout.RIGHT));

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

        standardDifficultyButton.setFont(FontPresets.H3PLAINFONT);
        standardDifficultyButton.setEnabled(false);
        hardcoreDifficultyButton.setFont(FontPresets.H3PLAINFONT);

        difficultyButtonPanel.add(standardDifficultyButton);
        difficultyButtonPanel.add(Box.createVerticalStrut(10));
        difficultyButtonPanel.add(hardcoreDifficultyButton);
        difficultyButtonPanel.setMaximumSize(difficultyButtonPanel.getPreferredSize());

        difficultyDesciptionLabel.setFont(FontPresets.REGULARPLAINFONT);
        difficultyDesciptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        acceptButton.setFont(FontPresets.H1BOLDFONT);
        acceptButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        acceptButton.setMaximumSize(
                new Dimension(acceptButton.getPreferredSize().width + 25, acceptButton.getPreferredSize().height));

        topBar.add(backButton, BorderLayout.WEST);

        contentPanel.add(Box.createVerticalStrut(50));
        contentPanel.add(newGameTitle);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(saveNameTextField);
        contentPanel.add(Box.createVerticalStrut(40));
        contentPanel.add(pleaseSelectDifficultyLabel);
        contentPanel.add(Box.createVerticalStrut(5));
        contentPanel.add(difficultyButtonPanel);
        contentPanel.add(Box.createVerticalStrut(5));
        contentPanel.add(difficultyDesciptionLabel);
        contentPanel.add(Box.createVerticalStrut(50));
        contentPanel.add(acceptButton);

        bottomBar.add(madeWithHeartLabel);

        add(topBar, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(bottomBar, BorderLayout.SOUTH);
    }

    public void addBackButtonActionListener(ActionListener l) {
        backButton.addActionListener(l);
    }

    public void addStandardButtonActionListener(ActionListener l) {
        standardDifficultyButton.addActionListener(l);
    }

    public void addHardcoreButtonActionListener(ActionListener l) {
        hardcoreDifficultyButton.addActionListener(l);
    }
}
