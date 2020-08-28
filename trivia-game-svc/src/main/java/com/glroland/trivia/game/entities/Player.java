package com.glroland.trivia.game.entities;

import java.util.Date;
import org.springframework.data.annotation.Id;

public class Player {
    
    @Id
    private String id;

    private String name;
    private String email;
    private Date createDateTime;
    private Date updateDateTime;
    
    public Player()
    {
        createDateTime = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }    
}
