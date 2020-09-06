package com.glroland.trivia.gamemaster.entities;

import java.util.Date;
import java.util.List;
import java.util.Calendar;
import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Lobby {
    
    @Id
    private String id;

    private List<LobbyPlayer> players;
    private Date createDateTime;
    private long timeWindow;
    private int idealPlayerCount;
    private LobbyStatusEnum status;
    private String gameId;
    
    public Lobby()
    {
        createDateTime = Calendar.getInstance().getTime();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<LobbyPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<LobbyPlayer> players) {
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

    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            return mapper.writeValueAsString(this);
        }
        catch(JsonProcessingException e)
        {
            throw new RuntimeException("Caught exception while trying to convert object to json string for debugging purposes", e);
        }
    }
}
