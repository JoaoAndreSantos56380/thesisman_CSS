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
					new Professor("BlackHole", "password", "Stephen Hawking"));
			createAndSaveUser(userRepository, passwordEncoder, new Professor("arguente", "password", "arguente"));
			createAndSaveUser(userRepository, passwordEncoder,
					new Professor("FermiParadox", "password", "Enrico Fermi"));
			createAndSaveUser(userRepository, passwordEncoder, new Professor("pjuri", "password", "presi juri"));
			createAndSaveUser(userRepository, passwordEncoder,
					new Consultant("ManhattanProj", "password", "Robert Oppenheimer", "USgov"));
			createAndSaveUser(userRepository, passwordEncoder,
					new Consultant("fc56380@alunos.fc.ul.pt", "password", "joao", " sumol"));
			createAndSaveUser(userRepository, passwordEncoder, new Professor("prof1", "password", "prof1"));
			createAndSaveUser(userRepository, passwordEncoder,
					new Professor("admin", "password", "admin_root", true));
			createAndSaveUser(userRepository, passwordEncoder, new Professor("Max", "password", "Planck"));
			createAndSaveUser(userRepository, passwordEncoder, new Professor("Michael", "password", "Faraday"));
			createAndSaveUser(userRepository, passwordEncoder, new Professor("prof4", "password", "prof4"));
			createAndSaveUser(userRepository, passwordEncoder, new Professor("prof5", "password", "prof5"));

			Masters theBestMaster = new Masters("GOLDENAI",
					(Professor) userRepository.findByUsername("ronaldo").get());
			Masters master2 = new Masters("INFORMATICA",
					(Professor) userRepository.findByUsername("quaresma").get());
			Masters master3 = new Masters("STOCK", (Professor) userRepository.findByUsername("pjuri").get());
			Masters master10 = new Masters("master10", (Professor) userRepository.findByUsername("prof1").get());
			Masters master9 = new Masters("master9", (Professor) userRepository.findByUsername("prof2").get());
			Masters master8 = new Masters("master8", (Professor) userRepository.findByUsername("prof3").get());
			Masters master7 = new Masters("master7", (Professor) userRepository.findByUsername("prof4").get());

			createAndSaveMaster(mastersRepository, theBestMaster);
			createAndSaveMaster(mastersRepository, master2);
			createAndSaveMaster(mastersRepository, master3);
			createAndSaveMaster(mastersRepository, master10);
			createAndSaveMaster(mastersRepository, master9);
			createAndSaveMaster(mastersRepository, master8);
			createAndSaveMaster(mastersRepository, master7);

			createAndSaveUser(userRepository, passwordEncoder,
					new Student("Radio", "password", "Marie Curie", 1903, 19.99, theBestMaster));
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

			DissertationTopic dissertationTopicRadio = new DissertationTopic(" Radio",
					"description", 99999, userRepository.findByUsername("quaresma").get(), mastersArr);
			DissertationTopic dissertationTopic0 = new DissertationTopic("1 sem defesa marcada", "description",
					11111,
					userRepository.findByUsername("quaresma").get(), mastersArr2);
			DissertationTopic dissertationTopic1 = new DissertationTopic("2 sem defesa marcada", "description",
					22222,
					userRepository.findByUsername("quaresma").get(), mastersArr2);
			DissertationTopic dissertationTopic6 = new DissertationTopic("3 sem defesa marcada", "description",
					77777,
					userRepository.findByUsername("quaresma").get(), mastersArr2);
			DissertationTopic dissertationTopic2 = new DissertationTopic("1 1 defesa para marcar nota",
					"description",
					33333, userRepository.findByUsername("quaresma").get(), mastersArr2);
			DissertationTopic dissertationTopic3 = new DissertationTopic("2 1 defesa para marcar nota",
					"description",
					44444, userRepository.findByUsername("quaresma").get(), mastersArr2);
			DissertationTopic dissertationTopic4 = new DissertationTopic("1 passou 1 defesa e vai para a final",
					"description", 55555, userRepository.findByUsername("quaresma").get(), mastersArr2);
			DissertationTopic dissertationTopic5 = new DissertationTopic("2 passou 1 defesa e vai para a final",
					"description", 66666, userRepository.findByUsername("quaresma").get(), mastersArr2);
			DissertationTopic dissertationTopicNaoAssociado1 = new DissertationTopic(
					"dissertationTopicNaoAssociado1",
					"description", 66666, userRepository.findByUsername("quaresma").get(), mastersArr9);
			DissertationTopic dissertationTopicNaoAssociado2 = new DissertationTopic(
					"dissertationTopicNaoAssociado2",
					"description", 66666, userRepository.findByUsername("quaresma").get(), mastersArr10);

			createAndSaveDissertation(dissertationTopicRepository, dissertationTopicNaoAssociado1);
			createAndSaveDissertation(dissertationTopicRepository, dissertationTopicNaoAssociado2);
			createAndSaveDissertation(dissertationTopicRepository, dissertationTopic0);
			createAndSaveDissertation(dissertationTopicRepository, dissertationTopic1);
			createAndSaveDissertation(dissertationTopicRepository, dissertationTopic2);
			createAndSaveDissertation(dissertationTopicRepository, dissertationTopic3);
			createAndSaveDissertation(dissertationTopicRepository, dissertationTopic4);
			createAndSaveDissertation(dissertationTopicRepository, dissertationTopic5);
			createAndSaveDissertation(dissertationTopicRepository, dissertationTopic6);

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

			createAndSaveThesisExecution(thesisExecutionRepository, thesis1);
			createAndSaveThesisExecution(thesisExecutionRepository, thesis2);
			createAndSaveThesisExecution(thesisExecutionRepository, thesis3);
			createAndSaveThesisExecution(thesisExecutionRepository, thesis4);
			createAndSaveThesisExecution(thesisExecutionRepository, thesis5);

			ThesisDefense defense1 = new ThesisDefense(thesis4, "local_defesa_1", new Date(),
					(Professor) userRepository.findByUsername("arguente").get());
			ThesisDefense defense2 = new ThesisDefense(thesis5, "local_defesa_2", new Date(),
					(Professor) userRepository.findByUsername("arguente").get());

			createAndSaveDefense(defenseRepository, defense1);
			createAndSaveDefense(defenseRepository, defense2);

			ThesisExecution thesis6 = new ThesisExecution((Student) userRepository.findByUsername("student6").get(),
					dissertationTopic4, "2024/2025");
			ThesisExecution thesis7 = new ThesisExecution((Student) userRepository.findByUsername("student7").get(),
					dissertationTopic5, "2024/2025");

			createAndSaveThesisExecution(thesisExecutionRepository, thesis6);
			createAndSaveThesisExecution(thesisExecutionRepository, thesis7);

			ThesisDefense defense3 = new ThesisDefense(thesis6, "local_defesa_3", new Date(),
					(Professor) userRepository.findByUsername("arguente").get());
			ThesisDefense defense4 = new ThesisDefense(thesis7, "local_defesa_4", new Date(),
					(Professor) userRepository.findByUsername("arguente").get());
			defense3.setGrade(10);
			defense4.setGrade(11);

			createAndSaveDefense(defenseRepository, defense3);
			createAndSaveDefense(defenseRepository, defense4);

			DissertationTopic dissertationTopic7 = new DissertationTopic("chumbou a 1 primeira defesa",
					"description",
					99999, userRepository.findByUsername("quaresma").get(), mastersArr2);
			createAndSaveDissertation(dissertationTopicRepository, dissertationTopic7);

			ThesisExecution thesis8 = new ThesisExecution((Student) userRepository.findByUsername("student8").get(),
					dissertationTopic7, "2024/2025");
			createAndSaveThesisExecution(thesisExecutionRepository, thesis8);

			ThesisDefense defense5 = new ThesisDefense(thesis8, "local_defesa_5", new Date(),
					(Professor) userRepository.findByUsername("arguente").get());
			defense5.setGrade(8);
			createAndSaveDefense(defenseRepository, defense5);

			Application application1 = new Application((Student) userRepository.findByUsername("student1").get(),
					dissertationTopic1);
			Application application2 = new Application((Student) userRepository.findByUsername("student2").get(),
					dissertationTopic2);
			Application application3 = new Application((Student) userRepository.findByUsername("student3").get(),
					dissertationTopic3);

			createAndSaveApplication(applicationRepository, application1);
			createAndSaveApplication(applicationRepository, application2);
			createAndSaveApplication(applicationRepository, application3);

			createAndSaveUser(userRepository, passwordEncoder,
					new Consultant("consultant", "password", "jorge mendes", " sumol"));
			createAndSaveUser(userRepository, passwordEncoder,
					new Consultant("fc56380@alunos.fc.ul.pt", "password", "joao", " sumol"));

			DissertationTopic topicoConsultant = new DissertationTopic("topicoConsultor", "descricao", 0,
					userRepository.findByUsername("consultant").get(), mastersArr9);
			createAndSaveDissertation(dissertationTopicRepository, topicoConsultant);

			createAndSaveUser(userRepository, passwordEncoder, new Professor("pjuri", "password", "presi juri"));
			createAndSaveUser(userRepository, passwordEncoder,
					new Professor("Einstein", "password", "Albert Einstein"));

			createAndSaveUser(userRepository, passwordEncoder,
					new Student("Radio", "password", "Marie Curie", 1903, 19.99, theBestMaster));

			createAndSaveDissertation(dissertationTopicRepository, dissertationTopicRadio);

			ThesisExecution thesis = new ThesisExecution((Student) userRepository.findByUsername("Radio").get(),
					dissertationTopicRadio, "2024/2025");
			createAndSaveThesisExecution(thesisExecutionRepository, thesis);

			ThesisDefense defense = new ThesisDefense(thesis, "LOCATION", new Date(),
					(Professor) userRepository.findByUsername("arguente").get());
			defense.setGrade(17);

			FinalDefense finalDefense = new FinalDefense(thesis, "FINAL_LOCATION", new Date(),
					(Professor) userRepository.findByUsername("pjuri").get(),
					(Professor) userRepository.findByUsername("arguente").get());
			finalDefense.setGrade(18);
			createAndSaveDefense(defenseRepository, defense);

			createAndSaveDefense(defenseRepository, finalDefense);

			Application application = new Application((Student) userRepository.findByUsername("Radio").get(),
					dissertationTopicRadio);
			createAndSaveApplication(applicationRepository, application);
		};
	}

	private void createAndSaveApplication(ApplicationRepository repo, Application thing) {
		repo.save(thing);
	}

	private void createAndSaveDissertation(DissertationTopicRepository repo, DissertationTopic thing) {
		Optional<DissertationTopic> maybe = repo.findByTitle(thing.getTitle());
		if (!maybe.isPresent()) {
			repo.save(thing);
		} else {
			System.out.println("Topic with title '" + thing.getTitle() + "' already exists.");
		}
	}

	private void createAndSaveThesisExecution(ThesisExecutionRepository repo, ThesisExecution thing) {
		Optional<ThesisExecution> maybe = repo.findByStudentID(thing.getStudent().getId());
		if (!maybe.isPresent()) {
			repo.save(thing);
		} else {
			System.out.println("ThesisExecution with Student ID '" + thing.getStudent().getId() + "' already exists.");
		}
	}

	private void createAndSaveDefense(DefenseRepository repo, ThesisDefense thing) {
		repo.save(thing);
	}

	private void createAndSaveUser(UserRepository repo, PasswordEncoder passwordEncoder, Professor thing) {
		Optional<AppUser> existingUser = repo.findByUsername(thing.getUsername());
		if (!existingUser.isPresent()) {
			thing.setPassword(passwordEncoder.encode(thing.getPassword()));
			repo.save(thing);
		} else {
			System.out.println("User with username '" + thing.getUsername() + "' already exists.");
		}
	}

	private void createAndSaveUser(UserRepository repo, PasswordEncoder passwordEncoder, Consultant thing) {
		Optional<AppUser> existingUser = repo.findByUsername(thing.getUsername());
		if (!existingUser.isPresent()) {
			thing.setPassword(passwordEncoder.encode(thing.getPassword()));
			repo.save(thing);
		} else {
			System.out.println("User with username '" + thing.getUsername() + "' already exists.");
		}
	}

	private void createAndSaveUser(UserRepository repo, PasswordEncoder passwordEncoder, Student thing) {
		Optional<AppUser> existingUser = repo.findByUsername(thing.getUsername());
		if (!existingUser.isPresent()) {
			thing.setPassword(passwordEncoder.encode(thing.getPassword()));
			repo.save(thing);
		} else {
			System.out.println("User with username '" + thing.getUsername() + "' already exists.");
		}
	}

	private void createAndSaveMaster(MastersRepository repo, Masters thing) {
		Optional<Masters> existingMasters = repo.findByName(thing.getName());
		if (!existingMasters.isPresent()) {
			repo.save(thing);
		} else {
			System.out.println("Masters with name '" + thing.getName() + "' already exists.");
		}
	}
}