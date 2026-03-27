package level8_tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Iterator;

public class TankMinigame extends JPanel implements ActionListener, KeyListener, MouseListener {
    // this is based on a certain flash game.
    // this is pretty long and complicated, so I will only leave a general idea of it:
    // most of the code is taught in class, some part require research and AI help. -Jo_jo
    private static final int MAP_W = 800, MAP_H = 480;
    private static final int WALL_T = 12;

    private Timer timer;
    private double tankX, tankY, tankAngle;
    private static final int TANK_W = 24, TANK_H = 30;
    private ArrayList<Bullet> bullets;
    private ArrayList<Rectangle> enemies;
    private ArrayList<Rectangle> walls;
    private boolean wPressed, sPressed, aPressed, dPressed;
    private JButton continueButton;
    private boolean gameWon = false;

    private static class Bullet {
        double x, y, dx, dy;
        Bullet(double x, double y, double dx, double dy) {
            this.x = x; this.y = y; this.dx = dx; this.dy = dy;
        }
        Rectangle getBounds() { return new Rectangle((int) x - 3, (int) y - 3, 6, 6); }
    }

    public TankMinigame(JButton continueButton) {
        this.continueButton = continueButton;
        this.continueButton.setEnabled(false);

        setFocusable(true);
        setBackground(Color.LIGHT_GRAY);
        addKeyListener(this);
        addMouseListener(this);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                requestFocusInWindow();
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                requestFocusInWindow();
            }
        });

        tankX = 55;
        tankY = MAP_H - 60;
        tankAngle = 0;

        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        walls = new ArrayList<>();

        buildWalls();
        buildEnemies();

        timer = new Timer(16, this);
        timer.start();

        SwingUtilities.invokeLater(this::requestFocusInWindow);
    }

    private void buildWalls() {
        // Outer border
        walls.add(new Rectangle(0, 0, MAP_W, WALL_T));
        walls.add(new Rectangle(0, MAP_H - WALL_T, MAP_W, WALL_T));
        walls.add(new Rectangle(0, 0, WALL_T, MAP_H));
        walls.add(new Rectangle(MAP_W - WALL_T, 0, WALL_T, MAP_H));

        // Inner maze walls
        walls.add(new Rectangle(90, WALL_T, WALL_T, 100));
        walls.add(new Rectangle(90, 100, 80, WALL_T));
        walls.add(new Rectangle(200, WALL_T, WALL_T, 60));
        walls.add(new Rectangle(200, 160, 300, WALL_T));
        walls.add(new Rectangle(200, 160, WALL_T, 180));
        walls.add(new Rectangle(200, 340, 150, WALL_T));
        walls.add(new Rectangle(350, 250, WALL_T, 100));
        walls.add(new Rectangle(300, 250, 60, WALL_T));
        walls.add(new Rectangle(500, 160, WALL_T, 120));
        walls.add(new Rectangle(500, 280, 100, WALL_T));
        walls.add(new Rectangle(600, 160, WALL_T, 130));
        walls.add(new Rectangle(600, 60, 90, WALL_T));
        walls.add(new Rectangle(690, 60, WALL_T, 100));
        walls.add(new Rectangle(WALL_T, 260, 80, WALL_T));
        walls.add(new Rectangle(550, 380, WALL_T, 100));
        walls.add(new Rectangle(650, 340, WALL_T, 80));
        walls.add(new Rectangle(650, 340, 80, WALL_T));
    }

    private void buildEnemies() {
        enemies.add(new Rectangle(20, 20, 70, 25));
        enemies.add(new Rectangle(700, 20, 70, 25));
        enemies.add(new Rectangle(400, 100, 70, 25));
        enemies.add(new Rectangle(590, 100, 70, 25));
        enemies.add(new Rectangle(25, 210, 70, 25));
        enemies.add(new Rectangle(580, 290, 70, 25));
        enemies.add(new Rectangle(320, 420, 70, 25));
        enemies.add(new Rectangle(530, 420, 70, 25));
        enemies.add(new Rectangle(680, 420, 70, 25));
    }

    @Override
    // This draw the game
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double sx = (double) getWidth() / MAP_W;
        double sy = (double) getHeight() / MAP_H;
        g2.scale(sx, sy);

        g2.setColor(new Color(210, 210, 210));
        g2.fillRect(0, 0, MAP_W, MAP_H);

        // Walls
        g2.setColor(new Color(70, 70, 70));
        for (Rectangle w : walls) {
            g2.fill(w);
        }

        // Enemies
        for (Rectangle enemy : enemies) {
            g2.setColor(Color.WHITE);
            g2.fill(enemy);
            g2.setColor(Color.DARK_GRAY);
            g2.drawRect(enemy.x, enemy.y, enemy.width, enemy.height);
            g2.setColor(Color.BLACK);
            g2.setFont(new Font("SansSerif", Font.PLAIN, 12));
            g2.drawString("Disagree", enemy.x + 5, enemy.y + 17);
        }

        // Tank
        AffineTransform old = g2.getTransform();
        g2.translate(tankX, tankY);
        g2.rotate(Math.toRadians(tankAngle));
        g2.setColor(new Color(34, 139, 34));
        g2.fillRect(-TANK_W / 2, -TANK_H / 2, TANK_W, TANK_H);
        g2.setColor(new Color(0, 100, 0));
        g2.fillRect(-3, -TANK_H / 2 - 14, 6, 16);
        g2.setTransform(old);

        // Bullets
        g2.setColor(Color.YELLOW);
        for (Bullet b : bullets) {
            g2.fillOval((int) b.x - 3, (int) b.y - 3, 6, 6);
        }

        // Win message
        if (gameWon) {
            g2.setColor(new Color(0, 0, 0, 150));
            g2.fillRect(MAP_W / 2 - 120, MAP_H / 2 - 20, 200, 40);
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("SansSerif", Font.BOLD, 18));
            g2.drawString("Requirement met.", MAP_W / 2 - 100, MAP_H / 2 + 6);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameWon) { repaint(); return; }

        double speed = 3;
        double turnSpeed = 4;

        if (aPressed) tankAngle -= turnSpeed;
        if (dPressed) tankAngle += turnSpeed;

        double rad = Math.toRadians(tankAngle);
        double newX = tankX, newY = tankY;
        if (wPressed) {
            newX += Math.sin(rad) * speed;
            newY -= Math.cos(rad) * speed;
        }
        if (sPressed) {
            newX -= Math.sin(rad) * speed;
            newY += Math.cos(rad) * speed;
        }

        Rectangle tankRect = new Rectangle((int) newX - TANK_W / 2, (int) newY - TANK_H / 2, TANK_W, TANK_H);
        boolean blocked = false;
        for (Rectangle w : walls) {
            if (tankRect.intersects(w)) { blocked = true; break; }
        }
        if (!blocked) {
            tankX = newX;
            tankY = newY;
        }

        Iterator<Bullet> bIt = bullets.iterator();
        // Move bullets and check collisions
        while (bIt.hasNext()) {
            Bullet b = bIt.next();
            b.x += b.dx;
            b.y += b.dy;
            if (b.x < 0 || b.x > MAP_W || b.y < 0 || b.y > MAP_H) {
                bIt.remove();
                continue;
            }
            boolean hitWall = false;
            for (Rectangle w : walls) {
                if (w.contains(b.x, b.y)) { hitWall = true; break; }
            }
            if (hitWall) {
                bIt.remove();
                continue;
            }
            Rectangle bb = b.getBounds();
            Iterator<Rectangle> eIt = enemies.iterator();
            boolean hitEnemy = false;
            while (eIt.hasNext()) {
                Rectangle en = eIt.next();
                if (bb.intersects(en)) {
                    eIt.remove();
                    hitEnemy = true;
                    break;
                }
            }
            if (hitEnemy) { bIt.remove(); }
        }

        if (enemies.isEmpty() && !gameWon) {
            gameWon = true;
            continueButton.setEnabled(true);
        }

        repaint();
    }

    private void shoot() {
        // Calculate bullet position and velocity
        double rad = Math.toRadians(tankAngle);
        double bulletSpeed = 8;
        double bx = tankX + Math.sin(rad) * (TANK_H / 2 + 14);
        double by = tankY - Math.cos(rad) * (TANK_H / 2 + 14);
        double dx = Math.sin(rad) * bulletSpeed;
        double dy = -Math.cos(rad) * bulletSpeed;
        bullets.add(new Bullet(bx, by, dx, dy));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> wPressed = true;
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> aPressed = true;
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> sPressed = true;
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> dPressed = true;
            case KeyEvent.VK_SPACE -> shoot();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> wPressed = false;
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> aPressed = false;
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> sPressed = false;
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> dPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        requestFocusInWindow();
        shoot();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}