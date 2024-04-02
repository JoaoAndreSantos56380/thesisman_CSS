package pt.ul.fc.css.example.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import pt.ul.fc.css.example.demo.entities.AppUser;
import pt.ul.fc.css.example.demo.entities.Author;
import pt.ul.fc.css.example.demo.entities.Masters;
import pt.ul.fc.css.example.demo.entities.Student;
import pt.ul.fc.css.example.demo.repositories.AuthorRepository;
import pt.ul.fc.css.example.demo.repositories.UserRepository;

@SpringBootApplication
public class DemoApplication {

    private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(AuthorRepository repository, UserRepository user) {
        return (args) -> {
            // save a few customers
            repository.save(new Author("Jack", "Bauer"));
            repository.save(new Author("Chloe", "O'Brian"));
            repository.save(new Author("Kim", "Bauer"));
            repository.save(new Author("David", "Palmer"));
            repository.save(new Author("Michelle", "Dessler"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Author author : repository.findAll()) {
                log.info(author.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            repository.findById(1L).ifPresent((Author author) -> {
                log.info("Customer found with findById(1L):");
                log.info("--------------------------------");
                log.info(author.toString());
                log.info("");
            });

            // fetch customers by last name
            log.info("Author found with findByName('Bauer'):");
            log.info("--------------------------------------------");
            repository.findByName("Bauer").forEach(bauer -> {
                log.info(bauer.toString());
            });
            // for (Customer bauer : repository.findByLastName("Bauer")) {
            // log.info(bauer.toString());
            // }
            log.info("");

			Student student = new Student("username", "password", "name", 56380, 13.48, null);
			System.out.println(student);
			user.save(student);
			List<AppUser> students = user.findByName("name");
			for (AppUser appUser : students) {
				System.out.println("appUser:");
				System.out.println(appUser.getName());
				System.out.println(appUser.getId());
				System.out.println(appUser.getPassword());
			}
			System.out.println("hello");
        };
    }

}
