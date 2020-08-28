package com.glroland.trivia.game.data;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.glroland.trivia.game.entities.Player;

public interface PlayerRepository extends MongoRepository<Player, String> {
    
    public List<Player> findByEmail(String email);
    public List<Player> findByName(String name);
}
