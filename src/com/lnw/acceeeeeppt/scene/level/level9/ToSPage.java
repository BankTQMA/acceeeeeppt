package com.lnw.acceeeeeppt.scene.level.level9;

import javax.swing.*;
import java.awt.*;

public class ToSPage extends JPanel {
    public ToSPage(startStat game) {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextArea textArea = new JTextArea();
        textArea.setText("TERMS OF LOCKED CONSENT\n\n"
            + "By accessing this interface, you acknowledge that agreement is a restricted feature.\n\n"
            + "1. Agreement Access\nThe 'Agree' function is currently locked.\n\n"
            + "2. Conditional Consent\nAny attempt to agree without authorization will fail.\n\n"
            + "3. Premium Agreement Rights\nUnlock via 'Master Key'.\n\n"
            + "4. Recursive Agreement Clause\nYou agree that you cannot agree until unlocked.\n\n"
            + "5. Consent Ownership\nAll agreements are property of the system.\n\n"
            + "By continuing, you confirm that you understand that you cannot agree... yet.");

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);

        JPanel buttonWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton agreeBtn = new JButton("Agree");
        game.agreeButtonRef = agreeBtn;
        
        agreeBtn.setPreferredSize(new Dimension(120, 40));
        agreeBtn.setBackground(new Color(225, 225, 225)); 
        agreeBtn.setEnabled(false); 

        agreeBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Level Complete!");
            System.exit(0);
        });

        buttonWrapper.add(agreeBtn);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonWrapper, BorderLayout.SOUTH);
    }
}