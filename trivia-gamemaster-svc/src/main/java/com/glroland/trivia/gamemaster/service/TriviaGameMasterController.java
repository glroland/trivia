package com.glroland.trivia.gamemaster.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.glroland.trivia.gamemaster.entities.Player;
import com.glroland.trivia.gamemaster.entities.Game;
import com.glroland.trivia.gamemaster.entities.GameStatusEnum;
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

//    @GetMapping(value = "/")
//    public void redirect(HttpServletResponse response) throws IOException {
//        response.sendRedirect("/swagger-ui/");
//    }

    @DeleteMapping("/all")
    @CrossOrigin(origins = "*")
    @Transactional
    public void deleteAll()
    {
        log.warn("Purging all managed types from databases.  Fun times!");
        gameRepository.deleteAll();
        lobbyRepository.deleteAll();
        playerRepository.deleteAll();
    }

    @PostMapping("/signin")
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

    @PostMapping("/enterLobby")
    @CrossOrigin(origins = "*")
    @Transactional
    public Lobby enterLobby(String playerId)
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
        List<Lobby> lobbies = lobbyRepository.findByStatus(LobbyStatusEnum.Open);
        if ((lobbies == null) || (lobbies.size() == 0))
        {
            log.info("No open lobbies found.  Creating!");

            // no lobby found, so create one
            lobby = new Lobby();
            lobby.setIdealPlayerCount(2);
            lobby.setStatus(LobbyStatusEnum.Open);

            // set expiration time
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.SECOND, 15);
            lobby.setExpireDateTime(cal.getTime());

            // add requesting player
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
            log.info("Updating First Lobby in Sequence: LobbyID = " + lobby.getId());

            List<LobbyPlayer> lobbyPlayers = lobby.getPlayers();
            boolean found = false;
            for (LobbyPlayer lobbyPlayer : lobbyPlayers)
            {
                if (playerId.equals(lobbyPlayer.getPlayerId()))
                {
                    log.info("Player was previously registered to lobby - updating check in time!  LobbyID=" + lobby.getId());

                    found = true;
                    lobbyPlayer.setLastCheckIn(Calendar.getInstance().getTime());
                    lobbyRepository.save(lobby);
                }
            }

            if (!found)
            {
                log.info("Adding player to existing lobby.  Lobby ID = " + lobby.getId());

                LobbyPlayer newPlayer = new LobbyPlayer();
                newPlayer.setLastCheckIn(Calendar.getInstance().getTime());
                newPlayer.setPlayerId(playerId);
                lobbyPlayers.add(newPlayer);

                lobbyRepository.save(lobby);
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

        log.info("Getting games for Player!  playerID=" + playerId);
        return gameRepository.findByPlayers(playerId);
    }

    @DeleteMapping("/lobbies")
    @CrossOrigin(origins = "*")
    @Transactional
    public void deleteLobbies()
    {
        log.info("Clearing Lobbies Task Invoked");

        List<Lobby> openLobbies = lobbyRepository.findByStatus(LobbyStatusEnum.Open);
        if (openLobbies != null)
        {
            Date now = Calendar.getInstance().getTime();
            for (Lobby openLobby : openLobbies) 
            {
                if (now.after(openLobby.getExpireDateTime()))
                {
                    log.info("Closing expired lobby: " + openLobby.getId());
                    this.closeLobby(openLobby.getId());
                }
            }
        }
    }

    @DeleteMapping("/lobby")
    @CrossOrigin(origins = "*")
    @Transactional
    public void closeLobby(String lobbyId)
    {
        // validate arguments
        if ((lobbyId == null) || (lobbyId.length() == 0))
        {
            String msg = "Lobby ID passed to close lobby is empty or null";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }

        log.info("Closing Lobby: " + lobbyId);

        // retrieve lobby
        Optional<Lobby> optionalLobby = lobbyRepository.findById(lobbyId);
        if (!optionalLobby.isPresent())
        {
            String msg = "Cannot close non-existent lobby!  ID=" + lobbyId;
            log.error(msg);
            throw new RuntimeException(msg);
        }
        Lobby lobby = optionalLobby.get();

        // validate that lobby is currently open
        if (lobby.getStatus() != LobbyStatusEnum.Open)
        {
            String msg = "Cannot close lobby that isn't currently open!  ID=" + lobbyId + " Status=" + lobby.getStatus();
            log.error(msg);
            throw new RuntimeException(msg);
        }

        // create game for lobby
        Game game = createGameForLobby(lobby);
        if (game == null)
        {
            String msg = "No game was able to be created for lobby!  LobbyID= " + lobbyId;
            log.error(msg);
            throw new RuntimeException(msg);
        }

        // update lobby
        lobby.setStatus(LobbyStatusEnum.Closed);
        lobby.setGameId(game.getId());
        lobby.setCloseDateTime(Calendar.getInstance().getTime());
        lobbyRepository.save(lobby);
    }

    private Game createGameForLobby(Lobby lobby)
    {
        // validate arguments
        if (lobby == null)
        {
            String msg = "Input lobby is null!";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }
        if ((lobby.getPlayers() == null) || (lobby.getPlayers().size() == 0))
        {
            String msg = "Cannot create game for lobby that has no players!  ID=" + lobby.getId();
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }

        // create game
        Game game = new Game();
        game.setStatus(GameStatusEnum.New);

        List<String> players = new ArrayList<String>();
        for (LobbyPlayer lobbyPlayer : lobby.getPlayers())
        {
            Optional<Player> optionalPlayer = playerRepository.findById(lobbyPlayer.getPlayerId());
            if (!optionalPlayer.isPresent())
            {
                String msg = "Lobby contains player ID that is not associated with actual player!  Lobby ID = " + lobby.getId() + " Player ID = " + lobbyPlayer.getPlayerId();
                log.error(msg);
                throw new RuntimeException(msg);
            }

            players.add(optionalPlayer.get().getId());
        }
        game.setPlayers(players);

        gameRepository.save(game);
        return game;
    }
}
