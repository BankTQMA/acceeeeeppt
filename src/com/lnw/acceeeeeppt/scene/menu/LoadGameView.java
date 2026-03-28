package com.lnw.acceeeeeppt.scene.menu;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.lnw.acceeeeeppt.ui.FontPresets;
import com.lnw.acceeeeeppt.ui.MarginConstants;

public class LoadGameView extends JPanel {
    private JPanel topBar;
    private JPanel contentPanel;
    private JPanel bottomBar;

    private JButton backButton;

    public LoadGameView() {
        // Container initialisation
        topBar = new JPanel();
        contentPanel = new JPanel();
        bottomBar = new JPanel();

        // Container Configuration
        setLayout(new BorderLayout());
        topBar.setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(MarginConstants.GLOBALMARGIN, MarginConstants.GLOBALMARGIN,
                MarginConstants.GLOBALMARGIN, MarginConstants.GLOBALMARGIN));
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        bottomBar.setLayout(new FlowLayout(FlowLayout.RIGHT));

        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setFont(FontPresets.H2BOLDFONT);
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        topBar.add(backButton, BorderLayout.WEST);

        add(topBar, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(bottomBar, BorderLayout.SOUTH);
    }
}
