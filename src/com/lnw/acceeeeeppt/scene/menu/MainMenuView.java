package com.lnw.acceeeeeppt.scene.menu;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.lnw.acceeeeeppt.ui.MainMenuConstants;

public class MainMenuView {
    private JLabel gameTitle;
    private JLabel madeWithLoveLabel;
    private JPanel mainJPanel;
    private JPanel menuComponentContainer;
    private JPanel buttonContainer;
    private JButton newGameButton;
    private JButton loadGameButton;
    private JButton optionButton;
    private JButton creditButton;
    private JButton exitButton;

    public MainMenuView() {
        mainJPanel = new JPanel();
        menuComponentContainer = new JPanel();
        buttonContainer = new JPanel();
        gameTitle = new JLabel("Acceeeeeppt");
        newGameButton = new JButton("New Game");
        loadGameButton = new JButton("Load");
        optionButton = new JButton("Option");
        creditButton = new JButton("Credit");
        exitButton = new JButton("Exit");
        madeWithLoveLabel = new JLabel("Made with ❤️ for your Potato PC.");

        mainJPanel.setLayout(new GridBagLayout());

        menuComponentContainer.setLayout(new BoxLayout(menuComponentContainer, BoxLayout.Y_AXIS));

        gameTitle.setHorizontalAlignment(SwingConstants.CENTER);
        gameTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuComponentContainer.add(gameTitle);
        menuComponentContainer.add(Box.createVerticalStrut(MainMenuConstants.MAIN1_V_GAP));

        buttonContainer.setLayout(new GridLayout(5, 1, 0, MainMenuConstants.BUTTON_V_GAP));
        buttonContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonContainer.add(newGameButton);
        buttonContainer.add(loadGameButton);
        buttonContainer.add(optionButton);
        buttonContainer.add(creditButton);
        buttonContainer.add(exitButton);

        menuComponentContainer.add(buttonContainer);
        menuComponentContainer.add(Box.createVerticalStrut(MainMenuConstants.MAIN2_V_GAP));

        madeWithLoveLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuComponentContainer.add(madeWithLoveLabel);
        mainJPanel.add(menuComponentContainer);
    }

    public void addNewGameButtonActionHandler(ActionListener l) {
        newGameButton.addActionListener(l);
    }

    public void addLoadGameButtonActionHandler(ActionListener l) {
        loadGameButton.addActionListener(l);
    }

    public void addOptionButtonActionHandler(ActionListener l) {
        optionButton.addActionListener(l);
    }

    public void addCreditButtonActionHandler(ActionListener l) {
        creditButton.addActionListener(l);
    }

    public void addExitButtonActionHandler(ActionListener l) {
        exitButton.addActionListener(l);
    }

    public JPanel getMainJPanel() {
        return mainJPanel;
    }
}
