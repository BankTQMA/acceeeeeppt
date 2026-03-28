package com.lnw.acceeeeeppt.scene.level.level3_term_of_service;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Font;

public abstract class BaseGameLevel extends JPanel implements GameLevel {
    protected int readProgress = 0;
    protected boolean isReadingComplete = false;

    public abstract void resetPuzzle();

    public void renderCustomGraphics(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(new Color(200, 200, 200, 100));
        g2d.setFont(new Font("Serif", Font.ITALIC, 12));
        g2d.drawString("Acceeeeeppt - Level 3", getWidth() - 150, getHeight() - 10);
    }

    public void checkAbstract(BaseGameLevel level) {
    }
}
