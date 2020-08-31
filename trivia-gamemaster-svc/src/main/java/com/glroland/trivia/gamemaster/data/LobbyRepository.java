package com.glroland.trivia.gamemaster.data;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.glroland.trivia.gamemaster.entities.Lobby;

public interface LobbyRepository extends MongoRepository<Lobby, String> {
    
}
