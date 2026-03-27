package com.lnw.acceeeeeppt.scene.level.level2_term_of_investment;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class InvestController {
    private Random random;
    private InvestModel model;
    private InvestView view;
    private InvestIntegration integration;

    public InvestController(InvestModel model, InvestView view, InvestIntegration integration) {
        random = new Random();
        this.model = model;
        this.view = view;
        this.integration = integration;

        view.updateMoneyDisplay(model.getPlayerMoney());
        initController();
    }

    private void initController() {
        view.btnBuyAccept.addActionListener(e -> handleBuyAccept());

        view.btnAccept.addActionListener(e -> handleAccept());

        view.btnGoBack.addActionListener(e -> handleGoBackTerm());

        view.btnSpin.addActionListener(e -> handleSpin());

        view.btnBackToTerm.addActionListener(e -> handleGoBackSlot());

        view.chkAccept.addItemListener(e -> view.btnAccept.setEnabled(view.chkAccept.isSelected()));

        view.lblInvestLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.cardLayout.show(view.mainCardPanel, "SLOT");
            }
        });
    }

    private void handleBuyAccept() {
        if (model.deductMoney(100)) {
            view.updateMoneyDisplay(model.getPlayerMoney());
            view.btnBuyAccept.setVisible(false);
            view.btnAccept.setVisible(true);
            view.chkAccept.setVisible(true);

            CardLayout cardLayout = (CardLayout) view.textSwitcherPanel.getLayout();
            cardLayout.show(view.textSwitcherPanel, "CHECKBOX_MODE");

            view.lblWarning.setText("*Please check the box to enable Accept button");
            view.lblWarning.setForeground(new Color(0, 150, 0));
        } else {
            flashRedButton(view.btnBuyAccept);
        }
    }

    private void handleAccept() {
        JOptionPane.showMessageDialog(view, "You accepted the terms of investment.", "Good luck",
                JOptionPane.INFORMATION_MESSAGE);
        integration.unlockNextStage();
    }

    private void handleGoBackTerm() {
        if (model.deductMoney(5)) {
            view.updateMoneyDisplay(model.getPlayerMoney());
            JOptionPane.showMessageDialog(view, "Go back successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            integration.navigateToMenu();
        } else {
            JOptionPane.showMessageDialog(view, "You don't have enought money lil bro.", "LMFAO",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void handleGoBackSlot() {
        if (model.deductMoney(5)) {
            view.updateMoneyDisplay(model.getPlayerMoney());
            JOptionPane.showMessageDialog(view, "Back button isn't free. (-$5)", "HAHAHA",
                    JOptionPane.INFORMATION_MESSAGE);
            view.cardLayout.show(view.mainCardPanel, "TERM");
        } else {
            JOptionPane.showMessageDialog(view, "I hope you have enough money to buy accept.", "xDDDD",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void handleSpin() {
        int betAmount = 0;
        try {
            betAmount = Integer.parseInt(view.txtBetAmount.getText());
        } catch (NumberFormatException _) {
            JOptionPane.showMessageDialog(view, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (betAmount <= 0) {
            JOptionPane.showMessageDialog(view, "Investment must be more than $0", "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (model.deductMoney(betAmount)) {
            view.updateMoneyDisplay(model.getPlayerMoney());
            int r1 = random.nextInt(7) + 1;
            int r2 = random.nextInt(7) + 1;
            int r3 = random.nextInt(7) + 1;

            view.lblSlot1.setText(String.valueOf(r1));
            view.lblSlot2.setText(String.valueOf(r2));
            view.lblSlot3.setText(String.valueOf(r3));

            calculatePrize(betAmount, r1, r2, r3);

        } else {
            JOptionPane.showMessageDialog(view, "Not enough money for this investment!", "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void calculatePrize(int betAmount, int n1, int n2, int n3) {
        int prize = 0;
        String message = "";

        if (n1 == 7 && n2 == 7 && n3 == 7) {
            model.addMoney(betAmount, 50);
            prize = betAmount * 50;
            message = "Wow you got 777. You won $" + prize + ".";
        } else if (n1 == n2 && n2 == n3) {
            model.addMoney(betAmount, 10);
            prize = betAmount * 10;
            message = "Oh baby a TRIPLE! You won $" + prize + ".";
        } else if (n1 == n2 || n2 == n3 || n1 == n3) {
            model.addMoney(betAmount, 2);
            prize = betAmount * 2;
            message = "Meh. Just a pair. You won $" + prize + "";
        } else if (n1 == 7 || n2 == 7 || n3 == 7) {
            model.addMoney(betAmount, 1);
            prize = betAmount * 1;
            message = "So lucky. You got your $" + prize + " back.";
        }

        if (prize > 0) {
            view.updateMoneyDisplay(model.getPlayerMoney());
            JOptionPane.showMessageDialog(view, message, "Congratulations", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void flashRedButton(JButton btn) {
        Color oldBg = btn.getBackground();
        Color oldFg = btn.getForeground();
        btn.setBackground(Color.RED);
        btn.setForeground(Color.WHITE);

        Timer timer = new Timer(500, e -> {
            btn.setBackground(oldBg);
            btn.setForeground(oldFg);
        });
        timer.setRepeats(false);
        timer.start();
    }
}
