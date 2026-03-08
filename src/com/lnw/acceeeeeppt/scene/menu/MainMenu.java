package com.lnw.acceeeeeppt.scene.menu;

import java.awt.*;
import javax.swing.*;

import com.lnw.acceeeeeppt.ui.MainMenuConstants;

public class MainMenu {
    private JFrame frame;
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

    public MainMenu() {
        frame = new JFrame("Acceeeeeppt");
        menuPanel = new JPanel();
        menuComponentContainer = new JPanel();
        buttonContainer = new JPanel();
        gameTitle = new JLabel("Acceeeeeppt");
        newGameButton = new JButton("New Game");
        loadGameButton = new JButton("Load");
        optionButton = new JButton("Option");
        creditButton = new JButton("Credit");
        exitButton = new JButton("Exit");
        madeWithLoveLabel = new JLabel("Made with ❤️ for your Potato PC.");

        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1080, 640);
        frame.setResizable(false);

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

        frame.add(menuPanel);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
