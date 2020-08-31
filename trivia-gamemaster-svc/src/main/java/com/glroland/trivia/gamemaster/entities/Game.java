package com.glroland.trivia.gamemaster.entities;

import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;

public class Game {
    
    @Id
    private String id;

    private List<Player> players;
    private Date createDateTime;
    private Date endDate;
    private GameStatusEnum status;
    
    public Game()
    {
        createDateTime = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public GameStatusEnum getStatus() {
        return status;
    }

    public void setStatus(GameStatusEnum status) {
        this.status = status;
    }    
}
