package com.marvel.comic.bookstore.controller;

import com.marvel.comic.bookstore.pojo.ComicMarvel;
import com.marvel.comic.bookstore.pojo.Result;
import com.marvel.comic.bookstore.repository.ComicRepository;
import com.marvel.comic.bookstore.domain.Comic;
import com.marvel.comic.bookstore.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ComicController {

    @Autowired
    private Environment env;

    @Autowired
    private ComicRepository comicRepository;
    
    @GetMapping(path = "/comics", produces = "application/json")
    public List<Comic> getComics() {
        return comicRepository.findAll();
    }

    @GetMapping(path = "/save/comics", produces = "application/json")
    public ComicMarvel saveComics() {
        try {

            RestTemplate restTemplate = new RestTemplate();
            ComicMarvel comicMarvel = restTemplate.getForObject(Utils.getComicUrl(env), ComicMarvel.class);

            for (Result result : comicMarvel.getData().getResults()) {

                Comic comic = new Comic();
                comic.setId(result.getId());
                comic.setTitle(result.getTitle());
                comic.setDescription((result.getDescription() != null) ? result.getDescription() : "");
                comicRepository.save(comic);
            }

            return comicMarvel;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
