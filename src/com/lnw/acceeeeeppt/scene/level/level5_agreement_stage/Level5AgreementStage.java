package com.lnw.acceeeeeppt.scene.level.level5_agreement_stage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Level5AgreementStage extends JFrame {

    private int targetClicks;
    private double currentClicks = 0;
    private JProgressBar progressBar;
    private JLabel percentLabel;
    private boolean completed = false;
    private Random random = new Random();

    private JPanel gridPanel;
    private int lastAgreeRow = -1;
    private int lastAgreeCol = -1;

    private static final int COLS = 4;
    private static final int ROWS = 3;
    private static final int BTN_W = 148;
    private static final int BTN_H = 75;
    private static final int GAP = 6;
    private static final int GRID_W = COLS * BTN_W + (COLS - 1) * GAP;
    private static final int GRID_H = ROWS * BTN_H + (ROWS - 1) * GAP;

    private Timer idleTimer;

    public Level5AgreementStage() {
        targetClicks = 10 + random.nextInt(4);

        setTitle("Do you agree to the Terms of Use [Article 1]?");
        setSize(680, 510);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout(0, 10));

        JPanel topWrapper = new JPanel(new BorderLayout());
        topWrapper.setBackground(Color.WHITE);
        topWrapper.setBorder(BorderFactory.createEmptyBorder(15, 20, 5, 20));

        JPanel textBox = new JPanel(new BorderLayout(0, 6));
        textBox.setBackground(Color.WHITE);
        textBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
                BorderFactory.createEmptyBorder(8, 15, 10, 15)));

        JPanel topRow = new JPanel(new BorderLayout(8, 0));
        topRow.setBackground(Color.WHITE);

        JLabel subLabel = new JLabel("About addictiveness");
        subLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        subLabel.setForeground(new Color(130, 130, 130));

        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(false);
        progressBar.setPreferredSize(new Dimension(160, 14));
        progressBar.setForeground(new Color(220, 40, 40));
        progressBar.setBackground(new Color(210, 210, 210));
        progressBar.setBorderPainted(true);

        percentLabel = new JLabel("0%");
        percentLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        percentLabel.setForeground(new Color(80, 80, 80));
        percentLabel.setPreferredSize(new Dimension(32, 14));
        percentLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        JPanel barPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 4, 0));
        barPanel.setBackground(Color.WHITE);
        barPanel.add(progressBar);
        barPanel.add(percentLabel);

        topRow.add(subLabel, BorderLayout.WEST);
        topRow.add(barPanel, BorderLayout.EAST);

        String content = "(1) The developer shall not be held responsible in anyway if a user becomes " +
                "too absorbed in this game and it interfaces with their work, studies, or social life.";
        JTextArea textArea = new JTextArea(content);
        textArea.setFont(new Font("Arial", Font.BOLD, 14));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setBackground(Color.WHITE);
        textArea.setForeground(Color.BLACK);
        textArea.setOpaque(false);

        textBox.add(topRow, BorderLayout.NORTH);
        textBox.add(textArea, BorderLayout.CENTER);
        topWrapper.add(textBox, BorderLayout.CENTER);
        add(topWrapper, BorderLayout.NORTH);

        gridPanel = new JPanel(null);
        gridPanel.setBackground(Color.WHITE);
        gridPanel.setPreferredSize(new Dimension(GRID_W + 40, GRID_H + 30));

        JPanel gridWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        gridWrapper.setBackground(Color.WHITE);
        gridWrapper.add(gridPanel);

        buildButtons();
        add(gridWrapper, BorderLayout.CENTER);

        idleTimer = new Timer(500, e -> applyIdleDecay());
        idleTimer.start();
    }

    private void applyIdleDecay() {
        if (completed || currentClicks <= 0)
            return;

        double currentPct = currentClicks / targetClicks;

        double decayPct = 0.01 + (0.14 * Math.pow(currentPct, 2));

        double decay = decayPct * targetClicks;
        currentClicks = Math.max(0, currentClicks - decay);
        updateProgress();
    }

    private void buildButtons() {
        gridPanel.removeAll();

        int newRow;
        int newCol;

        do {
            newRow = random.nextInt(ROWS);
            newCol = random.nextInt(COLS);
        } while (isTooClose(newRow, newCol));

        lastAgreeRow = newRow;
        lastAgreeCol = newCol;

        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                boolean isAgree = (r == newRow && c == newCol);
                JButton btn = isAgree
                        ? createStyledButton("Agree", new Color(60, 180, 60), new Color(30, 140, 30))
                        : createStyledButton("Disagree", new Color(220, 40, 40), new Color(170, 20, 20));

                int x = 20 + c * (BTN_W + GAP);
                int y = 15 + r * (BTN_H + GAP);
                btn.setBounds(x, y, BTN_W, BTN_H);

                if (isAgree) {
                    btn.addActionListener(e -> onAgree());
                } else {
                    btn.addActionListener(e -> onDisagree());
                }

                gridPanel.add(btn);
            }
        }

        gridPanel.revalidate();
        gridPanel.repaint();
    }

    private boolean isTooClose(int row, int col) {
        if (lastAgreeRow == -1)
            return false;
        int dr = Math.abs(row - lastAgreeRow);
        int dc = Math.abs(col - lastAgreeCol);
        return (dr + dc) < 3;
    }

    private void onAgree() {
        if (completed)
            return;

        currentClicks = Math.min(targetClicks, currentClicks + 1.5);
        updateProgress();

        if (currentClicks >= targetClicks) {
            completed = true;
            idleTimer.stop();
            JOptionPane.showMessageDialog(this, "Good Job! Let's Go To The Next Stage");
            System.exit(0);
        } else {
            buildButtons();
        }
    }

    private void onDisagree() {
        double decreasePct = 0.10 + (random.nextDouble() * 0.05);
        currentClicks = Math.max(0, currentClicks - decreasePct * targetClicks);
        updateProgress();
        JOptionPane.showMessageDialog(this, "You have to AGREE! Do It Again.", "WRONG!", JOptionPane.ERROR_MESSAGE);
        buildButtons();
    }

    private void updateProgress() {
        int percent = (int) Math.round((currentClicks / targetClicks) * 100);
        percent = Math.max(0, Math.min(100, percent));
        progressBar.setValue(percent);
        percentLabel.setText(percent + "%");

        if (percent < 40)
            progressBar.setForeground(new Color(220, 40, 40));
        else if (percent < 70)
            progressBar.setForeground(new Color(230, 150, 0));
        else
            progressBar.setForeground(new Color(60, 180, 60));
    }

    private JButton createStyledButton(String text, Color topColor, Color bottomColor) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setPaint(new GradientPaint(0, 0, topColor, 0, getHeight(), bottomColor));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Level5AgreementStage().setVisible(true));
    }
}
