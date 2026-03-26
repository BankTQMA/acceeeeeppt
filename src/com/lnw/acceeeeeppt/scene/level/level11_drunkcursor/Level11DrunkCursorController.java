package com.lnw.acceeeeeppt.scene.level.level11_drunkcursor;

import java.awt.*;
import java.awt.event.*;

public class Level11DrunkCursorController implements MouseMotionListener, MouseListener {
    
    private Level11DrunkCursorView view;
    private LevelMap map;

    private int fakeX;
    private int fakeY;
    private int lastRealX = -1;
    private int lastRealY = -1;
    private boolean isGameOver = false;
    private boolean isWin = false;

    private Robot robot;
    private boolean isRecentering = false;
    private Runnable onLevelComplete; 

    public Level11DrunkCursorController(Level11DrunkCursorView view, LevelMap map) {
        this.view = view;
        this.map = map;

        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        resetToStart();

        this.view.addMouseMotionListener(this);
        this.view.addMouseListener(this);
    }

    public void setOnLevelCompleteHandler(Runnable handler) {
        this.onLevelComplete = handler;
    }

    private void resetToStart() {
        if (map.startArea != null) {
            fakeX = map.startArea.x + 6;
            fakeY = map.startArea.y + 3;
            view.setFakePosition(fakeX, fakeY);
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
        if (fakeX > view.getWidth()) fakeX = view.getWidth();
        if (fakeY < 0) fakeY = 0;
        if (fakeY > view.getHeight()) fakeY = view.getHeight();

        if (e.getX() < 50 || e.getX() > view.getWidth() - 50 || 
            e.getY() < 50 || e.getY() > view.getHeight() - 50) {
            
            Point panelLocation = view.getLocationOnScreen();
            int centerX = panelLocation.x + (view.getWidth() / 2);
            int centerY = panelLocation.y + (view.getHeight() / 2);

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
            updateView();
            
            if (onLevelComplete != null) {
                onLevelComplete.run(); 
            }
            return;
        }

        updateView();
    }

    private void updateView() {
        view.setGameState(isWin, isGameOver);
        view.setFakePosition(fakeX, fakeY);
        view.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) { mouseMoved(e); }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isGameOver) {
            isGameOver = false;
            resetToStart(); 
            updateView();
        }
    }
    
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}