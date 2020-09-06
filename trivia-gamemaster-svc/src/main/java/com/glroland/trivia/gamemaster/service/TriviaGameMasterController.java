package com.glroland.trivia.gamemaster.service;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.glroland.trivia.gamemaster.entities.Player;
import com.glroland.trivia.gamemaster.entities.Game;
import com.glroland.trivia.gamemaster.entities.Lobby;
import com.glroland.trivia.gamemaster.entities.LobbyPlayer;
import com.glroland.trivia.gamemaster.entities.LobbyStatusEnum;
import com.glroland.trivia.gamemaster.data.PlayerRepository;
import com.glroland.trivia.gamemaster.data.GameRepository;
import com.glroland.trivia.gamemaster.data.LobbyRepository;

@RestController
public class TriviaGameMasterController {
    
    private static final Logger log = LoggerFactory.getLogger(TriviaGameMasterController.class);
    
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private LobbyRepository lobbyRepository;

    @GetMapping("/purge")
    @CrossOrigin(origins = "*")
    @Transactional
    public void purge()
    {
        log.warn("Purging all managed types from databases.  Fun times!");
        gameRepository.deleteAll();
        lobbyRepository.deleteAll();
        playerRepository.deleteAll();
    }

    @GetMapping("/signin")
    @CrossOrigin(origins = "*")
    @Transactional
    public String signin(String name, String email)
    {
        // validate arguments
        if ((email == null) || (email.length() == 0))
        {
            String msg = "Input email address is empty or null!";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }
        if ((name == null) || (name.length() == 0))
        {
            String msg = "Input name is empty or null!";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }

        if (log.isDebugEnabled())
            log.debug("signin called - Name=" + name + " Email=" + email);

        String cleanEmail = email.toLowerCase();

        // attempt to locate player by email
        Player player = null;
        List<Player> players = playerRepository.findByEmail(cleanEmail);
        if ((players != null) && (players.size() != 0))
        {
            if (players.size() == 1)
            {
                player = players.get(0);
            }
            else
            {
                String msg = "More than one player with email address exists.  This should be an impossible condition.  Email=" + cleanEmail;
                log.error(msg);
                throw new RuntimeException(msg);
            }
        }

        if (player == null)
        {
            // not found so create
            log.info("Creating player!  Name=" + name + " Email=" + cleanEmail);

            player = new Player();
            player.setName(name);
            player.setEmail(cleanEmail);
            playerRepository.save(player);
        }
        else if (!name.equals(player.getName())) 
        {
            // found but the player was married :)
            log.info("Updating player!  Name=" + name + " Email=" + email);

            player.setName(name);
            player.setUpdateDateTime(new Date());
            playerRepository.save(player);
        }

        log.info("Player Sign In!  Name=" + name + " Email=" + cleanEmail + " ID=" + player.getId());
        return player.getId();
    }

    @GetMapping("/lobby")
    @CrossOrigin(origins = "*")
    @Transactional
    public Lobby updateLobby(String playerId)
    {
        // validate arguments
        if ((playerId == null) || (playerId.length() == 0))
        {
            String msg = "Input player ID passed into update lobby is empty or null!";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }
        
        log.info("Update Lobby Called!  PlayerId=" + playerId);

        List<Lobby> allLobbies = lobbyRepository.findAll();
        log.info("# of Lobbies in system = " + allLobbies.size());
        int index = 0;
        for(Lobby logLobby : allLobbies)
        {
            log.info("Lobby [" + index + "] - " + logLobby.toString());
        }

        Lobby lobby = null;

        // attempt to find
        List<Lobby> lobbies = lobbyRepository.findByPlayerAndStatus(playerId, LobbyStatusEnum.Open);
        if ((lobbies == null) || (lobbies.size() == 0))
        {
            log.info("No matching lobbies found at all.  Creating!");

            // no lobby found, so create one
            lobby = new Lobby();
            lobby.setTimeWindow(60);    // one minute
            lobby.setIdealPlayerCount(2);
            lobby.setStatus(LobbyStatusEnum.Open);
            List<LobbyPlayer> players = new ArrayList<LobbyPlayer>();
            LobbyPlayer lobbyPlayer = new LobbyPlayer();
            lobbyPlayer.setPlayerId(playerId);
            lobbyPlayer.setLastCheckIn(Calendar.getInstance().getTime());
            players.add(lobbyPlayer);
            lobby.setPlayers(players);
            lobbyRepository.save(lobby);
        }
        else
        {
            log.info("Found lobbies - looking for player registration.  Size=" + lobbies.size());

            // update existing lobby
            lobby = lobbies.get(0);
            List<LobbyPlayer> players = lobby.getPlayers();
            for (int i=0; i<players.size(); i++)
            {
                
            }
        }

        return lobby;
    }

    @GetMapping("/games")
    @CrossOrigin(origins = "*")
    @Transactional
    public List<Game> getGamesForPlayer(String playerId)
    {
        // validate arguments
        if ((playerId == null) || (playerId.length() == 0))
        {
            String msg = "Input player ID passed into get games for player is empty or null!";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }

        if(log.isDebugEnabled())
            log.debug("Getting games for Player!  playerID=" + playerId);

        return gameRepository.findByPlayers(playerId);
    }
}
