package com.lnw.acceeeeeppt.model;

import java.io.Serializable;
import java.time.Instant;

public class PlayerModel implements Serializable {
    private String saveName;
    private Level currLevel;
    private Instant createdDateTimeInstant;

    public PlayerModel(String saveName) {
        this.saveName = saveName;
        this.createdDateTimeInstant = Instant.now();
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

    public Instant getCreatedDateTimeInstant() {
        return createdDateTimeInstant;
    }

    public void setCreatedDateTimeInstant(Instant timeStampInstant) {
        this.createdDateTimeInstant = timeStampInstant;
    }

}
