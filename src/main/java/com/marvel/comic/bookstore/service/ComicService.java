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

    private Environment env;
    private ComicRepository comicRepository;
    private RestTemplate restTemplate;

    @Autowired
    public ComicService(Environment env, ComicRepository comicRepository, RestTemplate restTemplate) {
        this.env = env;
        this.comicRepository = comicRepository;
        this.restTemplate = restTemplate;
    }

    public List<Comic> getComics() {
        return comicRepository.findAll();
    }

    public List<Comic> saveComics() {
        try {

            List<Comic> comics = new ArrayList<>();

            ComicMarvel comicMarvel = restTemplate.getForObject(Utils.getComicUrl(env), ComicMarvel.class);

            if(comicMarvel == null || comicMarvel.getData() == null || comicMarvel.getData().getResults() == null) {
                throw new Exception("Erro na API da Marvel.");
            }

            for (Result result : comicMarvel.getData().getResults()) {

                Comic comic = new Comic();
                comic.setId(result.getId());
                comic.setTitle(result.getTitle());
                comic.setDescription((result.getDescription() != null) ? result.getDescription() : "");
                if(!comicAlreadyExists(result.getId())) {
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

    private boolean comicAlreadyExists(Long id) {
        return comicRepository.existsById(id);
    }
}
