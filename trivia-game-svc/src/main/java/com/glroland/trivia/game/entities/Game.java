package com.glroland.trivia.game.entities;

import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;

public class Game {
    
    @Id
    private String id;

    private List<Player> players;
    private Date createDateTime;
    
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
}
