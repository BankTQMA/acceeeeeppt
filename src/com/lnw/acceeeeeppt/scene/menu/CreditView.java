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

public class CreditView extends JPanel {
    private JPanel topBar;
    private JPanel contentPanel;
    private JButton backButton;
    private JLabel titleLabel;

    public CreditView() {
        // Container initialisation
        topBar = new JPanel();
        contentPanel = new JPanel();

        // Container configuration
        setLayout(new BorderLayout());
        topBar.setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(MarginConstants.GLOBALMARGIN, MarginConstants.GLOBALMARGIN,
                MarginConstants.GLOBALMARGIN, MarginConstants.GLOBALMARGIN));
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        backButton = new JButton("< Back");
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setFont(FontPresets.H2BOLDFONT);
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        titleLabel = new JLabel(SceneConstants.CREDITMENU);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(FontPresets.H1BOLDFONT);

        topBar.add(backButton, BorderLayout.WEST);

        contentPanel.add(titleLabel);

        add(topBar, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
    }

    public void addBackButtonActionListener(ActionListener l) {
        backButton.addActionListener(l);
    }
}
