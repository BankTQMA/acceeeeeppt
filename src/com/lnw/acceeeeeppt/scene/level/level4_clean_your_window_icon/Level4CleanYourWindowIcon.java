package com.lnw.acceeeeeppt.scene.level.level4_clean_your_window_icon;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.lnw.acceeeeeppt.ui.WindowConfiguration;

public class Level4CleanYourWindowIcon extends JPanel {

    private JLabel fileLabel, binLabel, bgLabel;
    private int round = 1;
    private final int MAX_ROUND = 10;
    private JLabel draggedLabel = null;
    private Random rand = new Random();
    private ImageIcon fileIcon, binIcon;

    public Level4CleanYourWindowIcon() {
        setLayout(null);

        ImageIcon rawFile = new ImageIcon("resources/images/file.png");
        Image fileImg = rawFile.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        fileIcon = new ImageIcon(fileImg);

        ImageIcon rawBin = new ImageIcon("resources/images/bin.png");
        Image binImg = rawBin.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        binIcon = new ImageIcon(binImg);

        ImageIcon bgIcon = new ImageIcon("resources/images/bg.jpg");
        Image bgImg = bgIcon.getImage().getScaledInstance(WindowConfiguration.DISPLAYSIZE.width,
                WindowConfiguration.DISPLAYSIZE.height, Image.SCALE_SMOOTH);

        bgLabel = new JLabel(new ImageIcon(bgImg));
        bgLabel.setBounds(0, 0, WindowConfiguration.DISPLAYSIZE.width, WindowConfiguration.DISPLAYSIZE.height);

        fileLabel = new JLabel("File", fileIcon, JLabel.CENTER);
        fileLabel.setHorizontalTextPosition(JLabel.CENTER);
        fileLabel.setVerticalTextPosition(JLabel.BOTTOM);
        fileLabel.setBounds(120, 150, 120, 100);
        add(fileLabel);

        binLabel = new JLabel("Recycle Bin", binIcon, JLabel.CENTER);
        binLabel.setHorizontalTextPosition(JLabel.CENTER);
        binLabel.setVerticalTextPosition(JLabel.BOTTOM);
        binLabel.setBounds(350, 150, 120, 100);
        add(binLabel);

        add(bgLabel);
        setComponentZOrder(bgLabel, getComponentCount() - 1);
        enableDrag(fileLabel);
        enableDrag(binLabel);

        nextRound();
        setVisible(true);
        showMessageFrame();
    }

    private void showMessageFrame() {
        JDialog dialog = new JDialog();

        dialog.setSize(400, 150);
        dialog.setLayout(new BorderLayout());

        JLabel msg = new JLabel("Let's delete trash file", SwingConstants.CENTER);
        msg.setFont(new Font("Arial", Font.BOLD, 16));

        dialog.add(msg, BorderLayout.CENTER);

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void enableDrag(JLabel label) {
        label.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                draggedLabel = label;

                label.setLocation(label.getX() + e.getX() - 60,
                        label.getY() + e.getY() - 50);
            }
        });

        label.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                checkDrop();
            }
        });
    }

    private void checkDrop() {
        if (draggedLabel == null)
            return;

        JLabel targetLabel = (draggedLabel == fileLabel) ? binLabel : fileLabel;

        Rectangle r1 = draggedLabel.getBounds();
        Rectangle r2 = targetLabel.getBounds();

        r1.grow(30, 30);
        r2.grow(30, 30);

        boolean overlap = r1.intersects(r2);

        if (!overlap)
            return;

        String dragText = draggedLabel.getText();
        String targetText = targetLabel.getText();

        if (dragText.contains("File") && targetText.contains("Recycle")) {
            correctAction();
        } else if (dragText.contains("Recycle") && targetText.contains("File")) {
            wrongAction();
        }
    }

    private void correctAction() {
        if (round >= MAX_ROUND) {
            JOptionPane.showMessageDialog(this, "Congrats! NEXT LEVEL!");
            return;
        }

        JOptionPane.showMessageDialog(this, "You did Great!! Round " + round);
        round++;
        randomPosition();
        nextRound();
    }

    private void wrongAction() {
        int count = 15;

        for (int i = 0; i < count; i++) {
            JFrame wrongFrame = new JFrame("WRONG!!");
            wrongFrame.setSize(200, 100);

            JLabel label = new JLabel("WRONG!!", SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 16));
            wrongFrame.add(label);

            Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
            int x = rand.nextInt(screen.width - 200);
            int y = rand.nextInt(screen.height - 100);

            wrongFrame.setLocation(x, y);
            wrongFrame.setVisible(true);
        }

        new javax.swing.Timer(2000, e -> {
            System.exit(0);
        }).start();
    }

    private void randomPosition() {
        int maxX = WindowConfiguration.DISPLAYSIZE.width - 150;
        int maxY = WindowConfiguration.DISPLAYSIZE.height - 150;

        int x1 = rand.nextInt(maxX);
        int y1 = rand.nextInt(maxY);

        int x2, y2;

        do {
            x2 = rand.nextInt(maxX);
            y2 = rand.nextInt(maxY);
        } while (Math.abs(x1 - x2) < 100 && Math.abs(y1 - y2) < 100);

        fileLabel.setLocation(x1, y1);
        binLabel.setLocation(x2, y2);
    }

    private void nextRound() {
        int mode = rand.nextInt(4);

        switch (mode) {

            case 0:
                fileLabel.setText("File" + round);
                binLabel.setText("Recycle Bin");

                fileLabel.setIcon(fileIcon);
                binLabel.setIcon(binIcon);
                break;

            case 1:
                fileLabel.setText("Recycle Bin");
                binLabel.setText("File" + round);

                fileLabel.setIcon(fileIcon);
                binLabel.setIcon(binIcon);
                break;

            case 2:
                fileLabel.setText("File" + round);
                binLabel.setText("Recycle Bin");

                fileLabel.setIcon(binIcon);
                binLabel.setIcon(fileIcon);
                break;

            case 3:
                fileLabel.setText("File" + round);
                binLabel.setText("Recycle Bin");

                fileLabel.setIcon(fileIcon);
                binLabel.setIcon(binIcon);

                fileLabel.setLocation(350, 150);
                binLabel.setLocation(120, 150);
                break;
        }
    }
}
