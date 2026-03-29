package com.lnw.acceeeeeppt.scene.level.level9_idle_game;

import javax.swing.*;
import java.awt.*;

class Level9IdleGameView extends JPanel implements Runnable {
    private Level9IdleModel game;
    private JLabel pointsLbl, incomeLbl, timerLbl;
    private JButton upg1, upg2, upg3, keyBtn;
    private volatile boolean running = true;

    public Level9IdleGameView(Level9IdleModel game) {
        this.game = game;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel topPanel = new JPanel(new GridLayout(1, 3));
        pointsLbl = new JLabel(" Agree Points : 0");
        incomeLbl = new JLabel(" Income/Sec : 0");
        timerLbl = new JLabel("Time : 02:30 ", SwingConstants.RIGHT);
        topPanel.add(pointsLbl);
        topPanel.add(incomeLbl);
        topPanel.add(timerLbl);

        JButton generatorBtn = new JButton("Agree Points Generator");
        generatorBtn.setBackground(new Color(190, 255, 190));
        generatorBtn.setFont(new Font("Arial", Font.BOLD, 14));
        generatorBtn.addActionListener(e -> {
            game.points++;
            updateLabels();
        });

        JPanel upgradePanel = new JPanel(new GridLayout(4, 1, 5, 5));
        upg1 = Level9IdleHelperClass.createButton("Clicker", game.upg1Cost, "1 Point/sec");
        upg2 = Level9IdleHelperClass.createButton("Upgrade Clicker", game.upg2Cost, "5 Points/sec");
        upg3 = Level9IdleHelperClass.createButton("Mega Clicker", game.upg3Cost, "10 Points/sec");
        keyBtn = Level9IdleHelperClass.createButton("Master Key", game.keyCost, "Unlock Agree Button");

        upg1.addActionListener(e -> purchase(1, 1, upg1, "Clicker", "1 Point/sec"));
        upg2.addActionListener(e -> purchase(5, 2, upg2, "Upgrade Clicker", "5 Points/sec"));
        upg3.addActionListener(e -> purchase(10, 3, upg3, "Mega Clicker", "10 Points/sec"));

        keyBtn.addActionListener(e -> {
            if (game.points >= game.keyCost) {
                game.points -= game.keyCost;
                game.tosUnlocked = true;
                if (game.agreeButtonRef != null)
                    game.agreeButtonRef.setEnabled(true);
                keyBtn.setEnabled(false);
                updateLabels();
            }
        });

        upgradePanel.add(upg1);
        upgradePanel.add(upg2);
        upgradePanel.add(upg3);
        upgradePanel.add(keyBtn);

        add(topPanel, BorderLayout.NORTH);
        add(generatorBtn, BorderLayout.CENTER);
        add(upgradePanel, BorderLayout.EAST);

        new Thread(this).start();
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(1000);

                if (game.timeLeft > 0) {
                    game.points += game.incomePerSecond;
                    game.timeLeft--;

                    SwingUtilities.invokeLater(() -> {
                        updateLabels();
                        if (game.timeLeft <= 0) {
                            JOptionPane.showMessageDialog(this, "Time's up! Resetting level...");
                            resetGame();
                        }
                    });
                }
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    private void resetGame() {
        game.points = 0;
        game.incomePerSecond = 0;
        game.timeLeft = 150;
        game.tosUnlocked = false;
        game.upg1Cost = 10;
        game.upg2Cost = 50;
        game.upg3Cost = 200;

        if (game.agreeButtonRef != null) {
            game.agreeButtonRef.setEnabled(false);
        }

        keyBtn.setEnabled(true);
        Level9IdleHelperClass.updateButton(upg1, "Clicker", game.upg1Cost, "1 Point/sec");
        Level9IdleHelperClass.updateButton(upg2, "Upgrade Clicker", game.upg2Cost, "5 Points/sec");
        Level9IdleHelperClass.updateButton(upg3, "Mega Clicker", game.upg3Cost, "10 Points/sec");
        Level9IdleHelperClass.updateButton(keyBtn, "Master Key", game.keyCost, "Unlock Agree Button");

        updateLabels();
    }

    private void purchase(int addInc, int type, JButton btn, String name, String desc) {
        int cost = (type == 1) ? game.upg1Cost : (type == 2) ? game.upg2Cost : game.upg3Cost;
        if (game.points >= cost) {
            game.points -= cost;
            game.incomePerSecond += addInc;
            int next = (int) (cost * 1.2);
            if (type == 1)
                game.upg1Cost = next;
            else if (type == 2)
                game.upg2Cost = next;
            else
                game.upg3Cost = next;
            Level9IdleHelperClass.updateButton(btn, name, next, desc);
            updateLabels();
        }
    }

    private void updateLabels() {
        pointsLbl.setText(" Agree Points : " + game.points);
        incomeLbl.setText(" Income/Sec : " + game.incomePerSecond);
        timerLbl.setText(String.format("Time : %02d:%02d ", game.timeLeft / 60, game.timeLeft % 60));
    }
}
