package com.marvel.comic.bookstore.controller;

import com.marvel.comic.bookstore.model.Client;
import com.marvel.comic.bookstore.domain.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @RequestMapping(value = "/clients", method = RequestMethod.PUT)
    public List<Client> getAllClients() {
        return clientRepository.getAllClients();
    }

    public void updateClient(Long id) {
        clientRepository.updateClient(id);
    }

    public void createClient(Client client) {
        clientRepository.createClient(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteClient();
    }

}
