package com.lnw.acceeeeeppt.scene.level.level10_read_it_all_before_pass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Lv10 extends JFrame {

    private JButton acceptButton;
    private JScrollPane scrollPane;
    private JTextArea termsText;

    // Custom Scrollbars
    private CustomVerticalScrollBarPanel verticalScroll;
    private CustomHorizontalScrollBarPanel horizontalScroll;

    // --- State การปลดล็อค ---
    private boolean hasReachedBottom = false;
    private boolean hasReachedLeftmost = false;

    // --- ค่าคงที่ ---
    private final int HORIZONTAL_HEIGHT = 26;
    private final int FAKE_TOP_MARGIN = 26;

    private final int H_THUMB_SIZE = 570;
    private final int V_THUMB_SIZE = 50;

    // สีของ Scrollbar
    private final Color TRACK_COLOR = new Color(160, 160, 160);
    private final Color THUMB_COLOR = new Color(210, 210, 210);
    private final Color LINE_COLOR = new Color(100, 100, 100);

    // ข้อความที่ถูกจัดหน้าใหม่ ย้ายข้อ 7 ไปขวาสุด
    private final String TERMS_CONTENT = " \n\n" +
            " 0. Common Sense (Yes, This Is Important)\n" +
            " Before using this game, please remember that this is just a game.\n" +
            " If something weird happens, it’s probably part of the game… or maybe a bug. Who knows?\n\n" +
            "By continuing, you agree to.\n\n" +
            "-Not scream at your screen (too much).\n" +
            "-Not blame the developer for your skill issues.\n" +
            "Accept that losing is sometimes your own fault.\n" +
            "\n\n" + // เว้นระยะห่างให้ซ่อนเนียนๆ
            "Terms of Information Usage\n\n" +
            "By accessing or using this game.\n" +
            "You agree to the collection and use of information in accordance with these terms.\n\n" +
            "1. Information We Collect\n" +
            "We may collect basic information such as:\n" +
            "- Username and in-game ID\n" +
            "- Gameplay statistics and progress\n" +
            "- Device information (for performance optimization)\n" +
            "- Optional contact information (if provided by the user)\n\n" +

            "2. Purpose of Data Usage\n" +
            "Collected information may be used to:\n" +
            "- Improve gameplay experience\n" +
            "- Maintain and secure the game system\n" +
            "- Provide updates and new features\n" +
            "- Prevent cheating, abuse, or unauthorized access\n\n" +

            "3. Data Protection\n" +
            "We implement reasonable security measures to protect user information.\n" +
            "From unauthorized access, alteration, or disclosure.\n\n" +

            "4. Third-Party Services\n" +
            "The game may use third-party services to enhance functionality.\n" +
            "These services may process limited data necessary for operation.\n\n" +

            "5. User Rights\n" +
            "Users may request to review, update, or\n" +
            "delete their personal information by contacting the game administrator.\n\n" +

            "6. Changes to These Terms\n" +
            "We reserve the right to update or modify these terms at any time.\n" +
            "Continued use of the game constitutes acceptance of any changes.\n\n" +

            "7.Player Responsibility                                                                                                                             \n"
            +
            "By playing this game, you acknowledge that:                                                                             8. Unexpected Behavior\n"
            +
            "- Your wins are your skill.                                                                                                         This game may occasionally behave in unexpected or unintended ways.\n"
            +
            "- Your losses are also… your skill.                                                                                             By continuing to play, you agree that:\n"
            +
            "- Any emotional damage caused by losing, lag, or bad luck is entirely on you.                               - Not everything will always work perfectly (yes, bugs exist).\n"
            +
            "The developer is not responsible for broken keyboards or rage quits during gameplay.                    - Some features may change, break, or mysteriously disappear.\n"
            +
            "                                                                                                                                               - You will not take the game (or its developer) too seriously.\n"
            +
            "                                                                                                                                              If anything strange happens, please consider it part of the experience.\n\n";

    public Lv10() {
        setTitle("Term of Information usage");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(new BorderLayout());

        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.setBackground(new Color(220, 220, 220));

        JPanel fixedSizePanel = new JPanel(new BorderLayout());
        fixedSizePanel.setPreferredSize(new Dimension(660, 440));
        fixedSizePanel.setOpaque(false);

        JPanel mainContainer = new JPanel(new BorderLayout());
        mainContainer.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
        mainContainer.setOpaque(false);

        JPanel contentBorderPanel = new JPanel(new BorderLayout());
        contentBorderPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        contentBorderPanel.setBackground(Color.WHITE);

        termsText = new JTextArea(TERMS_CONTENT);
        termsText.setEditable(false);
        termsText.setLineWrap(false);
        termsText.setFocusable(false); // <--- กันผู้เล่นคลุมดำตัวหนังสือแล้วลากลงเพื่อโกงการเลื่อนหน้าจอ
        termsText.setMargin(new Insets(15, 20, 15, 20));
        termsText.setFont(new Font("SansSerif", Font.PLAIN, 13));

        scrollPane = new JScrollPane(termsText);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        scrollPane.setWheelScrollingEnabled(false); // <--- ปิดการใช้ลูกกลิ้งเมาส์ (Mouse Wheel) แบบ 100%

        JPanel innerPanel = new JPanel(new BorderLayout());
        horizontalScroll = new CustomHorizontalScrollBarPanel();

        innerPanel.add(horizontalScroll, BorderLayout.NORTH);
        innerPanel.add(scrollPane, BorderLayout.CENTER);
        contentBorderPanel.add(innerPanel, BorderLayout.CENTER);

        verticalScroll = new CustomVerticalScrollBarPanel();
        contentBorderPanel.add(verticalScroll, BorderLayout.EAST);

        mainContainer.add(contentBorderPanel, BorderLayout.CENTER);
        fixedSizePanel.add(mainContainer, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        JLabel hintLabel = new JLabel("\"KEEP UP\"");
        hintLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        hintLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));

        acceptButton = new JButton("Accept button");
        acceptButton.setEnabled(false);
        acceptButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        acceptButton.setPreferredSize(new Dimension(160, 45));
        acceptButton.setMaximumSize(new Dimension(160, 45));
        acceptButton.setBackground(TRACK_COLOR);
        acceptButton.setForeground(Color.BLACK);
        acceptButton.setFocusPainted(false);
        acceptButton.setOpaque(true);
        acceptButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));

        acceptButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(Lv10.this,
                    "Congratulations! You unlocked the secret terms.",
                    "Level Cleared",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        });

        bottomPanel.add(hintLabel);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        bottomPanel.add(acceptButton);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        fixedSizePanel.add(bottomPanel, BorderLayout.SOUTH);

        wrapperPanel.add(fixedSizePanel);
        add(wrapperPanel, BorderLayout.CENTER);
    }

    // =========================================================
    // Custom Horizontal ScrollBar
    // =========================================================
    private class CustomHorizontalScrollBarPanel extends JPanel {
        private int thumbX;
        private int dragOffsetX = -1;
        private boolean isInitialized = false;

        public CustomHorizontalScrollBarPanel() {
            setPreferredSize(new Dimension(0, HORIZONTAL_HEIGHT));
            setBackground(TRACK_COLOR);
            setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.DARK_GRAY));

            MouseAdapter mouseHandler = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getX() >= thumbX && e.getX() <= thumbX + H_THUMB_SIZE) {
                        dragOffsetX = e.getX() - thumbX;
                    } else {
                        dragOffsetX = -1;
                    }
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    if (dragOffsetX == -1)
                        return;
                    int newX = e.getX() - dragOffsetX;
                    int maxX = getWidth() - H_THUMB_SIZE;
                    thumbX = Math.max(0, Math.min(maxX, newX));

                    if (!hasReachedLeftmost && thumbX <= 2) {
                        hasReachedLeftmost = true;
                    }

                    syncTextScrollHorizontal(maxX);
                    repaint();
                    verticalScroll.repaint();
                }
            };
            addMouseListener(mouseHandler);
            addMouseMotionListener(mouseHandler);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (!isInitialized && getWidth() > 0) {
                thumbX = getWidth() - H_THUMB_SIZE;
                syncTextScrollHorizontal(getWidth() - H_THUMB_SIZE);
                isInitialized = true;
            }

            g.setColor(THUMB_COLOR);
            g.fillRect(thumbX, 2, H_THUMB_SIZE, getHeight() - 4);
            g.setColor(Color.DARK_GRAY);
            g.drawRect(thumbX, 2, H_THUMB_SIZE, getHeight() - 4);

            g.setColor(LINE_COLOR);
            int centerX = thumbX + (H_THUMB_SIZE / 2);
            int startY = 6;
            int endY = getHeight() - 6;
            g.drawLine(centerX - 4, startY, centerX - 4, endY);
            g.drawLine(centerX, startY, centerX, endY);
            g.drawLine(centerX + 4, startY, centerX + 4, endY);
        }

        private void syncTextScrollHorizontal(int maxX) {
            JViewport viewport = scrollPane.getViewport();
            int viewWidth = viewport.getView().getWidth();
            int extent = viewport.getWidth();
            int maxScroll = viewWidth - extent;

            if (maxScroll > 0 && maxX > 0) {
                double progress = 1.0 - ((double) thumbX / maxX);
                progress = Math.max(0.0, Math.min(1.0, progress));
                Point p = viewport.getViewPosition();
                p.x = (int) (maxScroll * progress);
                viewport.setViewPosition(p);
            }
        }
    }

    // =========================================================
    // Custom Vertical ScrollBar
    // =========================================================
    private class CustomVerticalScrollBarPanel extends JPanel {
        private int dragOffsetY = -1;
        private int thumbY = FAKE_TOP_MARGIN;
        private boolean isInitialized = false;

        public CustomVerticalScrollBarPanel() {
            setPreferredSize(new Dimension(22, 0));
            setBackground(TRACK_COLOR);
            setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.DARK_GRAY));

            MouseAdapter mouseHandler = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getY() >= thumbY && e.getY() <= thumbY + V_THUMB_SIZE) {
                        dragOffsetY = e.getY() - thumbY;
                    } else {
                        dragOffsetY = -1;
                    }
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    if (dragOffsetY == -1)
                        return;
                    int newY = e.getY() - dragOffsetY;
                    int maxY = getHeight() - V_THUMB_SIZE;

                    int minY = (hasReachedBottom && hasReachedLeftmost) ? 0 : FAKE_TOP_MARGIN;

                    thumbY = Math.max(minY, Math.min(maxY, newY));

                    if (!hasReachedBottom && thumbY >= maxY) {
                        hasReachedBottom = true;
                    }

                    if (hasReachedBottom && hasReachedLeftmost && thumbY < FAKE_TOP_MARGIN) {
                        acceptButton.setEnabled(true);
                    } else {
                        acceptButton.setEnabled(false);
                        acceptButton.setBackground(TRACK_COLOR);
                    }

                    syncTextScrollVertical(maxY);
                    repaint();
                }
            };
            addMouseListener(mouseHandler);
            addMouseMotionListener(mouseHandler);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (!isInitialized && getHeight() > 0) {
                thumbY = FAKE_TOP_MARGIN;
                syncTextScrollVertical(getHeight() - V_THUMB_SIZE);
                isInitialized = true;
            }

            boolean isUnlocked = (hasReachedBottom && hasReachedLeftmost);

            if (!isUnlocked) {
                g.setColor(TRACK_COLOR);
                g.fillRect(0, 0, getWidth(), FAKE_TOP_MARGIN);
                g.setColor(Color.DARK_GRAY);
                g.drawLine(0, FAKE_TOP_MARGIN - 1, getWidth(), FAKE_TOP_MARGIN - 1);
            } else {
                g.setColor(TRACK_COLOR);
                g.fillRect(0, 0, getWidth(), FAKE_TOP_MARGIN);
            }

            g.setColor(THUMB_COLOR);
            g.fillRect(2, thumbY, getWidth() - 4, V_THUMB_SIZE);
            g.setColor(Color.DARK_GRAY);
            g.drawRect(2, thumbY, getWidth() - 4, V_THUMB_SIZE);

            g.setColor(LINE_COLOR);
            int centerY = thumbY + (V_THUMB_SIZE / 2);
            int startX = 6;
            int endX = getWidth() - 6;
            g.drawLine(startX, centerY - 4, endX, centerY - 4);
            g.drawLine(startX, centerY, endX, centerY);
            g.drawLine(startX, centerY + 4, endX, centerY + 4);
        }

        private void syncTextScrollVertical(int maxY) {
            JViewport viewport = scrollPane.getViewport();
            int viewHeight = viewport.getView().getHeight();
            int extent = viewport.getHeight();
            int maxScroll = viewHeight - extent;

            if (maxScroll > 0 && maxY > 0) {
                int secretScrollLimit = 220;
                int currentScrollY;

                if (thumbY <= FAKE_TOP_MARGIN) {
                    double ratio = (double) thumbY / FAKE_TOP_MARGIN;
                    currentScrollY = (int) (ratio * secretScrollLimit);
                } else {
                    double ratio = (double) (thumbY - FAKE_TOP_MARGIN) / (maxY - FAKE_TOP_MARGIN);
                    currentScrollY = secretScrollLimit + (int) (ratio * (maxScroll - secretScrollLimit));
                }

                currentScrollY = Math.max(0, Math.min(maxScroll, currentScrollY));
                Point p = viewport.getViewPosition();
                p.y = currentScrollY;
                viewport.setViewPosition(p);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Lv10().setVisible(true);
        });
    }
}
