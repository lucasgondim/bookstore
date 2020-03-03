package com.marvel.comic.bookstore;

import com.marvel.comic.bookstore.controller.ClientController;
import com.marvel.comic.bookstore.controller.ComicController;
import com.marvel.comic.bookstore.model.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest
public class ClientApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientController clientController;

    @MockBean
    private ComicController comicController;

    @Test
    public void getClientsSuccess() throws Exception {

        Client client = new Client();
        client.setId(1L);
        client.setName("Teste");
        client.setAge(18);
        client.setEmail("test@test.com");

        List<Client> clients = Arrays.asList(client);
        given(clientController.getClients()).willReturn(clients);

        this.mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id': 1,'name': 'Teste','age': 18, 'email': 'test@test.com'}]"));
    }

}
