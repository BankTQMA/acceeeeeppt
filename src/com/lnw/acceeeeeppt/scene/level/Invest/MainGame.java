package Invest;

import javax.swing.*;

// This is for testing only. Once the Main Menu is finished. Youn can delete this
public class MainGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Term of Investment");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                InvestController controller = new InvestController(model, view, integration);

                frame.add(view);
                frame.setVisible(true);
            }
        });
    }
}