package com.lnw.acceeeeeppt.scene.level.level3;

public class CheckboxPuzzle {
    private boolean[] checkboxes;
    private boolean isSolved;

    public CheckboxPuzzle(boolean[] initialState) {
        this.checkboxes = new boolean[5];
        for (int i = 0; i < 5; i++) {
            this.checkboxes[i] = initialState[i];
        }
        this.isSolved = false;
        checkWinCondition();
    }

    public void toggle(int index) {
        if (index < 0 || index >= checkboxes.length) return;
        
        checkboxes[index] = !checkboxes[index];
        
        if (index > 0) {
            checkboxes[index - 1] = !checkboxes[index - 1];
        }
        
        if (index < checkboxes.length - 1) {
            checkboxes[index + 1] = !checkboxes[index + 1];
        }
        
        checkWinCondition();
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