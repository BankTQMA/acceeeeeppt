package com.lnw.acceeeeeppt.scene.level.level2_term_of_investment;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

// This is for testing only. Once the Main Menu is finished. Youn can delete this
public class MainGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Term of Investment");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setSize(800, 500);
            frame.setLocationRelativeTo(null);

            InvestModel model = new InvestModel(15);
            InvestView view = new InvestView();

            InvestIntegration integration = new InvestIntegration() {
                public void navigateToMenu() {
                    JOptionPane.showMessageDialog(frame, "Back to Menu");
                }

                public void unlockNextStage() {
                    JOptionPane.showMessageDialog(frame, "Next Stage");
                }
            };
            new InvestController(model, view, integration);

            frame.add(view);
            frame.setVisible(true);
        });
    }
}
