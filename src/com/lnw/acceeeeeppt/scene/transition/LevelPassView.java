package com.lnw.acceeeeeppt.scene.transition;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.lnw.acceeeeeppt.ui.MarginConstants;

public class LevelPassView extends JPanel {
    private JPanel topPanel;
    private JPanel buttonRowPanel;
    private JLabel passLabel;

    public LevelPassView() {
        topPanel = new JPanel();
        buttonRowPanel = new JPanel();
        passLabel = new JLabel("PASSED");

        // Container configuration
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(MarginConstants.GLOBALMARGIN, MarginConstants.GLOBALMARGIN,
                MarginConstants.GLOBALMARGIN, MarginConstants.GLOBALMARGIN));
        buttonRowPanel.setLayout(new FlowLayout());
    }
}
