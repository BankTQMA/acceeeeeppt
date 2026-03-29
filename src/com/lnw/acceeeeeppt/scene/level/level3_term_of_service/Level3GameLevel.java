package com.lnw.acceeeeeppt.scene.level.level3_term_of_service;

import javax.swing.JScrollPane;

interface Level3GameLevel {
    void validatePuzzle(Level3CheckboxPuzzle puzzle);

    void startScrollMonitor(JScrollPane scrollPane);

    void checkInterface(Level3GameLevel level);
}
