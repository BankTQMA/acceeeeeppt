package com.lnw.acceeeeeppt.scene.menu;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.lnw.acceeeeeppt.ui.FontPresets;
import com.lnw.acceeeeeppt.ui.MarginConstants;
import com.lnw.acceeeeeppt.ui.SceneConstants;

public class NewGameView extends JPanel {
    private JPanel topBar;
    private JPanel contentPanel;
    private JButton backButton;
    private JLabel newGameTitle;

    public NewGameView() {
        topBar = new JPanel();
        contentPanel = new JPanel();

        backButton = new JButton("< Back");

        newGameTitle = new JLabel(SceneConstants.NEWGAMEMENU);

        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setFont(FontPresets.H2BOLDFONT);
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        setLayout(new BorderLayout());
        topBar.setLayout(new BorderLayout());
        topBar.setBorder(BorderFactory.createEmptyBorder(MarginConstants.GLOBALMARGIN, MarginConstants.GLOBALMARGIN,
                MarginConstants.GLOBALMARGIN, MarginConstants.GLOBALMARGIN));
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        newGameTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        newGameTitle.setFont(FontPresets.H1BOLDFONT);

        topBar.add(backButton, BorderLayout.WEST);
        contentPanel.add(newGameTitle);

        add(topBar, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
    }

    public void addBackButtonActionListener(ActionListener l) {
        backButton.addActionListener(l);
    }
}
