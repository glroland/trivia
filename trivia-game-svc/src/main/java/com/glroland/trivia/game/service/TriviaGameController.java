package com.glroland.trivia.game.service;

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

    @GetMapping("/newGame")
    @CrossOrigin(origins = "*")
    public void newGame()
    {

    }

    @GetMapping("/createPlayer")
    @CrossOrigin(origins = "*")
    public Player createPlayer(String name, String email)
    {
        Player o = new Player();
        o.setName(name);
        o.setEmail(email);
        playerRepository.save(o);

        return o;
    }
}