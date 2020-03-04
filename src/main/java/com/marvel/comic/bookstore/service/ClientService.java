package com.marvel.comic.bookstore.service;

import com.marvel.comic.bookstore.domain.Client;
import com.marvel.comic.bookstore.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public List<Client> getClients() {
        return clientRepository.findAll();
    }
    public Optional<Client> getUserById(@PathVariable Long id) {
        return clientRepository.findById(id);
    }

    public Client saveClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    public void deleteUser(@PathVariable Client client) {
        clientRepository.deleteById(client.getId());
    }

    public Client update(@PathVariable Long id, @RequestBody Client client) {
        client.setName(client.getName());
        client.setEmail(client.getEmail());
        client.setAge(client.getAge());
        Client updated = clientRepository.save(client);
        return updated;
    }

}
