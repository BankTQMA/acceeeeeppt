package com.lnw.acceeeeeppt.scene.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;

import com.lnw.acceeeeeppt.ui.FontPresets;
import com.lnw.acceeeeeppt.ui.MarginConstants;

public class OptionView extends JPanel {
    private JPanel topBar;
    private JPanel contentPanel;
    private JPanel bottomBar;

    private JButton backButton;
    private JLabel optionsTitle;
    private JLabel unappliedWarningLabel;

    private JSlider masterSlider;
    private JSlider sfxSlider;
    private JSlider musicSlider;

    private JButton revertButton;
    private JButton applyButton;

    private JLabel madeWithHeartLabel;

    public OptionView() {
        setLayout(new BorderLayout());

        topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButton = new JButton("< Back");
        topBar.add(backButton);

        add(topBar, BorderLayout.NORTH);
        setBorder(BorderFactory.createEmptyBorder(MarginConstants.GLOBALMARGIN, MarginConstants.GLOBALMARGIN,
                MarginConstants.GLOBALMARGIN, MarginConstants.GLOBALMARGIN));
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setFont(FontPresets.H2BOLDFONT);
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        optionsTitle = new JLabel("Options");
        optionsTitle.setFont(FontPresets.H1BOLDFONT);
        optionsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        unappliedWarningLabel = new JLabel("* You have unapplied changes");
        unappliedWarningLabel.setFont(FontPresets.REGULARITALICFONT);
        unappliedWarningLabel.setForeground(Color.RED);
        unappliedWarningLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        unappliedWarningLabel.setVisible(false);

        masterSlider = new JSlider(0, 100, 50);
        sfxSlider = new JSlider(0, 100, 50);
        musicSlider = new JSlider(0, 100, 50);

        JPanel masterBox = createSettingBox("Master Volume:", masterSlider);
        JPanel sfxBox = createSettingBox("SFX:", sfxSlider);
        JPanel musicBox = createSettingBox("Music:", musicSlider);
        JPanel actionButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));

        revertButton = new JButton("Revert");
        applyButton = new JButton("Apply");

        revertButton.setFont(FontPresets.H2PLAINFONT);
        applyButton.setFont(FontPresets.H2PLAINFONT);

        actionButtonPanel.add(revertButton);
        actionButtonPanel.add(applyButton);

        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(optionsTitle);
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(unappliedWarningLabel);
        contentPanel.add(Box.createVerticalStrut(15));

        contentPanel.add(masterBox);
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(sfxBox);
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(musicBox);

        contentPanel.add(Box.createVerticalStrut(25));
        contentPanel.add(actionButtonPanel);

        add(contentPanel, BorderLayout.CENTER);

        bottomBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        madeWithHeartLabel = new JLabel("Made with ❤️ for your potato PC.");
        bottomBar.add(madeWithHeartLabel);
        add(bottomBar, BorderLayout.SOUTH);
    }

    private JPanel createSettingBox(String title, JSlider slider) {
        JPanel box = new JPanel(new BorderLayout(10, 0));
        box.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));

        JLabel label = new JLabel(title);
        label.setFont(new Font("Tahoma", Font.BOLD, 14));
        label.setPreferredSize(new Dimension(120, 30));

        box.add(label, BorderLayout.WEST);
        box.add(slider, BorderLayout.CENTER);

        box.setMaximumSize(new Dimension(500, 50));

        return box;
    }

    public void addBackButtonActionListener(ActionListener l) {
        backButton.addActionListener(l);
    }

    public void addRevertButtonActionListener(ActionListener l) {
        revertButton.addActionListener(l);
    }

    public void addApplyButtonActionListener(ActionListener l) {
        applyButton.addActionListener(l);
    }

    public void addMasterSliderChangeListener(ChangeListener l) {
        masterSlider.addChangeListener(l);
    }

    public void addSfxSliderChangeListener(ChangeListener l) {
        sfxSlider.addChangeListener(l);
    }

    public void addMusicSliderChangeListener(ChangeListener l) {
        musicSlider.addChangeListener(l);
    }

    public void setUnappliedWarningVisible(boolean visible) {
        unappliedWarningLabel.setVisible(visible);
    }

    public int getMasterVolume() {
        return masterSlider.getValue();
    }

    public int getSfxVolume() {
        return sfxSlider.getValue();
    }

    public int getMusicVolume() {
        return musicSlider.getValue();
    }

    public void setMasterVolume(int vol) {
        masterSlider.setValue(vol);
    }

    public void setSfxVolume(int vol) {
        sfxSlider.setValue(vol);
    }

    public void setMusicVolume(int vol) {
        musicSlider.setValue(vol);
    }
}
