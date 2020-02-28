package com.marvel.comic.bookstore.controller;

import com.marvel.comic.bookstore.domain.ClientRepository;
import com.marvel.comic.bookstore.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

@RestController
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @GetMapping(path = "/users", produces = "application/json")
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return clientRepository.findById(id)
                .map(record -> {
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/users", consumes = "application/json")
    public Client saveClient(@RequestBody Client client) {

        /*byte[] decodedImg = Base64.getDecoder()
                .decode(encodedImg.getBytes(StandardCharsets.UTF_8));
        Path destinationFile = Paths.get("/path/to/imageDir", "myImage.jpg");

        try {
            Files.write(destinationFile, decodedImg);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        return clientRepository.save(client);
    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return clientRepository.findById(id)
                .map(record -> {
                    clientRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value="/users/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Client client) {
        return clientRepository.findById(id)
                .map(record -> {
                    record.setName(client.getName());
                    record.setEmail(client.getEmail());
                    record.setAge(client.getAge());
                    Client updated = clientRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

}
