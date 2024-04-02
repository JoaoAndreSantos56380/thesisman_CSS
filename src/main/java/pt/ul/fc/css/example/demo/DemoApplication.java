package pt.ul.fc.css.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.postgresql.translation.messages_bg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import pt.ul.fc.css.example.demo.entities.AppUser;
import pt.ul.fc.css.example.demo.entities.Application;
import pt.ul.fc.css.example.demo.entities.Consultant;
import pt.ul.fc.css.example.demo.entities.DissertationTopic;
import pt.ul.fc.css.example.demo.entities.Masters;
import pt.ul.fc.css.example.demo.entities.Professor;
import pt.ul.fc.css.example.demo.entities.Student;
import pt.ul.fc.css.example.demo.repositories.MastersRepository;
import pt.ul.fc.css.example.demo.repositories.UserRepository;

@SpringBootApplication
public class DemoApplication {

    private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRepository user, MastersRepository master) {
        return (args) -> {
			//criar professor
			Professor prof1 = new Professor("prof1", "passProf1", "profname1");
			//criar mestrado com esse prof a coordenador
			Masters master1 = new Masters("master1", prof1);
			ArrayList<Masters> masters = new ArrayList<Masters>();
			masters.add(master1);
			//criar consultor
			Consultant Consultant = new Consultant("consultant", "passwordconsul", "consultant1", "companhia1");
			//criar topico
			DissertationTopic topic = new DissertationTopic("topico1", "isto é um topico", 1.5, prof1, Consultant, masters);
			Student student = new Student("studentUsername", "password", "name", 56380, 13.48, null);

			Application application = new Application(student, topic);

			user.save(prof1);
			master.save(master1);
			ArrayList<Masters> mastersArray = (ArrayList<Masters>) master.findByName("master1");
			System.out.println(mastersArray.get(0).getName());


			/* Student student2 = new Student("username2", "password2", "name2", 56380, 13.48, null); */
			//System.out.println(student);
			/* System.out.println("ids de uma tabela vazia:");
			for (AppUser userx : user.findAll()) {
				System.out.println("ids de uma tabela vazia:");
				System.out.println(userx.getId());
			}
			user.save(student);
			user.save(student2);
			for (AppUser userx2 : user.findAll()) {
				System.out.println("ids de uma tabela nao vazia:");
				System.out.println(userx2.getId());
			} */
			/* List<AppUser> students = user.findByName("name");
			for (AppUser appUser : students) {
				System.out.println("appUser:");
				System.out.println(appUser.getName());
				System.out.println(appUser.getId());
				System.out.println(appUser.getPassword());
			}
			List<AppUser> students2 = user.findByName("name2");
			for (AppUser appUser : students2) {
				System.out.println("appUser2:");
				System.out.println(appUser.getName());
				System.out.println(appUser.getId());
				System.out.println(appUser.getPassword());
			} */
			/* ArrayList<AppUser> students = (ArrayList<AppUser>) user.findByName("name2");
			user.removeUser(students.get(0).getId());
			for (AppUser userx3 : user.findAll()) {
				System.out.println("ids de uma tabela sem um id:");
				System.out.println(userx3.getId());
			} */

			System.out.println("fim");
        };
    }

}
