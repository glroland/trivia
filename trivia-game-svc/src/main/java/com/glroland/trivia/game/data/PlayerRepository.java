package com.glroland.trivia.game.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.glroland.trivia.game.entities.Player;

public interface PlayerRepository extends MongoRepository<Player, String> {
    
}
