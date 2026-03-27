package Invest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InvestController {
    private InvestModel model;
    private InvestView view;
    private InvestIntegration integration;
    private boolean isSpinning = false;

    public InvestController(InvestModel model, InvestView view, InvestIntegration integration) {
        this.model = model;
        this.view = view;
        this.integration = integration;

        view.updateMoneyDisplay(model.getPlayerMoney());
        initController();
    }

    private void initController() {
        view.btnBuyAccept.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleBuyAccept();
            }
        });

        view.btnAccept.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleAccept();
            }
        });

        view.btnGoBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleGoBackTerm();
            }
        });

        view.btnSpin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleSpin();
            }
        });

        view.btnBackToTerm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleGoBackSlot();
            }
        });

        view.chkAccept.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (view.chkAccept.isSelected() == true) {
                    view.btnAccept.setEnabled(true);
                } else {
                    view.btnAccept.setEnabled(false);
                }
            }
        });

        view.lblInvestLink.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                CardLayout cardLayout = (CardLayout) view.mainCardPanel.getLayout();
                cardLayout.show(view.mainCardPanel, "SLOT");
            }
        });
    }

    private void handleBuyAccept() {
        if (model.deductMoney(100) == true) {
            view.updateMoneyDisplay(model.getPlayerMoney());
            view.btnBuyAccept.setVisible(false);
            view.btnAccept.setVisible(true);
            view.chkAccept.setVisible(true);

            CardLayout cardLayout = (CardLayout) view.textSwitcherPanel.getLayout();
            cardLayout.show(view.textSwitcherPanel, "CHECKBOX_MODE");

            view.lblWarning.setText("*Please check the box to enable Accept button");
            view.lblWarning.setForeground(new Color(0, 150, 0));
        } else {
            FlashThread ft = new FlashThread(view.btnBuyAccept);
            ft.start();
        }
    }

    private void handleAccept() {
        JOptionPane.showMessageDialog(view, "You accepted the terms of investment.", "Good luck",
                JOptionPane.INFORMATION_MESSAGE);
        integration.unlockNextStage();
    }

    private void handleGoBackTerm() {
        if (model.deductMoney(5) == true) {
            view.updateMoneyDisplay(model.getPlayerMoney());
            JOptionPane.showMessageDialog(view, "Go back successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            integration.navigateToMenu();
        } else {
            JOptionPane.showMessageDialog(view, "You don't have enought money lil bro.", "LMFAO",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void handleGoBackSlot() {
        if (model.deductMoney(5) == true) {
            view.updateMoneyDisplay(model.getPlayerMoney());
            JOptionPane.showMessageDialog(view, "Back button isn't free. (-$5)", "HAHAHA",
                    JOptionPane.INFORMATION_MESSAGE);

            CardLayout cardLayout = (CardLayout) view.mainCardPanel.getLayout();
            cardLayout.show(view.mainCardPanel, "TERM");
        } else {
            JOptionPane.showMessageDialog(view, "I hope you have enough money to buy accept.", "xDDDD",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void handleSpin() {
        if (isSpinning == true) {
            return;
        }

        int betAmount = 0;
        try {
            betAmount = Integer.parseInt(view.txtBetAmount.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (betAmount <= 0) {
            JOptionPane.showMessageDialog(view, "Investment must be more than $0", "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (model.deductMoney(betAmount) == true) {
            view.updateMoneyDisplay(model.getPlayerMoney());
            isSpinning = true;
            view.btnSpin.setEnabled(false);
            view.btnBackToTerm.setEnabled(false);
            view.txtBetAmount.setEnabled(false);

            SpinThread st = new SpinThread(betAmount);
            st.start();
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

    class FlashThread extends Thread {
        private JButton btn;

        public FlashThread(JButton btn) {
            this.btn = btn;
        }

        public void run() {
            Color oldBg = btn.getBackground();
            Color oldFg = btn.getForeground();
            btn.setBackground(Color.RED);
            btn.setForeground(Color.WHITE);

            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                System.out.println("Error");
            }

            btn.setBackground(oldBg);
            btn.setForeground(oldFg);
        }
    }

    class SpinThread extends Thread {
        private int bet;

        public SpinThread(int betAmount) {
            this.bet = betAmount;
        }

        public void run() {
            try {
                for (int i = 0; i < 20; i++) {
                    int r1 = (int) (Math.random() * 7) + 1;
                    int r2 = (int) (Math.random() * 7) + 1;
                    int r3 = (int) (Math.random() * 7) + 1;

                    view.lblSlot1.setText(String.valueOf(r1));
                    view.lblSlot2.setText(String.valueOf(r2));
                    view.lblSlot3.setText(String.valueOf(r3));

                    Thread.sleep(50);
                }

                int num1 = Integer.parseInt(view.lblSlot1.getText());
                int num2 = Integer.parseInt(view.lblSlot2.getText());
                int num3 = Integer.parseInt(view.lblSlot3.getText());

                calculatePrize(bet, num1, num2, num3);

                isSpinning = false;
                view.btnSpin.setEnabled(true);
                view.btnBackToTerm.setEnabled(true);
                view.txtBetAmount.setEnabled(true);

            } catch (Exception ex) {
                System.out.println("Error");
            }
        }
    }
}