package com.glroland.trivia.gamemaster.data;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.glroland.trivia.gamemaster.entities.Game;

public interface GameRepository extends MongoRepository<Game, String> {
    
//    @Query(value = "{ 'players.playerId' : ?0 }")
    public List<Game> findByPlayers(String player);
}
