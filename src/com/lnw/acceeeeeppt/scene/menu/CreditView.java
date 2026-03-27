package com.lnw.acceeeeeppt.scene.menu;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.lnw.acceeeeeppt.ui.MarginConstants;

public class CreditView extends JPanel {
    private JPanel topBar;
    private JPanel contentPanel;

    public CreditView() {
        // Container initialisation
        topBar = new JPanel();
        contentPanel = new JPanel();

        // Container configuration
        setLayout(new BorderLayout());
        topBar.setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(MarginConstants.GLOBALMARGIN, MarginConstants.GLOBALMARGIN,
                MarginConstants.GLOBALMARGIN, MarginConstants.GLOBALMARGIN));
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
    }
}
