package com.lnw.acceeeeeppt.model;

import java.io.Serializable;

public class PlayerModel implements Serializable {
    private String saveName;
    private Level currLevel;

    public PlayerModel(String saveName) {
        this.saveName = saveName;
        currLevel = Level.INITIAL;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public Level getCurrLevel() {
        return currLevel;
    }

    public void setCurrLevel(Level currLevel) {
        this.currLevel = currLevel;
    }

}
