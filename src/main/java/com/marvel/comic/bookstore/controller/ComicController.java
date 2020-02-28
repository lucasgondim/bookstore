package com.marvel.comic.bookstore.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.marvel.comic.bookstore.domain.ComicRepository;
import com.marvel.comic.bookstore.model.Comic;
import com.marvel.comic.bookstore.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@RestController
public class ComicController {

    @Autowired
    Environment env;

    @Autowired
    ComicRepository comicRepository;

    @GetMapping(path = "/comics", produces = "application/json")
    public List<Comic> getComics() {
        return comicRepository.findAll();
    }

    @GetMapping(path = "/save/comics", produces = "application/json")
    public JsonObject saveComics() {
        try {
            URL url = new URL(Utils.getComicUrl(env));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
            }

            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);

            //Gambis
            JsonObject convertedObject = new Gson().fromJson(br.readLine().replaceAll("\"","\\\""), JsonObject.class);

            JsonArray results = convertedObject.getAsJsonObject("data").getAsJsonArray("results");

            for (int i = 0; i < results.size(); i++) {

                Comic comic = new Comic();

                JsonElement id = results.get(i).getAsJsonObject().get("id");
                comic.setId(id.getAsLong());
                JsonElement title = results.get(i).getAsJsonObject().get("title");
                comic.setTitle(title.getAsString());
                JsonElement desc = results.get(i).getAsJsonObject().get("description");
                comic.setDescription((!desc.isJsonNull()) ? desc.getAsString() : "");
                comicRepository.save(comic);
            }

            conn.disconnect();
            return results.get(0).getAsJsonObject();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
