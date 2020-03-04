package com.marvel.comic.bookstore.service;

import com.marvel.comic.bookstore.domain.Comic;
import com.marvel.comic.bookstore.pojo.ComicMarvel;
import com.marvel.comic.bookstore.pojo.Result;
import com.marvel.comic.bookstore.repository.ComicRepository;
import com.marvel.comic.bookstore.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComicService {

    @Autowired
    private Environment env;

    @Autowired
    private ComicRepository comicRepository;

    public List<Comic> getComics() {
        return comicRepository.findAll();
    }

    public List<Comic> saveComics() {
        try {

            List<Comic> comics = new ArrayList<>();

            RestTemplate restTemplate = new RestTemplate();
            ComicMarvel comicMarvel = restTemplate.getForObject(Utils.getComicUrl(env), ComicMarvel.class);

            for (Result result : comicMarvel.getData().getResults()) {

                Comic comic = new Comic();
                comic.setId(result.getId());
                comic.setTitle(result.getTitle());
                comic.setDescription((result.getDescription() != null) ? result.getDescription() : "");
                if(!ComicAlreadyExists(result.getId())) {
                    Comic c = comicRepository.save(comic);
                    comics.add(c);
                }
            }

            return comics;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean ComicAlreadyExists(Long id) {
        return comicRepository.existsById(id);
    }
}
