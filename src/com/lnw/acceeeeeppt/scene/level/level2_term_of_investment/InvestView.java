package com.lnw.acceeeeeppt.scene.level.level2_term_of_investment;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class InvestView extends JPanel {
    CardLayout cardLayout;
    JPanel mainCardPanel;

    JLabel lblMoneyTerm, lblWarning, lblMoneySlot, lblSlot1, lblSlot2, lblSlot3, lblInvestLink;
    JPanel pnlInvestText, textSwitcherPanel;
    JCheckBox chkAccept;
    JButton btnBuyAccept, btnAccept, btnGoBack, btnSpin, btnBackToTerm;
    JTextField txtBetAmount;

    public InvestView() {
        setLayout(new BorderLayout());
        cardLayout = new CardLayout();
        mainCardPanel = new JPanel(cardLayout);

        mainCardPanel.add(createTermPanel(), "TERM");
        mainCardPanel.add(createSlotPanel(), "SLOT");

        add(mainCardPanel, BorderLayout.CENTER);
    }

    public void updateMoneyDisplay(int money) {
        lblMoneyTerm.setText("Money: $" + money);
        lblMoneySlot.setText("Money: $" + money);
    }

    private JPanel createTermPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel lblTitle = new JLabel("                 Term of Investment", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 22));

        lblMoneyTerm = new JLabel("Money: $0");
        lblMoneyTerm.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblMoneyTerm.setForeground(new Color(0, 150, 0));

        topPanel.add(new JLabel(" "), BorderLayout.WEST);
        topPanel.add(lblTitle, BorderLayout.CENTER);
        topPanel.add(lblMoneyTerm, BorderLayout.EAST);
        panel.add(topPanel, BorderLayout.NORTH);

        JTextArea txtInfo = new JTextArea();
        txtInfo.setEditable(false);
        txtInfo.setLineWrap(true);
        txtInfo.setWrapStyleWord(true);
        txtInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtInfo.setText(
                """
                        Investment (N)
                        The action or process of investing money for profit or material result.

                        Gambling (N)
                        the activity of playing games of chance for money, or of betting on the outcome of future events such as the results
                        of races or games.

                        Is investment a kind of gambling (Q&A)
                        While investment and gambling both involve risking capital for potential gain, they are fundamentally different in their
                        economic impact and approach to risk . Economically, investing is considered a positive-sum game because it allocates
                        capital to productive assets like businesses or real estate, creating new value, jobs, and goods that allow all participants
                        to potentially profit over time.

                        Rule No.1
                        Nothing in this world is free. Not even back button.""");

        JScrollPane scrollPane = new JScrollPane(txtInfo);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomContainer = new JPanel(new BorderLayout());
        bottomContainer.setBorder(BorderFactory.createEmptyBorder(5, 20, 20, 20));

        JPanel warningPanel = new JPanel(new GridLayout(2, 1));
        lblWarning = new JLabel("*you must read all the terms before accepting");
        lblWarning.setForeground(Color.RED);
        lblWarning.setFont(new Font("Tahoma", Font.ITALIC, 12));

        pnlInvestText = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JLabel lblPart1 = new JLabel("I accept Term of Investment and want to ");
        lblPart1.setFont(new Font("Tahoma", Font.PLAIN, 14));

        lblInvestLink = new JLabel("<html><u>Invest</u></html>");
        lblInvestLink.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblInvestLink.setForeground(Color.BLUE);
        lblInvestLink.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel lblPart3 = new JLabel(" my money");
        lblPart3.setFont(new Font("Tahoma", Font.PLAIN, 14));

        pnlInvestText.add(lblPart1);
        pnlInvestText.add(lblInvestLink);
        pnlInvestText.add(lblPart3);

        chkAccept = new JCheckBox("I accept Term of Investment");
        chkAccept.setFont(new Font("Tahoma", Font.BOLD, 14));
        chkAccept.setVisible(false);

        textSwitcherPanel = new JPanel(new CardLayout());
        textSwitcherPanel.add(pnlInvestText, "TEXT_MODE");
        textSwitcherPanel.add(chkAccept, "CHECKBOX_MODE");

        warningPanel.add(lblWarning);
        warningPanel.add(textSwitcherPanel);
        bottomContainer.add(warningPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 15, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        btnBuyAccept = new JButton("Buy Accept button (100$)");
        btnAccept = new JButton("Accept");
        btnAccept.setVisible(false);
        btnAccept.setEnabled(false);
        btnGoBack = new JButton("Go back to main menu");

        buttonPanel.add(btnBuyAccept);
        buttonPanel.add(btnAccept);
        buttonPanel.add(btnGoBack);
        bottomContainer.add(buttonPanel, BorderLayout.SOUTH);

        panel.add(bottomContainer, BorderLayout.SOUTH);

        lblInvestLink.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(mainCardPanel, "SLOT");
            }
        });

        chkAccept.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnAccept.setEnabled(chkAccept.isSelected());
            }
        });

        return panel;
    }

    private JPanel createSlotPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel lblTitle = new JLabel("                Slot Machine", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));

        lblMoneySlot = new JLabel("Money: $0");
        lblMoneySlot.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblMoneySlot.setForeground(new Color(0, 150, 0));

        topPanel.add(lblTitle, BorderLayout.CENTER);
        topPanel.add(lblMoneySlot, BorderLayout.EAST);
        panel.add(topPanel, BorderLayout.NORTH);

        JPanel slotArea = new JPanel(new GridLayout(1, 3, 20, 0));
        slotArea.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        Font slotFont = new Font("Tahoma", Font.BOLD, 60);
        lblSlot1 = createBasicSlotLabel("7", slotFont);
        lblSlot2 = createBasicSlotLabel("7", slotFont);
        lblSlot3 = createBasicSlotLabel("7", slotFont);

        slotArea.add(lblSlot1);
        slotArea.add(lblSlot2);
        slotArea.add(lblSlot3);
        panel.add(slotArea, BorderLayout.CENTER);

        JPanel bottomWrapper = new JPanel(new BorderLayout());

        JPanel betPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblBetInfo = new JLabel("Invest Amount: $");
        lblBetInfo.setFont(new Font("Tahoma", Font.BOLD, 16));

        txtBetAmount = new JTextField("10", 5);
        txtBetAmount.setFont(new Font("Tahoma", Font.BOLD, 16));
        txtBetAmount.setHorizontalAlignment(JTextField.CENTER);

        betPanel.add(lblBetInfo);
        betPanel.add(txtBetAmount);
        bottomWrapper.add(betPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));

        btnSpin = new JButton("SPIN!");
        btnSpin.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnSpin.setPreferredSize(new Dimension(150, 40));

        btnBackToTerm = new JButton("Back to Term");
        btnBackToTerm.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnBackToTerm.setPreferredSize(new Dimension(150, 40));

        buttonPanel.add(btnSpin);
        buttonPanel.add(btnBackToTerm);
        bottomWrapper.add(buttonPanel, BorderLayout.SOUTH);

        panel.add(bottomWrapper, BorderLayout.SOUTH);
        return panel;
    }

    private JLabel createBasicSlotLabel(String text, Font font) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(font);
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        return label;
    }
}
