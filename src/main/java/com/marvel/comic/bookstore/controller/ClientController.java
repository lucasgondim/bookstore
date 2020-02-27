package com.marvel.comic.bookstore.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.marvel.comic.bookstore.domain.ClientRepository;
import com.marvel.comic.bookstore.model.Client;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ClientController {

    private static String link = "http://gateway.marvel.com/v1/public/comics";
    private static String ts = "1";
    private static String apikey = "5a04d3d38a90cb9b801d9e248f438ff8";
    private static String privatekey = "e70d6c2d9c7c55a4675975efa43eaf37bfc7e408";

    @Autowired
    ClientRepository clientRepository;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping(path = "/users", produces = "application/json")
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @GetMapping(path = "/comics", produces = "application/json")
    public JsonObject getComics() {
        try {
            URL url = new URL("http://gateway.marvel.com/v1/public/comics?ts=1&apikey=5a04d3d38a90cb9b801d9e248f438ff8&hash=9bb1d327d6e2c930ebb6f304dbfd323c");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            /*String output;
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }*/

            JsonObject convertedObject = new Gson().fromJson(br.readLine().replaceAll("\"","\\\""), JsonObject.class);

            JsonArray results = convertedObject.getAsJsonObject("data").getAsJsonArray("results");

            for (int i = 0; i < results.size(); i++) {
                JsonElement comic_id = results.get(i).getAsJsonObject().get("id");
                //System.out.println(comic_id);
            }

            conn.disconnect();
            //System.out.println("teste: " + convertedObject);
            return results.get(0).getAsJsonObject();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
