package com.glroland.trivia.gamemaster.entities;

import java.util.Date;
import java.util.Map;
import org.springframework.data.annotation.Id;

public class Lobby {
    
    @Id
    private String id;

    private Map<String, Date> players;
    private Date createDateTime;
    private long timeWindow;
    private int numPlayers;
    private LobbyStatusEnum status;
    
    public Lobby()
    {
        createDateTime = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Date> getPlayers() {
        return players;
    }

    public void setPlayers(Map<String, Date> players) {
        this.players = players;
    }

    public long getTimeWindow() {
        return timeWindow;
    }

    public void setTimeWindow(long timeWindow) {
        this.timeWindow = timeWindow;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public LobbyStatusEnum getStatus() {
        return status;
    }

    public void setStatus(LobbyStatusEnum status) {
        this.status = status;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

}
