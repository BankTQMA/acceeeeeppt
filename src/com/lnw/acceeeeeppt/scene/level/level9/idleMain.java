package com.lnw.acceeeeeppt.scene.level.level9;

import javax.swing.*;
import java.awt.*;

public class idleMain {

    private startStat game = new startStat();

    private JLabel pointsLbl;
    private JLabel incomeLbl;
    private JLabel timerLbl;

    public idleMain() {
        JFrame frame = new JFrame("Idle Game");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10,10));

        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel leftTop = new JPanel(new GridLayout(2,1));

        pointsLbl = new JLabel(" Agree Points : 0");
        pointsLbl.setFont(new Font("Arial", Font.BOLD, 22));

        incomeLbl = new JLabel(" Income/Sec : 0");

        leftTop.add(pointsLbl);
        leftTop.add(incomeLbl);

        timerLbl = new JLabel("Time : 03:00 ", SwingConstants.RIGHT);
        timerLbl.setFont(new Font("Arial", Font.BOLD, 16));

        topPanel.add(leftTop, BorderLayout.WEST);
        topPanel.add(timerLbl, BorderLayout.EAST);

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 90));
        JButton generatorBtn = new JButton("Agree Points Generator");
        generatorBtn.setPreferredSize(new Dimension(250,150));
        generatorBtn.setBackground(Color.GREEN);

        generatorBtn.addActionListener(e -> {
            game.points += 1;
            updateLabels();
        });

        leftPanel.add(generatorBtn);

        JPanel upgradePanel = new JPanel(new GridLayout(4,1,10,10));
        upgradePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JButton upg1Btn = idleUI.createButton("Clicker", game.upg1Cost, "Give 1 Agree Points/sec");
        JButton upg2Btn = idleUI.createButton("Upgrade Clicker", game.upg2Cost, "Give 5 Agree Points/sec");
        JButton upg3Btn = idleUI.createButton("Another Upgrade Clicker", game.upg3Cost, "Give 10 Agree Points/sec");
        JButton keyBtn = idleUI.createButton("Master Key", game.keyCost, "Unlock Agree Button");

        JButton finalAgreeBtn = new JButton("FINAL AGREE");
        finalAgreeBtn.setEnabled(false);

        upg1Btn.addActionListener(e -> {
            if(game.points >= game.upg1Cost){
                game.points -= game.upg1Cost;
                game.incomePerSecond += 1;
                game.upg1Cost = (int)(game.upg1Cost * 1.2);

                idleUI.updateButton(upg1Btn, "Clicker", game.upg1Cost, "Give 1 Agree Points/sec");
                updateLabels();
            }
        });

        upg2Btn.addActionListener(e -> {
            if(game.points >= game.upg2Cost){
                game.points -= game.upg2Cost;
                game.incomePerSecond += 5;
                game.upg2Cost = (int)(game.upg2Cost * 1.2);

                idleUI.updateButton(upg2Btn, "Upgrade Clicker", game.upg2Cost, "Give 5 Agree Points/sec");
                updateLabels();
            }
        });

        upg3Btn.addActionListener(e -> {
            if(game.points >= game.upg3Cost){
                game.points -= game.upg3Cost;
                game.incomePerSecond += 10;
                game.upg3Cost = (int)(game.upg3Cost * 1.2);

                idleUI.updateButton(upg3Btn, "Another Upgrade Clicker", game.upg3Cost, "Give 10 Agree Points/sec");
                updateLabels();
            }
        });

        keyBtn.addActionListener(e -> {
            if(game.points >= game.keyCost){
                game.points -= game.keyCost;
                finalAgreeBtn.setEnabled(true);
                keyBtn.setEnabled(false);
                updateLabels();
            }
        });

        finalAgreeBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "You win!");
            System.exit(0);
        });

        upgradePanel.add(upg1Btn);
        upgradePanel.add(upg2Btn);
        upgradePanel.add(upg3Btn);
        upgradePanel.add(keyBtn);

        JPanel rightWrapper = new JPanel(new GridBagLayout());
        rightWrapper.add(upgradePanel);

        JPanel mainPanel = new JPanel(new BorderLayout(10,10));
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(15,15,15,15),
                BorderFactory.createLineBorder(Color.BLACK, 3)
        ));

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(leftPanel, BorderLayout.CENTER);
        mainPanel.add(rightWrapper, BorderLayout.EAST);

        frame.add(mainPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        finalAgreeBtn.setPreferredSize(new Dimension(150,40));
        bottomPanel.add(finalAgreeBtn);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        Timer timer = new Timer(1000, e -> {
            game.points += game.incomePerSecond;
            game.timeLeft--;

            int m = game.timeLeft / 60;
            int s = game.timeLeft % 60;

            timerLbl.setText(String.format("Time : %02d:%02d ", m, s));

            if(game.timeLeft <= 0){
                ((Timer)e.getSource()).stop();
                JOptionPane.showMessageDialog(frame, "Time's up! You lose.");
                System.exit(0);
            }

            updateLabels();
        });

        timer.start();
        frame.setVisible(true);
    }

    private void updateLabels(){
        pointsLbl.setText(" Agree Points : " + game.points);
        incomeLbl.setText(" Income/Sec : " + game.incomePerSecond);
    }

    public static void main(String[] args) {
        new idleMain();
    }
}