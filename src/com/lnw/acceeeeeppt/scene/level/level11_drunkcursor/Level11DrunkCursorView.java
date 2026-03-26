package com.lnw.acceeeeeppt.scene.level.level11_drunkcursor;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Level11DrunkCursorView extends JPanel {
    
    private LevelMap map;
    private int fakeX;
    private int fakeY;
    private boolean isGameOver = false;
    private boolean isWin = false;

    public Level11DrunkCursorView(LevelMap map) {
        this.map = map;
        setPreferredSize(new Dimension(800, 600));

        BufferedImage blankImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(blankImg, new Point(0, 0), "blank");
        setCursor(blankCursor);
    }

    public JPanel getPanel() {
        return this;
    }

    public void setFakePosition(int x, int y) {
        this.fakeX = x;
        this.fakeY = y;
    }

    public void setGameState(boolean isWin, boolean isGameOver) {
        this.isWin = isWin;
        this.isGameOver = isGameOver;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (map.startArea != null) {
            g.setColor(Color.GRAY);
            g.fillRect(map.startArea.x, map.startArea.y, map.startArea.width, map.startArea.height);
            g.setColor(Color.BLACK);
            g.drawRect(map.startArea.x, map.startArea.y, map.startArea.width, map.startArea.height);
        }

        if (map.agreeButton != null) {
            g.setColor(Color.GREEN);
            g.fillRect(map.agreeButton.x, map.agreeButton.y, map.agreeButton.width, map.agreeButton.height);
            g.setColor(Color.BLACK);
            g.drawRect(map.agreeButton.x, map.agreeButton.y, map.agreeButton.width, map.agreeButton.height);
            
            g.setFont(new Font("Arial", Font.PLAIN, 8));
            g.drawString("Agree", map.agreeButton.x + 2, map.agreeButton.y + 16);
        }

        for (Rectangle wall : map.walls) {
            g.setColor(Color.RED);
            g.fillRect(wall.x, wall.y, wall.width, wall.height);
            g.setColor(Color.BLACK);
            g.drawRect(wall.x, wall.y, wall.width, wall.height);
        }

        if (!isGameOver && !isWin) {
            g.setColor(Color.BLACK);
            int[] xPoints = {fakeX, fakeX, fakeX + 12};
            int[] yPoints = {fakeY, fakeY + 18, fakeY + 12};
            g.fillPolygon(xPoints, yPoints, 3);
        }

        if (isGameOver) {
            g.setColor(new Color(0, 0, 0, 150)); 
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 60)); 
            g.drawString("GAME OVER", 200, 250);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Click anywhere to restart", 280, 320);
        }
    }
}