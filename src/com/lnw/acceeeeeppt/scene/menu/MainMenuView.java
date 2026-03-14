package com.lnw.acceeeeeppt.scene.menu;

import java.awt.*;
import javax.swing.*;

import com.lnw.acceeeeeppt.ui.MainMenuConstants;

public class MainMenuView {
    private JLabel gameTitle;
    private JLabel madeWithLoveLabel;
    private JPanel menuPanel;
    private JPanel menuComponentContainer;
    private JPanel buttonContainer;
    private JButton newGameButton;
    private JButton loadGameButton;
    private JButton optionButton;
    private JButton creditButton;
    private JButton exitButton;
    private MainMenuController menuController;

    public MainMenuView() {
        SwingUtilities.invokeLater(() -> {
            menuPanel = new JPanel();
            menuComponentContainer = new JPanel();
            buttonContainer = new JPanel();
            gameTitle = new JLabel("Acceeeeeppt");
            newGameButton = new JButton("New Game");
            loadGameButton = new JButton("Load");
            optionButton = new JButton("Option");
            creditButton = new JButton("Credit");
            exitButton = new JButton("Exit");
            menuController = new MainMenuController();
            madeWithLoveLabel = new JLabel("Made with ❤️ for your Potato PC.");

            newGameButton.addActionListener(menuController::onNewGame);
            loadGameButton.addActionListener(menuController::onLoadGame);
            optionButton.addActionListener(menuController::onOption);
            creditButton.addActionListener(menuController::onCredit);
            exitButton.addActionListener(menuController::onExit);

            menuPanel.setLayout(new GridBagLayout());

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
            menuPanel.add(menuComponentContainer);
        });
    }

    public static void main(String[] args) {
        new MainMenuView();
    }
}
