package pt.ul.fc.css.example.demo;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import pt.ul.fc.css.example.demo.entities.AppUser;
import pt.ul.fc.css.example.demo.entities.Application;
import pt.ul.fc.css.example.demo.entities.Consultant;
import pt.ul.fc.css.example.demo.entities.DissertationTopic;
import pt.ul.fc.css.example.demo.entities.FinalDefense;
import pt.ul.fc.css.example.demo.entities.Masters;
import pt.ul.fc.css.example.demo.entities.Professor;
import pt.ul.fc.css.example.demo.entities.Student;
import pt.ul.fc.css.example.demo.entities.ThesisDefense;
import pt.ul.fc.css.example.demo.entities.ThesisExecution;
import pt.ul.fc.css.example.demo.repositories.ApplicationRepository;
import pt.ul.fc.css.example.demo.repositories.DefenseRepository;
import pt.ul.fc.css.example.demo.repositories.DissertationTopicRepository;
import pt.ul.fc.css.example.demo.repositories.MastersRepository;
import pt.ul.fc.css.example.demo.repositories.ThesisExecutionRepository;
import pt.ul.fc.css.example.demo.repositories.UserRepository;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(
			UserRepository userRepository,
			MastersRepository mastersRepository,
			DissertationTopicRepository dissertationTopicRepository,
			ThesisExecutionRepository thesisExecutionRepository,
			DefenseRepository defenseRepository,
			ApplicationRepository applicationRepository,
			PasswordEncoder passwordEncoder) {
		return (args) -> {
			createAndSaveUser(userRepository, passwordEncoder,
					new Professor("ronaldo", "password", "cristiano ronaldo"));
			createAndSaveUser(userRepository, passwordEncoder, new Professor("arguente", "password", "arguente"));
			createAndSaveUser(userRepository, passwordEncoder,
			new Professor("quaresma", "password", "ricardo quaresma"));
			createAndSaveUser(userRepository, passwordEncoder, new Professor("pjuri", "password", "presi juri"));
			createAndSaveUser(userRepository, passwordEncoder,
			new Consultant("consultant", "password", "jorge mendes", " sumol"));
			createAndSaveUser(userRepository, passwordEncoder,
					new Consultant("fc56380@alunos.fc.ul.pt", "password", "joao", " sumol"));
			createAndSaveUser(userRepository, passwordEncoder, new Professor("prof1", "password", "prof1"));
			createAndSaveUser(userRepository, passwordEncoder,
			new Professor("admin", "password", "vescomosouadmin", true));
			createAndSaveUser(userRepository, passwordEncoder, new Professor("prof2", "password", "prof2"));
			createAndSaveUser(userRepository, passwordEncoder, new Professor("prof3", "password", "prof3"));
			createAndSaveUser(userRepository, passwordEncoder, new Professor("prof4", "password", "prof4"));
			createAndSaveUser(userRepository, passwordEncoder, new Professor("prof5", "password", "prof5"));


			Masters theBestMaster = new Masters("GOLDENAI", (Professor) userRepository.findByUsername("ronaldo").get());
			Masters master2 = new Masters("INFORMATICA", (Professor) userRepository.findByUsername("quaresma").get());
			Masters master3 = new Masters("STOCK", (Professor) userRepository.findByUsername("pjuri").get());
			Masters master10 = new Masters("master10", (Professor) userRepository.findByUsername("prof1").get());
			Masters master9 = new Masters("master9", (Professor) userRepository.findByUsername("prof2").get());
			Masters master8 = new Masters("master8", (Professor) userRepository.findByUsername("prof3").get());
			Masters master7 = new Masters("master7", (Professor) userRepository.findByUsername("prof4").get());

			mastersRepository.save(theBestMaster);
			mastersRepository.save(master2);
			mastersRepository.save(master3);
			mastersRepository.save(master10);
			mastersRepository.save(master9);
			mastersRepository.save(master8);
			mastersRepository.save(master7);

			createAndSaveUser(userRepository, passwordEncoder,
					new Student("radiante", "password", "Marie Curie", 1903, 19.99, theBestMaster));
			createAndSaveUser(userRepository, passwordEncoder,
					new Student("student1", "password", "student1", 1903, 19.99, master10));
			createAndSaveUser(userRepository, passwordEncoder,
					new Student("student2", "password", "student2", 1903, 19.99, master9));
			createAndSaveUser(userRepository, passwordEncoder,
					new Student("student3", "password", "student3", 1903, 19.99, master8));
			createAndSaveUser(userRepository, passwordEncoder,
					new Student("student4", "password", "student4", 1903, 19.99, master7));
			createAndSaveUser(userRepository, passwordEncoder,
					new Student("student5", "password", "student5", 1903, 19.99, master7));
			createAndSaveUser(userRepository, passwordEncoder,
					new Student("student6", "password", "student6", 1903, 19.99, master7));
			createAndSaveUser(userRepository, passwordEncoder,
					new Student("student7", "password", "student7", 1903, 19.99, master7));
			createAndSaveUser(userRepository, passwordEncoder,
					new Student("student8", "password", "student8", 1903, 19.99, master7));
			createAndSaveUser(userRepository, passwordEncoder,
					new Student("studentSemTopico1", "password", "studentSemTopico1", 1903, 19.99, master10));
			createAndSaveUser(userRepository, passwordEncoder,
					new Student("studentSemTopico2", "password", "studentSemTopico2", 1903, 19.99, master10));
			createAndSaveUser(userRepository, passwordEncoder,
					new Student("studentSemTopico3", "password", "studentSemTopico3", 1903, 19.99, master9));

			HashSet<Masters> mastersArr = new HashSet<>();
			mastersArr.add(theBestMaster);

			HashSet<Masters> mastersArr2 = new HashSet<>();
			mastersArr2.add(master8);
			mastersArr2.add(master10);
			mastersArr2.add(master9);
			mastersArr2.add(master7);

			HashSet<Masters> mastersArr9 = new HashSet<>();
			mastersArr9.add(master9);

			HashSet<Masters> mastersArr10 = new HashSet<>();
			mastersArr10.add(master10);

			DissertationTopic dissertationTopicFutebolRadiante = new DissertationTopic("Futebol radiante",
					"description", 99999, userRepository.findByUsername("quaresma").get(), mastersArr);
			DissertationTopic dissertationTopic0 = new DissertationTopic("1 sem defesa marcada", "description", 11111,
					userRepository.findByUsername("quaresma").get(), mastersArr2);
			DissertationTopic dissertationTopic1 = new DissertationTopic("2 sem defesa marcada", "description", 22222,
					userRepository.findByUsername("quaresma").get(), mastersArr2);
			DissertationTopic dissertationTopic6 = new DissertationTopic("3 sem defesa marcada", "description", 77777,
					userRepository.findByUsername("quaresma").get(), mastersArr2);
			DissertationTopic dissertationTopic2 = new DissertationTopic("1 1 defesa para marcar nota", "description",
					33333, userRepository.findByUsername("quaresma").get(), mastersArr2);
			DissertationTopic dissertationTopic3 = new DissertationTopic("2 1 defesa para marcar nota", "description",
					44444, userRepository.findByUsername("quaresma").get(), mastersArr2);
			DissertationTopic dissertationTopic4 = new DissertationTopic("1 passou 1 defesa e vai para a final",
					"description", 55555, userRepository.findByUsername("quaresma").get(), mastersArr2);
			DissertationTopic dissertationTopic5 = new DissertationTopic("2 passou 1 defesa e vai para a final",
					"description", 66666, userRepository.findByUsername("quaresma").get(), mastersArr2);
			DissertationTopic dissertationTopicNaoAssociado1 = new DissertationTopic("dissertationTopicNaoAssociado1",
					"description", 66666, userRepository.findByUsername("quaresma").get(), mastersArr9);
			DissertationTopic dissertationTopicNaoAssociado2 = new DissertationTopic("dissertationTopicNaoAssociado2",
					"description", 66666, userRepository.findByUsername("quaresma").get(), mastersArr10);

			dissertationTopicRepository.save(dissertationTopicNaoAssociado1);
			dissertationTopicRepository.save(dissertationTopicNaoAssociado2);
			dissertationTopicRepository.save(dissertationTopic0);
			dissertationTopicRepository.save(dissertationTopic1);
			dissertationTopicRepository.save(dissertationTopic2);
			dissertationTopicRepository.save(dissertationTopic3);
			dissertationTopicRepository.save(dissertationTopic4);
			dissertationTopicRepository.save(dissertationTopic5);
			dissertationTopicRepository.save(dissertationTopic6);

			ThesisExecution thesis1 = new ThesisExecution((Student) userRepository.findByUsername("student1").get(),
					dissertationTopic0, "2024/2025");
			ThesisExecution thesis2 = new ThesisExecution((Student) userRepository.findByUsername("student2").get(),
					dissertationTopic1, "2024/2025");
			ThesisExecution thesis3 = new ThesisExecution((Student) userRepository.findByUsername("student3").get(),
					dissertationTopic6, "2024/2025");
			ThesisExecution thesis4 = new ThesisExecution((Student) userRepository.findByUsername("student4").get(),
					dissertationTopic2, "2024/2025");
			ThesisExecution thesis5 = new ThesisExecution((Student) userRepository.findByUsername("student5").get(),
					dissertationTopic3, "2024/2025");

			thesisExecutionRepository.save(thesis1);
			thesisExecutionRepository.save(thesis2);
			thesisExecutionRepository.save(thesis3);
			thesisExecutionRepository.save(thesis4);
			thesisExecutionRepository.save(thesis5);

			ThesisDefense defense1 = new ThesisDefense(thesis4, "local_defesa_1", new Date(),
					(Professor) userRepository.findByUsername("arguente").get());
			ThesisDefense defense2 = new ThesisDefense(thesis5, "local_defesa_2", new Date(),
					(Professor) userRepository.findByUsername("arguente").get());

			defenseRepository.save(defense1);
			defenseRepository.save(defense2);

			ThesisExecution thesis6 = new ThesisExecution((Student) userRepository.findByUsername("student6").get(),
					dissertationTopic4, "2024/2025");
			ThesisExecution thesis7 = new ThesisExecution((Student) userRepository.findByUsername("student7").get(),
					dissertationTopic5, "2024/2025");

			thesisExecutionRepository.save(thesis6);
			thesisExecutionRepository.save(thesis7);

			ThesisDefense defense3 = new ThesisDefense(thesis6, "local_defesa_3", new Date(),
					(Professor) userRepository.findByUsername("arguente").get());
			ThesisDefense defense4 = new ThesisDefense(thesis7, "local_defesa_4", new Date(),
					(Professor) userRepository.findByUsername("arguente").get());
			defense3.setGrade(10);
			defense4.setGrade(11);

			defenseRepository.save(defense3);
			defenseRepository.save(defense4);

			DissertationTopic dissertationTopic7 = new DissertationTopic("chumbou a 1 primeira defesa", "description",
					99999, userRepository.findByUsername("quaresma").get(), mastersArr2);
			dissertationTopicRepository.save(dissertationTopic7);

			ThesisExecution thesis8 = new ThesisExecution((Student) userRepository.findByUsername("student8").get(),
					dissertationTopic7, "2024/2025");
			thesisExecutionRepository.save(thesis8);

			ThesisDefense defense5 = new ThesisDefense(thesis8, "local_defesa_5", new Date(),
					(Professor) userRepository.findByUsername("arguente").get());
			defense5.setGrade(8);
			defenseRepository.save(defense5);

			Application application1 = new Application((Student) userRepository.findByUsername("student1").get(),
					dissertationTopic1);
			Application application2 = new Application((Student) userRepository.findByUsername("student2").get(),
					dissertationTopic2);
			Application application3 = new Application((Student) userRepository.findByUsername("student3").get(),
					dissertationTopic3);

			applicationRepository.save(application1);
			applicationRepository.save(application2);
			applicationRepository.save(application3);

			createAndSaveUser(userRepository, passwordEncoder,
					new Consultant("consultant", "password", "jorge mendes", " sumol"));
			createAndSaveUser(userRepository, passwordEncoder,
					new Consultant("fc56380@alunos.fc.ul.pt", "password", "joao", " sumol"));

			DissertationTopic topicoConsultant = new DissertationTopic("topicoConsultot", "descioqe", 0,
					userRepository.findByUsername("consultant").get(), mastersArr9);
			dissertationTopicRepository.save(topicoConsultant);

			createAndSaveUser(userRepository, passwordEncoder, new Professor("pjuri", "password", "presi juri"));
			createAndSaveUser(userRepository, passwordEncoder,
					new Professor("ronaldo", "password", "cristiano ronaldo"));

			//mastersRepository.save(theBestMaster);

			createAndSaveUser(userRepository, passwordEncoder,
					new Student("radiante", "password", "Marie Curie", 1903, 19.99, theBestMaster));

			dissertationTopicRepository.save(dissertationTopicFutebolRadiante);

			ThesisExecution thesis = new ThesisExecution((Student) userRepository.findByUsername("radiante").get(),
					dissertationTopicFutebolRadiante, "2024/2025");
			thesisExecutionRepository.save(thesis);

			ThesisDefense defense = new ThesisDefense(thesis, "LOCATION", new Date(),
					(Professor) userRepository.findByUsername("arguente").get());
			defense.setGrade(17);

			FinalDefense finalDefense = new FinalDefense(thesis, "FINAL_LOCATION", new Date(),
					(Professor) userRepository.findByUsername("pjuri").get(), (Professor) userRepository.findByUsername("arguente").get());
			finalDefense.setGrade(18);
			defenseRepository.save(defense);

			defenseRepository.save(finalDefense);


			Application application = new Application((Student) userRepository.findByUsername("radiante").get(),
					dissertationTopicFutebolRadiante);
			applicationRepository.save(application);
		};
	}

	private void createAndSaveUser(UserRepository userRepository, PasswordEncoder passwordEncoder, Professor user) {
		Optional<AppUser> existingUser = userRepository.findByUsername(user.getUsername());
		if (!existingUser.isPresent()) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(user);
		} else {
			System.out.println("User with username '" + user.getUsername() + "' already exists.");
		}
	}

	private void createAndSaveUser(UserRepository userRepository, PasswordEncoder passwordEncoder, Consultant user) {
		Optional<AppUser> existingUser = userRepository.findByUsername(user.getUsername());
		if (!existingUser.isPresent()) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(user);
		} else {
			System.out.println("User with username '" + user.getUsername() + "' already exists.");
		}
	}

	private void createAndSaveUser(UserRepository userRepository, PasswordEncoder passwordEncoder, Student user) {
		Optional<AppUser> existingUser = userRepository.findByUsername(user.getUsername());
		if (!existingUser.isPresent()) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(user);
		} else {
			System.out.println("User with username '" + user.getUsername() + "' already exists.");
		}
	}
}
