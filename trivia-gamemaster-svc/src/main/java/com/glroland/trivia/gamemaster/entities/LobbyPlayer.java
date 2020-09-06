package com.glroland.trivia.gamemaster.entities;

import java.util.Date;
import java.util.Calendar;

public class LobbyPlayer {
    private String playerId;
    private Date createDateTime;
    private Date lastCheckIn;

    public LobbyPlayer() {
        createDateTime = Calendar.getInstance().getTime();
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Date getLastCheckIn() {
        return lastCheckIn;
    }

    public void setLastCheckIn(Date lastCheckIn) {
        this.lastCheckIn = lastCheckIn;
    }
}