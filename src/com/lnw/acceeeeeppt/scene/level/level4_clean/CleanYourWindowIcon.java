package com.lnw.acceeeeepppt.scene.level.level4_clean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class CleanYourWindowIcon extends JFrame {

    private JLabel fileLabel, binLabel, bgLabel;
    private int round = 1;
    private final int MAX_ROUND = 10;
    private JLabel draggedLabel = null;
    private Random rand = new Random();
    private ImageIcon fileIcon, binIcon;

    public CleanYourWindowIcon() {
        setTitle("Clean Your Window");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImageIcon rawFile = new ImageIcon("resources/images/file.png");
        Image fileImg = rawFile.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        fileIcon = new ImageIcon(fileImg);
        
        ImageIcon rawBin  = new ImageIcon( "resources/images/bin.png");
        Image binImg = rawBin.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        binIcon = new ImageIcon(binImg);
        
        ImageIcon bgIcon  = new ImageIcon("resources/images/bg.jpg");
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        Image bgImg = bgIcon.getImage().getScaledInstance(screen.width, screen.height, Image.SCALE_SMOOTH);

        bgLabel = new JLabel(new ImageIcon(bgImg));
        bgLabel.setBounds(0, 0, screen.width, screen.height);

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
        
        getContentPane().add(bgLabel);
        getContentPane().setComponentZOrder(bgLabel, getContentPane().getComponentCount()-1);
        enableDrag(fileLabel);
        enableDrag(binLabel);
        
        nextRound();
        setVisible(true);
        showMessageFrame();
    }
    
    private void showMessageFrame() {
        JDialog dialog = new JDialog(this, "Message", true);

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
        if (draggedLabel == null) return;

        JLabel targetLabel = (draggedLabel == fileLabel) ? binLabel : fileLabel;

        Rectangle r1 = draggedLabel.getBounds();
        Rectangle r2 = targetLabel.getBounds();

        r1.grow(30, 30);
        r2.grow(30, 30);

        boolean overlap = r1.intersects(r2);

        if (!overlap) return;

        String dragText = draggedLabel.getText();
        String targetText = targetLabel.getText();

        if (dragText.contains("File") && targetText.contains("Recycle")) {
            correctAction();
        } 
        else if (dragText.contains("Recycle") && targetText.contains("File")) {
            wrongAction();
        }
    }

    private void correctAction() {
        if (round >= MAX_ROUND) {
            JOptionPane.showMessageDialog(this, "Congrats! NEXT LEVEL!");
            dispose();
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
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        int maxX = screen.width - 150;
        int maxY = screen.height - 150;

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

    public static void main(String[] args) {
        new CleanYourWindowIcon();
    }
}