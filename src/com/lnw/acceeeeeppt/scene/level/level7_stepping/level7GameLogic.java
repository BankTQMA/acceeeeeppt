package com.lnw.acceeeeeppt.scene.level.level7_stepping;

import java.util.Random;

public class Level7GameLogic {

    public static final int TOTAL_STAGES = 4;
    private int stage = 1;
    private int correctPath;
    private int failCount = 0;
    private final Random rng = new Random();

    public enum Screen {
        START, GAME, WIN
    }

    private Screen currentScreen = Screen.START;

    public void rollNewPath() {
        correctPath = rng.nextInt(3);
    }

    public boolean checkChoice(int index) {
        if (index == correctPath) {
            stage++;
            if (stage > TOTAL_STAGES) {
                currentScreen = Screen.WIN;
            } else {
                rollNewPath();
            }
            return true;
        } else {
            failCount++;
            stage = 1;
            rollNewPath();
            return false;
        }
    }

    public void startGame() {
        stage = 1;
        failCount = 0;
        currentScreen = Screen.GAME;
        rollNewPath();
    }

    public void goHome() {
        currentScreen = Screen.START;
    }

    // Getters
    public int getStage() {
        return stage;
    }

    public int getFailCount() {
        return failCount;
    }

    public Screen getCurrentScreen() {
        return currentScreen;
    }

    public String getQuote() {
        return switch (stage) {
            case 1 ->
                "\"First step always the hardest\"";
            case 2 ->
                "\"One step closer\"";
            case 3 ->
                "\"P(A)\"";
            case 4 ->
                "\"Enact the Ending\"";
            default ->
                "";
        };
    }
}
