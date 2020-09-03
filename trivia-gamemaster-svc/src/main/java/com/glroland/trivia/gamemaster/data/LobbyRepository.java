package com.glroland.trivia.gamemaster.data;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.glroland.trivia.gamemaster.entities.Lobby;
import com.glroland.trivia.gamemaster.entities.LobbyStatusEnum;

public interface LobbyRepository extends MongoRepository<Lobby, String> {

    @Query(value = "{ 'status' : ?1, 'players.id' : ?0 }")
//    @Query(value = "{ 'status' : ?1 }")
    public List<Lobby> findByPlayerAndStatus(String playerId, LobbyStatusEnum lobbyStatus);
}
