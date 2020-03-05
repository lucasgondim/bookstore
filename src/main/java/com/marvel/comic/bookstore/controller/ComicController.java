package com.marvel.comic.bookstore.controller;

import com.marvel.comic.bookstore.domain.Comic;
import com.marvel.comic.bookstore.service.ComicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ComicController {

    private ComicService comicService;

    @Autowired
    public ComicController(ComicService comicService) {
        this.comicService = comicService;
    }

    @GetMapping(path = "/comics", produces = "application/json")
    public List<Comic> getComics() {
        return comicService.getComics();
    }

    @GetMapping(path = "/save/comics", produces = "application/json")
    public List<Comic> saveComics() {
        return comicService.saveComics();
    }
}
