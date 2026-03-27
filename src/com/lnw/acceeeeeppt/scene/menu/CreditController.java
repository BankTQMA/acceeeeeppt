package com.lnw.acceeeeeppt.scene.menu;

import com.lnw.acceeeeeppt.scene.MainView;
import com.lnw.acceeeeeppt.ui.SceneConstants;

public class CreditController {
    private MainView mainView;
    private CreditView creditView;

    public CreditController(MainView mainView, CreditView creditView) {
        this.creditView = creditView;
        this.mainView = mainView;

        this.creditView.addBackButtonActionListener(e -> onBackButton());
    }

    private void onBackButton() {
        mainView.switchPanelCard(SceneConstants.MAINMENU);
    }
}
