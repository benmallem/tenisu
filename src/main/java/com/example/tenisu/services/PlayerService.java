package com.example.tenisu.services;

import com.example.tenisu.models.Player;
import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    public Player[] getPlayers() throws Exception {
        try {
            URL url = new URL("https://data.latelier.co/training/tennis_stats/headtohead.json");
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(new InputStreamReader(url.openStream()));
            JSONArray playersList = (JSONArray) json.get("players");

            String playersSerialized = playersList.toJSONString();
            Gson gson = new Gson();
            return gson.fromJson(playersSerialized, Player[].class);
        } catch (Exception error) {
            throw new Exception("Couldn't get players from the source url");
        }
    }

    public double getAverageImc(Player[] players) throws Exception {
        if (players.length == 0) {
            throw new Exception("No players found");
        }
        return Arrays.stream(players).mapToDouble(Player::getImc).average().getAsDouble();
    }

    public int getMedianHeight(Player[] players) throws Exception {
        if (players.length == 0) {
            throw new Exception("No players found");
        }
        int[] heights = Arrays.stream(players).sorted(Comparator.comparingInt(player -> player.getData().getHeight()))
                .mapToInt(player -> player.getData().getHeight())
                .toArray();
        int length = heights.length;
        if (length % 2 == 0) {
            int value1 = heights[length / 2 - 1];
            int value2 = heights[length / 2];
            return (value1 + value2) / 2;
        } else {
            return heights[length / 2];
        }
    }

    public String getCountryWithHighestWinRatio(Player[] players) throws Exception {
        if (players.length == 0) {
            throw new Exception("No players found");
        }
        return Arrays.stream(players).collect(Collectors.groupingBy(player -> player.getCountry().getCode(), Collectors.averagingDouble(Player::getWinRatio)))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get().getKey();
    }
}
