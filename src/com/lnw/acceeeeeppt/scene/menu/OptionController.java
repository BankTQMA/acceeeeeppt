package com.lnw.acceeeeeppt.scene.menu;

import javax.swing.event.ChangeListener;

import com.lnw.acceeeeeppt.scene.MainView;
import com.lnw.acceeeeeppt.system.SoundManager;
import com.lnw.acceeeeeppt.ui.SceneConstants;

public class OptionController {
    private MainView mainView;
    private OptionView optionView;

    private int savedMasterVol = 50;
    private int savedSfxVol = 50;
    private int savedMusicVol = 50;

    public OptionController(MainView mainView, OptionView optionView) {
        this.mainView = mainView;
        this.optionView = optionView;

        this.optionView.addBackButtonActionListener(e -> onBackButton());
        this.optionView.addRevertButtonActionListener(e -> onRevert());
        this.optionView.addApplyButtonActionListener(e -> onApply());

        ChangeListener sliderListener = e -> checkUnappliedChanges();

        this.optionView.addMasterSliderChangeListener(sliderListener);
        this.optionView.addSfxSliderChangeListener(sliderListener);
        this.optionView.addMusicSliderChangeListener(sliderListener);
    }

    public void onBackButton() {
        onRevert();
        mainView.switchPanelCard(SceneConstants.MAINMENU);
    }

    public void onRevert() {
        optionView.setMasterVolume(savedMasterVol);
        optionView.setSfxVolume(savedSfxVol);
        optionView.setMusicVolume(savedMusicVol);

        optionView.setUnappliedWarningVisible(false);
    }

    public void onApply() {
        savedMasterVol = optionView.getMasterVolume();
        savedSfxVol = optionView.getSfxVolume();
        savedMusicVol = optionView.getMusicVolume();

        optionView.setUnappliedWarningVisible(false);

        SoundManager.setVolumeSettings(savedMasterVol, savedSfxVol, savedMusicVol);
    }

    private void checkUnappliedChanges() {
        boolean isChanged = (optionView.getMasterVolume() != savedMasterVol) ||
                (optionView.getSfxVolume() != savedSfxVol) ||
                (optionView.getMusicVolume() != savedMusicVol);
        optionView.setUnappliedWarningVisible(isChanged);
    }
}
