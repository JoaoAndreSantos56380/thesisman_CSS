package pt.ul.fc.css.example.demo;

import java.util.Date;
import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

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

	// private static final Logger log =
	// LoggerFactory.getLogger(DemoApplication.class);

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
			// criar professor
			Professor cr7Professor = new Professor("ronaldo", "password", "cristiano ronaldo");
			Professor arguente = new Professor("arguente", "password", "arguente");
			userRepository.save(arguente);
			Professor rq7Professor = new Professor("quaresma", "password", "ricardo quaresma");
			Professor presiJuri = new Professor("pjuri", "password", "presi juri");
			Consultant consultant = new Consultant("consultant@mail.com", "password", "jorge mendes", " sumol");
			Consultant consultant2 = new Consultant("fc56380@alunos.fc.ul.pt", "password", "joao", " sumol");

			Professor prof1 = new Professor("prof1", "password", "prof1");
			Professor SUPERADMIN = new Professor("admin", "password", "vescomosouadmin", true);
			Professor prof2 = new Professor("prof2", "password", "prof2");
			Professor prof3 = new Professor("prof3", "password", "prof3");
			Professor prof4 = new Professor("prof4", "password", "prof4");
			Professor prof5 = new Professor("prof5", "password", "prof5");

			userRepository.save(prof1);
			String encodedPassword = passwordEncoder.encode(SUPERADMIN.getPassword());
			SUPERADMIN.setPassword(encodedPassword);
			userRepository.save(SUPERADMIN);
			userRepository.save(prof2);
			userRepository.save(prof3);
			userRepository.save(prof4);
			userRepository.save(prof5);

			// criar mestrado com esse prof a coordenador
			Masters theBestMaster = new Masters("GOLDENAI", cr7Professor);
			Masters master2 = new Masters("INFORMATICA", rq7Professor);
			Masters master3 = new Masters("STOCK", presiJuri);

			Masters master10 = new Masters("master10", prof1);
			Masters master9 = new Masters("master9", prof2);
			Masters master8 = new Masters("master8", prof3);
			Masters master7 = new Masters("master7", prof4);

			mastersRepository.save(master10);
			mastersRepository.save(master9);
			mastersRepository.save(master8);
			mastersRepository.save(master7);

			Student mCurieStudent = new Student("radiante", "password", "Marie Curie", 1903, 19.99, theBestMaster);

			Student student1 = new Student("student1", "password", "student1", 1903, 19.99, master10);
			Student student2 = new Student("student2", "password", "student2", 1903, 19.99, master9);
			Student student3 = new Student("student3", "password", "student3", 1903, 19.99, master8);
			Student student4 = new Student("student4", "password", "student4", 1903, 19.99, master7);
			Student student5 = new Student("student5", "password", "student5", 1903, 19.99, master7);
			Student student6 = new Student("student6", "password", "student6", 1903, 19.99, master7);
			Student student7 = new Student("student7", "password", "student7", 1903, 19.99, master7);
			Student student8 = new Student("student8", "password", "student8", 1903, 19.99, master7);

			userRepository.save(student1);
			userRepository.save(student2);
			userRepository.save(student3);
			userRepository.save(student4);
			userRepository.save(student5);
			userRepository.save(student6);
			userRepository.save(student7);
			userRepository.save(student8);

			String encodedPassword2 = passwordEncoder.encode(rq7Professor.getPassword());
			rq7Professor.setPassword(encodedPassword2);
			userRepository.save(rq7Professor);

			HashSet<Masters> mastersArr = new HashSet<Masters>();
			mastersArr.add(theBestMaster);
			HashSet<Masters> mastersArr2 = new HashSet<Masters>();
			mastersArr.add(master10);
			mastersArr.add(master9);
			mastersArr.add(master8);
			mastersArr.add(master7);

			DissertationTopic dissertationTopicFutebolRadiante = new DissertationTopic("Futebol radiante",
					"description", 99999, rq7Professor, mastersArr);

			DissertationTopic dissertationTopic0 = new DissertationTopic("1 sem defesa marcada", "description", 11111,
					rq7Professor, mastersArr2);
			DissertationTopic dissertationTopic1 = new DissertationTopic("2 sem defesa marcada", "description", 22222,
					rq7Professor, mastersArr2);
			DissertationTopic dissertationTopic6 = new DissertationTopic("3 sem defesa marcada", "description", 77777,
					rq7Professor, mastersArr2);

			DissertationTopic dissertationTopic2 = new DissertationTopic("1 1 defesa para marcar nota", "description",
					33333, rq7Professor, mastersArr2);
			DissertationTopic dissertationTopic3 = new DissertationTopic("2 1 defesa para marcar nota", "description",
					44444, rq7Professor, mastersArr2);

			DissertationTopic dissertationTopic4 = new DissertationTopic("1 passou 1 defesa e vai para a final",
					"description", 55555, rq7Professor, mastersArr2);
			DissertationTopic dissertationTopic5 = new DissertationTopic("2 passou 1 defesa e vai para a final",
					"description", 66666, rq7Professor, mastersArr2);

			dissertationTopicRepository.save(dissertationTopic0);
			dissertationTopicRepository.save(dissertationTopic1);
			dissertationTopicRepository.save(dissertationTopic2);
			dissertationTopicRepository.save(dissertationTopic3);
			dissertationTopicRepository.save(dissertationTopic4);
			dissertationTopicRepository.save(dissertationTopic5);
			dissertationTopicRepository.save(dissertationTopic6);

			// sem defesa
			ThesisExecution thesis1 = new ThesisExecution(student1, dissertationTopic0, "2024/2025");
			ThesisExecution thesis2 = new ThesisExecution(student2, dissertationTopic1, "2024/2025");
			ThesisExecution thesis3 = new ThesisExecution(student3, dissertationTopic6, "2024/2025");

			// falta nota
			ThesisExecution thesis4 = new ThesisExecution(student4, dissertationTopic2, "2024/2025");
			ThesisExecution thesis5 = new ThesisExecution(student5, dissertationTopic3, "2024/2025");

			thesisExecutionRepository.save(thesis1);
			thesisExecutionRepository.save(thesis2);
			thesisExecutionRepository.save(thesis3);
			thesisExecutionRepository.save(thesis4);
			thesisExecutionRepository.save(thesis5);

			ThesisDefense defense1 = new ThesisDefense(thesis4, "local_defesa_1", new Date(), arguente);
			ThesisDefense defense2 = new ThesisDefense(thesis5, "local_defesa_2", new Date(), arguente);

			defenseRepository.save(defense1);
			defenseRepository.save(defense2);

			// passou e vai a defesa final
			ThesisExecution thesis6 = new ThesisExecution(student6, dissertationTopic4, "2024/2025");
			ThesisExecution thesis7 = new ThesisExecution(student7, dissertationTopic5, "2024/2025");

			thesisExecutionRepository.save(thesis6);
			thesisExecutionRepository.save(thesis7);

			ThesisDefense defense3 = new ThesisDefense(thesis6, "local_defesa_3", new Date(), arguente);
			ThesisDefense defense4 = new ThesisDefense(thesis7, "local_defesa_4", new Date(), arguente);
			defense3.setGrade(10);
			defense4.setGrade(11);

			defenseRepository.save(defense3);
			defenseRepository.save(defense4);

			// chumbou a 1 defesa
			DissertationTopic dissertationTopic7 = new DissertationTopic("chumbou a 1 primeira defesa", "description",
					99999, rq7Professor, mastersArr2);
			dissertationTopicRepository.save(dissertationTopic7);

			ThesisExecution thesis8 = new ThesisExecution(student8, dissertationTopic7, "2024/2025");
			thesisExecutionRepository.save(thesis8);

			ThesisDefense defense5 = new ThesisDefense(thesis8, "local_defesa_5", new Date(), arguente);
			defense5.setGrade(8);
			defenseRepository.save(defense5);

			Application application1 = new Application(student1, dissertationTopic1);
			Application application2 = new Application(student2, dissertationTopic2);
			Application application3 = new Application(student3, dissertationTopic3);

			applicationRepository.save(application1);
			applicationRepository.save(application2);
			applicationRepository.save(application3);

			encodedPassword = passwordEncoder.encode(consultant.getPassword());
			consultant.setPassword(encodedPassword);
			userRepository.save(consultant);

			encodedPassword = passwordEncoder.encode(consultant.getPassword());
			consultant2.setPassword(encodedPassword);
			userRepository.save(consultant2);

			encodedPassword = passwordEncoder.encode(presiJuri.getPassword());
			presiJuri.setPassword(encodedPassword);
			userRepository.save(presiJuri);

			encodedPassword = passwordEncoder.encode(cr7Professor.getPassword());
			cr7Professor.setPassword(encodedPassword);
			userRepository.save(cr7Professor);

			mastersRepository.save(theBestMaster);

			encodedPassword = passwordEncoder.encode(mCurieStudent.getPassword());
			mCurieStudent.setPassword(encodedPassword);
			userRepository.save(mCurieStudent);
			mastersRepository.save(master2);
			mastersRepository.save(master3);

			dissertationTopicRepository.save(dissertationTopicFutebolRadiante);

			ThesisExecution thesis = new ThesisExecution(mCurieStudent, dissertationTopicFutebolRadiante, "2024/2025");

			thesisExecutionRepository.save(thesis);

			ThesisDefense defense = new ThesisDefense(thesis, "LOCATION", new Date(), arguente);

			defense.setGrade(17);

			FinalDefense finalDefense = new FinalDefense(thesis, "FINAL_LOCATION", new Date(), presiJuri, arguente);

			finalDefense.setGrade(18);
			defenseRepository.save(defense);

			defenseRepository.save(finalDefense);

			Application application = new Application(mCurieStudent, dissertationTopicFutebolRadiante);

			applicationRepository.save(application);

		};
	}

}
