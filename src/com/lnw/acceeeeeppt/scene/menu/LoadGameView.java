package com.lnw.acceeeeeppt.scene.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.lnw.acceeeeeppt.ui.FontPresets;
import com.lnw.acceeeeeppt.ui.MarginConstants;

public class LoadGameView extends JPanel {
    private JPanel topBar;
    private JPanel contentPanel;
    private JPanel bottomBar;
    private JScrollPane scrollPane;

    private JButton backButton;
    private JButton renameButton;
    private JButton deleteButton;
    private JButton loadButton;

    public LoadGameView() {
        // Container initialisation
        topBar = new JPanel();
        contentPanel = new JPanel();
        bottomBar = new JPanel();
        scrollPane = new JScrollPane(contentPanel);

        // Container Configuration
        setLayout(new BorderLayout());
        topBar.setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(MarginConstants.GLOBALMARGIN, MarginConstants.GLOBALMARGIN,
                MarginConstants.GLOBALMARGIN, MarginConstants.GLOBALMARGIN));

        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Smoother scrolling
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, MarginConstants.GLOBALMARGIN * 2, 0,
                MarginConstants.GLOBALMARGIN * 2));

        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        bottomBar.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        backButton = new JButton("< Back");
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setFont(FontPresets.H2BOLDFONT);
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel titleLabel = new JLabel("Load", SwingConstants.CENTER);
        titleLabel.setFont(FontPresets.H1BOLDFONT);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        topBar.add(backButton, BorderLayout.WEST);
        contentPanel.add(titleLabel, BorderLayout.CENTER);
        contentPanel.add(Box.createVerticalStrut(10));

        renameButton = createActionButton("Rename", false);
        deleteButton = createActionButton("Delete", true);
        loadButton = createActionButton("Load", true);

        bottomBar.add(renameButton);
        bottomBar.add(deleteButton);
        bottomBar.add(loadButton);

        add(topBar, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomBar, BorderLayout.SOUTH);
    }

    private JPanel createSaveSlot(String worldName, String date, String level) {
        JPanel slot = new JPanel(new BorderLayout(10, 10));
        slot.setBackground(Color.WHITE);

        slot.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(30, 50, 100), 2),
                new EmptyBorder(10, 15, 10, 15)));

        slot.setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));

        JPanel leftInfo = new JPanel(new GridLayout(2, 1, 0, 5));
        leftInfo.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel(worldName);
        nameLabel.setFont(FontPresets.H2BOLDFONT);

        JLabel dateLabel = new JLabel("Last accessed: " + date);

        leftInfo.add(nameLabel);
        leftInfo.add(dateLabel);

        JLabel lvlLabel = new JLabel(level, SwingConstants.CENTER);
        lvlLabel.setOpaque(true);
        lvlLabel.setBackground(Color.LIGHT_GRAY);
        lvlLabel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.DARK_GRAY, 2),
                new EmptyBorder(10, 20, 10, 20)));

        slot.add(leftInfo, BorderLayout.CENTER);
        slot.add(lvlLabel, BorderLayout.EAST);

        return slot;
    }

    private JButton createActionButton(String text, boolean longButton) {
        int width = longButton ? 200 : 130;
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(width, 45));
        button.setOpaque(true);
        button.setBorder(new LineBorder(Color.DARK_GRAY, 1));
        button.setFont(FontPresets.H2BOLDFONT);
        return button;
    }

    public void addBackButtonActionListener(ActionListener l) {
        backButton.addActionListener(l);
    }

    public void addRenameButtonActionListener(ActionListener l) {
        renameButton.addActionListener(l);
    }

    public void addDeleteButtonActionListener(ActionListener l) {
        deleteButton.addActionListener(l);
    }

    public void addLoadButtonActionListener(ActionListener l) {
        loadButton.addActionListener(l);
    }

}
