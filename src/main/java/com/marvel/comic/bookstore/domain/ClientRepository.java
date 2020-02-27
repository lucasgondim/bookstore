package com.marvel.comic.bookstore.domain;


import com.marvel.comic.bookstore.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {

    List<Client> findByName(String name);

    Client findById(long id);

    List<Client> findAll();
}
