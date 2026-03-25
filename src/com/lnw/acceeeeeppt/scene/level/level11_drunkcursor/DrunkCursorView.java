package com.lnw.acceeeeeppt.scene.level.level11_drunkcursor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class DrunkCursorView extends JPanel implements MouseMotionListener, MouseListener {
    
    private LevelMap map;
    private int fakeX, fakeY; 
    private int lastRealX = -1;
    private int lastRealY = -1;
    private boolean isGameOver = false;
    private boolean isWin = false;

    private Robot robot;
    private boolean isRecentering = false;

    private Runnable onLevelComplete; 

    public DrunkCursorView() {
        this.map = new LevelMap();
        setPreferredSize(new Dimension(800, 600));

        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        resetToStart();

        BufferedImage blankImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(blankImg, new Point(0, 0), "blank");
        setCursor(blankCursor);

        addMouseMotionListener(this);
        addMouseListener(this);
    }

    public void setOnLevelCompleteHandler(Runnable handler) {
        this.onLevelComplete = handler;
    }

    public JPanel getMainJPanel() {
        return this;
    }

    private void resetToStart() {
        if (map.startArea != null) {
            fakeX = map.startArea.x + 6;
            fakeY = map.startArea.y + 3;
        }
        lastRealX = -1; 
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (isGameOver || isWin) return;

        if (isRecentering) {
            isRecentering = false;
            lastRealX = e.getX();
            lastRealY = e.getY();
            return;
        }

        if (lastRealX == -1) {
            lastRealX = e.getX();
            lastRealY = e.getY();
            return;
        }

        int deltaX = e.getX() - lastRealX;
        int deltaY = e.getY() - lastRealY;
        fakeX -= deltaX;
        fakeY -= deltaY;

        if (fakeX < 0) fakeX = 0;
        if (fakeX > getWidth()) fakeX = getWidth();
        if (fakeY < 0) fakeY = 0;
        if (fakeY > getHeight()) fakeY = getHeight();

        if (e.getX() < 50 || e.getX() > getWidth() - 50 || 
            e.getY() < 50 || e.getY() > getHeight() - 50) {
            
            Point panelLocation = getLocationOnScreen();
            int centerX = panelLocation.x + (getWidth() / 2);
            int centerY = panelLocation.y + (getHeight() / 2);

            isRecentering = true;
            if (robot != null) {
                robot.mouseMove(centerX, centerY);
            }
        } else {
            lastRealX = e.getX();
            lastRealY = e.getY();
        }

        for (Rectangle wall : map.walls) {
            if (wall.contains(fakeX, fakeY)) {
                isGameOver = true;
                break;
            }
        }

        if (map.agreeButton != null && map.agreeButton.contains(fakeX, fakeY)) {
            isWin = true;
            repaint();
            
            if (onLevelComplete != null) {
                onLevelComplete.run(); 
            }
        }

        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) { mouseMoved(e); }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isGameOver) {
            isGameOver = false;
            resetToStart(); 
            repaint();
        }
    }
    
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}

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