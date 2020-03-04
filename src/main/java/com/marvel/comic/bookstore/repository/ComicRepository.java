package com.marvel.comic.bookstore.repository;


import com.marvel.comic.bookstore.domain.Comic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComicRepository extends JpaRepository<Comic, Long> {

    List<Comic> findByTitle(String title);

    List<Comic> findAll();
}
