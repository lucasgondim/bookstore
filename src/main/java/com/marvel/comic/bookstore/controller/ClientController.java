package com.marvel.comic.bookstore.controller;

import com.marvel.comic.bookstore.domain.Client;
import com.marvel.comic.bookstore.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(path = "/users", produces = "application/json")
    public List<Client> getClients() {
        return clientService.getClients();
    }

    @GetMapping(path = "/users/{id}")
    public Client getUserById(@PathVariable Long id) {
        return clientService.getUserById(id);
    }

    @PostMapping(path = "/users", consumes = "application/json")
    public Client saveClient(@RequestBody Client client) {
        return clientService.saveClient(client);
    }

    @DeleteMapping(path = "/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        clientService.deleteUser(id);
    }

    @PutMapping(value="/users/{id}")
    public Client update(@PathVariable Long id, @RequestBody Client client) {
        return clientService.update(id, client);
    }

}
