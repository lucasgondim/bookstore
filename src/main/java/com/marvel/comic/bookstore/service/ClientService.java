package com.marvel.comic.bookstore.service;

import com.marvel.comic.bookstore.model.Client;

import java.util.List;

public interface ClientService {

    List<Client> getAllClients();

    void updateClient(Long id);

    void createClient(Client client);

    void deleteClient(Long id);
}
