package level8_tank;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Level8MainFrame {
    public Level8MainFrame() {
        JFrame frame = new JFrame("Tank");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JTextArea textArea = new JTextArea("Text Area");
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(800, 150));

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPanel.add(scrollPane, BorderLayout.NORTH);

        JButton continueButton = new JButton("Continue");
        continueButton.setEnabled(false);
        continueButton.addActionListener(e ->
            JOptionPane.showMessageDialog(frame, "To be replaced with nextLevel()"));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(continueButton);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        TankMinigame tankMinigame = new TankMinigame(continueButton);
        tankMinigame.setPreferredSize(new Dimension(800, 300));
        contentPanel.add(tankMinigame, BorderLayout.CENTER);

        frame.setContentPane(contentPanel);

        frame.setVisible(true);
    }
}
