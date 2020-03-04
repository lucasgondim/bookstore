package com.marvel.comic.bookstore.controller;

import com.marvel.comic.bookstore.domain.Client;
import com.marvel.comic.bookstore.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    ClientService clientService;

    private static final Logger log = LoggerFactory.getLogger(ClientController.class);

    @Cacheable(cacheNames = "User", key = "#root.method.name")
    @GetMapping(path = "/users", produces = "application/json")
    public List<Client> getClients() {
        return clientService.getClients();
    }

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return clientService.getUserById(id)
                .map(record -> {
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/users", consumes = "application/json")
    public Client saveClient(@RequestBody Client client) {
        return clientService.saveClient(client);
    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return clientService.getUserById(id)
                .map(record -> {
                    clientService.deleteUser(record);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value="/users/{id}")
    public Client update(@PathVariable Long id, @RequestBody Client client) {
        return clientService.update(id, client);
    }

}
