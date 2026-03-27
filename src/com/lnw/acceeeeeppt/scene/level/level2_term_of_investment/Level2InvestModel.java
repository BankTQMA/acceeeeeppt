package com.lnw.acceeeeeppt.scene.level.level2_term_of_investment;

public class Level2InvestModel {
    private int playerMoney;

    public Level2InvestModel(int initialMoney) {
        this.playerMoney = initialMoney;
    }

    public int getPlayerMoney() {
        return playerMoney;
    }

    public boolean deductMoney(int amount) {
        if (playerMoney >= amount) {
            playerMoney -= amount;
            return true;
        }
        return false;
    }

    public void addMoney(int amount) {
        playerMoney += amount;
    }

    public void addMoney(int amount, int multiplier) {
        playerMoney += (amount * multiplier);
    }
}
