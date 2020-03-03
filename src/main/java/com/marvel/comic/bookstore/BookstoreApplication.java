package com.marvel.comic.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
		log.info("Hello from Log4j!!!!");
	}

	/*@Bean
	public CommandLineRunner demo(ClientRepository repository) {
		return (args) -> {
			// save a few clients
			repository.save(new Client("Jack", 33, "jack@gmail.com"));
			repository.save(new Client("Chloe", 23, "chloe@mail.com"));
			repository.save(new Client("Michele", 33, "michele@gmail.com"));
			repository.save(new Client("David", 23, "david@mail.com"));

			// fetch all clients
			log.info("Clients found with findAll():");
			log.info("-------------------------------");
			for (Client client : repository.findAll()) {
				log.info(client.toString());
			}
			log.info("");

			// fetch an individual client by ID
			Client client = repository.findById(1L);
			log.info("Client found with findById(1L):");
			log.info("--------------------------------");
			log.info(client.toString());
			log.info("");

			// fetch clients by last name
			log.info("Client found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});
		};
	} */
}
