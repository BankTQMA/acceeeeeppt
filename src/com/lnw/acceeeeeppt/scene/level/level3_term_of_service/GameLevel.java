package com.lnw.acceeeeeppt.scene.level.level3_term_of_service;

import javax.swing.JScrollPane;

public interface GameLevel {
    void validatePuzzle(CheckboxPuzzle puzzle);

    void startScrollMonitor(JScrollPane scrollPane);

    void checkInterface(GameLevel level);
}
