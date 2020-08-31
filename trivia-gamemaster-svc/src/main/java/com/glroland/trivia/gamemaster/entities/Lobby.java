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
    private int idealPlayerCount;
    private LobbyStatusEnum status;
    private String gameId;
    
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

    public int getIdealPlayerCount() {
        return idealPlayerCount;
    }

    public void setIdealPlayerCount(int idealPlayerCount) {
        this.idealPlayerCount = idealPlayerCount;
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

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
}
