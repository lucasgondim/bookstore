package com.marvel.comic.bookstore.domain;


import com.marvel.comic.bookstore.model.Comic;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ComicRepository extends CrudRepository<Comic, Long> {

    List<Comic> findByTitle(String title);

    Comic findById(long id);

    List<Comic> findAll();
}
