package com.lnw.acceeeeeppt.scene.level.level9;

import javax.swing.*;
import java.awt.*;

public class ToSPage {

    public ToSPage(startStat game){

        JFrame frame = new JFrame("Terms of Locked Consent");
        game.tosFrameRef = frame;
        frame.setSize(400,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10,10));

        frame.setLocation(100, 200);

        JPanel mainPanel = new JPanel(new BorderLayout(10,10));
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(15,15,15,15),
                BorderFactory.createLineBorder(Color.BLACK, 3)
        ));

        JTextArea textArea = new JTextArea();
        textArea.setText(
        "TERMS OF LOCKED CONSENT\n\n"

        + "By accessing this interface, you acknowledge that agreement is a restricted feature.\n\n"

        + "1. Agreement Access\n"
        + "The 'Agree' function is currently locked. Users are not permitted to agree at this time.\n\n"

        + "2. Conditional Consent\n"
        + "Any attempt to agree without proper authorization will result in no agreement being recognized.\n\n"

        + "3. Premium Agreement Rights\n"
        + "Agreement privileges may be unlocked through acquisition of a designated 'Master Key'.\n\n"

        + "4. Recursive Agreement Clause\n"
        + "By attempting to agree, you agree that you cannot agree until agreement has been unlocked.\n\n"

        + "5. Consent Ownership\n"
        + "All agreements, whether successful or not, are property of the system.\n\n"

        + "6. User Responsibility\n"
        + "It is the user's responsibility to obtain the necessary permissions before agreeing.\n\n"

        + "7. Final Agreement\n"
        + "Once the Master Key is obtained, the user may perform a valid agreement, completing all obligations.\n\n"

        + "By continuing, you confirm that you understand that you cannot agree... yet."
        );

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setFont(new Font("Serif", Font.PLAIN, 14));
        textArea.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel outsidePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.insets = new Insets(5,5,5,5);

        JLabel messageLabel = new JLabel(" ");
        messageLabel.setPreferredSize(new Dimension(260, 20));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton agreeBtn = new JButton("Agree");
        game.agreeButtonRef = agreeBtn;

        agreeBtn.addActionListener(e -> {

            if(!game.tosUnlocked){
                agreeBtn.setEnabled(false);
                messageLabel.setText("Use Master Key to unlock Agree button");

                new idleMain(game, () -> {});
            }
            else{
                JOptionPane.showMessageDialog(frame, "You win the level!");
                System.exit(0);
            }
        });

        gbc.gridy = 0;
        outsidePanel.add(messageLabel, gbc);

        gbc.gridy = 1;
        outsidePanel.add(agreeBtn, gbc);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(outsidePanel, BorderLayout.SOUTH);

        frame.setResizable(false);
        frame.setVisible(true);
    }
}