package com.lnw.acceeeeeppt.scene.level.level7_stepping_stones;

import java.awt.*;
import javax.swing.*;

public class Level7SteppingStonesView extends JFrame {

    private final Level7SteppingStonesModel logic;
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel mainPanel = new JPanel(cardLayout);
    private JLabel lblStage;
    private JLabel lblQuote;
    private JLabel lblStats;
    private JLabel lblFinalStats;

    public Level7SteppingStonesView(Level7SteppingStonesModel logic) {
        this.logic = logic;
        setTitle("Stepping Stones");
        setSize(460, 315);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel.add(createStartPanel(), "START");
        mainPanel.add(createGamePanel(), "GAME");
        mainPanel.add(createWinPanel(), "WIN");

        add(mainPanel);
        setVisible(true);
    }

    private JPanel createStartPanel() {
        JPanel p = new JPanel(new BorderLayout(15, 15));
        p.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JLabel title = new JLabel("STEPPING STONES", SwingConstants.CENTER);
        title.setFont(new Font("Monospaced", Font.BOLD, 28));

        String rules = "<html><body style='width: 300px;'>"
                + "<b>Instructions:</b><br>"
                + "- Choose 1 of 3 paths. Only 1 is correct.<br>"
                + "- Survive 4 stages in a row to win.<br>"
                + "- 1 wrong move = Start over from Stage 1.<br>"
                + "- Paths are random every time!"
                + "</body></html>";
        JLabel lblRules = new JLabel(rules);
        lblRules.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JButton btnStart = new JButton("START");
        btnStart.addActionListener(e -> {
            logic.startGame();
            updateGameScreen();
            cardLayout.show(mainPanel, "GAME");
        });

        p.add(title, BorderLayout.NORTH);
        p.add(lblRules, BorderLayout.CENTER);
        p.add(btnStart, BorderLayout.SOUTH);
        return p;
    }

    private JPanel createGamePanel() {
        JPanel p = new JPanel(new BorderLayout(10, 10));
        p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel header = new JPanel(new GridLayout(2, 1));
        lblStage = new JLabel("STAGE 1", SwingConstants.CENTER);
        lblStage.setFont(new Font("Monospaced", Font.BOLD, 36));
        lblQuote = new JLabel(logic.getQuote(), SwingConstants.CENTER);
        header.add(lblStage);
        header.add(lblQuote);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        String[] labels = { "AC", "CE", "PT" };
        for (int i = 0; i < 3; i++) {
            final int idx = i;
            JButton b = new JButton(labels[i]);
            b.setPreferredSize(new Dimension(85, 50));
            b.addActionListener(e -> {
                logic.checkChoice(idx);
                if (logic.getCurrentScreen() == Level7SteppingStonesModel.Screen.WIN) {
                    lblFinalStats.setText("Total Failures: " + logic.getFailCount());
                    cardLayout.show(mainPanel, "WIN");
                } else {
                    updateGameScreen();
                }
            });
            btnPanel.add(b);
        }

        lblStats = new JLabel("Failed Count : 0", SwingConstants.CENTER);
        p.add(header, BorderLayout.NORTH);
        p.add(btnPanel, BorderLayout.CENTER);
        p.add(lblStats, BorderLayout.SOUTH);
        return p;
    }

    private JPanel createWinPanel() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel win = new JLabel("Congratulations!", SwingConstants.CENTER);
        win.setFont(new Font("Monospaced", Font.BOLD, 30));

        lblFinalStats = new JLabel("", SwingConstants.CENTER);
        lblFinalStats.setFont(new Font("Monospaced", Font.PLAIN, 18));

        JButton btnAccept = new JButton("ACCEPT");
        btnAccept.addActionListener(e -> cardLayout.show(mainPanel, "START"));

        p.add(win, gbc);
        p.add(lblFinalStats, gbc);
        p.add(btnAccept, gbc);
        return p;
    }

    private void updateGameScreen() {
        lblStage.setText("STAGE " + logic.getStage());
        lblQuote.setText(logic.getQuote());
        lblStats.setText("Failed Count : " + logic.getFailCount());
    }
}
