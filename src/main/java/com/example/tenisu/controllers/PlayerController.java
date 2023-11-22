package com.example.tenisu.controllers;

import com.example.tenisu.models.Player;
import com.example.tenisu.models.Statistics;
import com.example.tenisu.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class PlayerController {

    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/players")
    public List<Player> getSortedPlayers() throws Exception {
        Player[] players = playerService.getPlayers();
        return Arrays.stream(players).sorted(Comparator.comparingInt(player -> player.getData().getRank()))
                                            .collect(Collectors.toList());
    }

    @GetMapping("/players/{id}")
    public ResponseEntity<?> getPlayerById(@PathVariable Long id) throws Exception {
        Player[] players = playerService.getPlayers();
        Optional<Player> foundPlayer = Arrays.stream(players).filter(player -> player.getId() == id)
                                              .findFirst();
        if(foundPlayer.isPresent()) {
            return ResponseEntity.ok(foundPlayer);
        } else {
            String message = "Sorry, no player found with ID: " + id ;
            Map<String, String> noPlayerFound = Map.of("error", message);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noPlayerFound);
        }
    }

    @GetMapping("/statistics")
    public Statistics getStatistics() throws Exception {
        Player[] players = playerService.getPlayers();
        double imc = playerService.getAverageImc(players);
        String countryWithHighestWinRatio = playerService.getCountryWithHighestWinRatio(players);
        double medianHeight = playerService.getMedianHeight(players);
        Statistics statistics = new Statistics(imc, medianHeight, countryWithHighestWinRatio);
        return statistics;
    }
}
