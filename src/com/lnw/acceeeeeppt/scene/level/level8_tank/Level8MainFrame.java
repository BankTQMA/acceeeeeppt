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
        scrollPane.setPreferredSize(new Dimension(800, 300));

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add a 10-pixel border
        contentPanel.add(scrollPane, BorderLayout.NORTH);

        JButton continueButton = new JButton("Continue");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(continueButton);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.setContentPane(contentPanel);

        frame.setVisible(true);
    }
}
