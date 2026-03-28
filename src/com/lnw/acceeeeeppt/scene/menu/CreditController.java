package com.lnw.acceeeeeppt.scene.menu;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JOptionPane;

import com.lnw.acceeeeeppt.scene.MainView;
import com.lnw.acceeeeeppt.ui.SceneConstants;

public class CreditController {
    private MainView mainView;
    private CreditView creditView;

    public CreditController(MainView mainView, CreditView creditView) {
        this.creditView = creditView;
        this.mainView = mainView;

        this.creditView.addBackButtonActionListener(e -> onBackButton());
        this.creditView.addAgreeeeButtonActionListener(e -> onAgreeeeButton());
    }

    private void onBackButton() {
        mainView.switchPanelCard(SceneConstants.MAINMENU);
    }

    private void onAgreeeeButton() {
        int confirmation = JOptionPane.showConfirmDialog(creditView,
                "This will open a browser to the Wikipedia page of the Agreeee game. Do you want to continue?",
                "Agreeee? Acceeeeeppt?",
                JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.NO_OPTION)
            return;
        String url = "https://ja.wikipedia.org/wiki/%E5%88%A9%E7%94%A8%E8%A6%8F%E7%B4%84%E3%81%AB%E5%90%8C%E6%84%8F%E3%81%97%E3%81%9F%E3%81%84";
        try {
            URI uri = new URI(url);

            if (!Desktop.isDesktopSupported()) {
                JOptionPane.showMessageDialog(creditView,
                        "Desktop is not supported on this system.",
                        "Desktop is not supported", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Desktop desktop = Desktop.getDesktop();

            if (!desktop.isSupported(Desktop.Action.BROWSE)) {
                JOptionPane.showMessageDialog(creditView,
                        "Browsing is not supported on this system.",
                        "Browsing is not supported", JOptionPane.ERROR_MESSAGE);
                return;
            }

            desktop.browse(uri);
        } catch (URISyntaxException ex) {
            JOptionPane.showMessageDialog(creditView, "Invalid URL: " + url, "Invalid URL",
                    JOptionPane.WARNING_MESSAGE);
            ex.printStackTrace();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(creditView, "Failed to open browser: " + ex.getMessage(),
                    "Failed to open browser", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
