package com.glroland.trivia.game.service;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import com.glroland.trivia.game.entities.Player;
import com.glroland.trivia.game.data.PlayerRepository;

@RestController
public class TriviaGameController {
    
    private static final Logger log = LoggerFactory.getLogger(TriviaGameController.class);
    
    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping("/purge")
    @CrossOrigin(origins = "*")
    public void purge()
    {
        log.warn("Purging entire database.  Fun times!");
        playerRepository.deleteAll();
    }

    @GetMapping("/newGame")
    @CrossOrigin(origins = "*")
    public void newGame(String playerId)
    {

    }

    @GetMapping("/signin")
    @CrossOrigin(origins = "*")
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
}
