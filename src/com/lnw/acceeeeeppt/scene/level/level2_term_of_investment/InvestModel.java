package Invest;

public class InvestModel {
    private int playerMoney;

    public InvestModel(int initialMoney) {
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