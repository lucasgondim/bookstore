package com.marvel.comic.bookstore.service;

import com.marvel.comic.bookstore.domain.Client;
import com.marvel.comic.bookstore.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Cacheable(cacheNames = "User", key = "#root.method.name")
    public List<Client> getClients() {
        return clientRepository.findAll();
    }
    public Client getUserById(Long id) {
        return clientRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteUser(Long id) {
        clientRepository.delete(getUserById(id));
    }

    public Client update(Long id, Client client) {
        client.setName(client.getName());
        client.setEmail(client.getEmail());
        client.setAge(client.getAge());
        Client updated = clientRepository.save(client);
        return updated;
    }

}
