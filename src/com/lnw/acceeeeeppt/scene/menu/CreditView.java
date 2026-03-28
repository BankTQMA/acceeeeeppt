package com.lnw.acceeeeeppt.scene.menu;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.lnw.acceeeeeppt.ui.FontPresets;
import com.lnw.acceeeeeppt.ui.MarginConstants;
import com.lnw.acceeeeeppt.ui.ResourceConstants;
import com.lnw.acceeeeeppt.ui.SceneConstants;

public class CreditView extends JPanel {
    private JPanel topBar;
    private JPanel contentPanel;
    private JPanel creditMessageAndButtonPanel;
    private JButton backButton;
    private JLabel titleLabel;
    private JLabel pictureLabel;
    private JLabel creditMessageLabel;

    public CreditView() {
        // Container initialisation
        topBar = new JPanel();
        contentPanel = new JPanel();
        creditMessageAndButtonPanel = new JPanel();

        // Container configuration
        setLayout(new BorderLayout());
        topBar.setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(MarginConstants.GLOBALMARGIN, MarginConstants.GLOBALMARGIN,
                MarginConstants.GLOBALMARGIN, MarginConstants.GLOBALMARGIN));
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        creditMessageAndButtonPanel.setLayout(new FlowLayout());

        backButton = new JButton("< Back");
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setFont(FontPresets.H2BOLDFONT);
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        titleLabel = new JLabel(SceneConstants.CREDITMENU);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(FontPresets.H1BOLDFONT);

        try {
            BufferedImage tokidokiImage = ImageIO.read(new File(ResourceConstants.TOKIDOKI_ACTIONGAME_PATH));
            Image image = tokidokiImage.getScaledInstance(600, 350, Image.SCALE_SMOOTH);

            pictureLabel = new JLabel(new ImageIcon(image));
        } catch (Exception e) {
            e.printStackTrace();
        }

        pictureLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        /* language=html */
        creditMessageLabel = new JLabel("""
                <html>
                    <b>Inspired by Agreeee — </b>
                    a brilliantly frustrating experience.<br>
                    This version is an original re-implementation built from scratch<br>
                    for learning and experimentation.<br>
                </html>
                    """);

        creditMessageAndButtonPanel.add(creditMessageLabel);

        topBar.add(backButton, BorderLayout.WEST);

        contentPanel.add(titleLabel);
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(pictureLabel);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(creditMessageAndButtonPanel);

        add(topBar, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
    }

    public void addBackButtonActionListener(ActionListener l) {
        backButton.addActionListener(l);
    }
}
