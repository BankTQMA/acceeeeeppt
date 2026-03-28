package com.lnw.acceeeeeppt.scene.level.level3_term_of_service;

import java.util.Arrays;

public class Level3CheckboxPuzzle {
    private boolean[] checkboxes;
    private boolean isSolved;

    public Level3CheckboxPuzzle(boolean[] initialState) {
        this.checkboxes = Arrays.copyOf(initialState, 5);
        this.isSolved = false;
        checkWinCondition();
    }

    public void toggle(int index) {
        if (index < 0 || index >= checkboxes.length)
            return;

        checkboxes[index] = !checkboxes[index];

        if (index > 0) {
            checkboxes[index - 1] = !checkboxes[index - 1];
        }

        if (index < checkboxes.length - 1) {
            checkboxes[index + 1] = !checkboxes[index + 1];
        }

        checkWinCondition();
    }

    public void toggle(int index1, int index2) {
        toggle(index1);
        toggle(index2);
    }

    private void checkWinCondition() {
        for (boolean state : checkboxes) {
            if (!state) {
                isSolved = false;
                return;
            }
        }
        isSolved = true;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public boolean[] getStates() {
        return checkboxes;
    }
}
