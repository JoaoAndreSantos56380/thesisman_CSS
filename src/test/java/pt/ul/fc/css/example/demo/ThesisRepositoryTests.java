package pt.ul.fc.css.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pt.ul.fc.css.example.demo.entities.DissertationTopic;
import pt.ul.fc.css.example.demo.entities.Professor;
import pt.ul.fc.css.example.demo.entities.Student;
import pt.ul.fc.css.example.demo.entities.ThesisExecution;
import pt.ul.fc.css.example.demo.repositories.ApplicationRepository;
import pt.ul.fc.css.example.demo.repositories.DefenseRepository;
import pt.ul.fc.css.example.demo.repositories.DissertationTopicRepository;
import pt.ul.fc.css.example.demo.repositories.MastersRepository;
import pt.ul.fc.css.example.demo.repositories.ThesisExecutionRepository;
import pt.ul.fc.css.example.demo.repositories.UserRepository;

@Transactional
@SpringBootTest
public class ThesisRepositoryTests {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MastersRepository mastersRepository;
	@Autowired
	private DissertationTopicRepository dissertationTopicRepository;
	@Autowired
	private ThesisExecutionRepository thesisExecutionRepository;
	@Autowired
	private ApplicationRepository ApplicationRepository;
	@Autowired
	private DefenseRepository defenseRepository;

	@Test
	void testUserListIsNotEmpty() {
		assertTrue(userRepository.count() > 0);
	}

	@Test
	void testfindByStudent() {
		Student einstein = new Student("aeinstein", "password", "einstein", 1935, 19.99, null);

		Professor oppenheimer = new Professor("oppenheimer", "password", "oppenheimer");

		userRepository.save(einstein);
		userRepository.save(oppenheimer);

		DissertationTopic dissertationTopic = new DissertationTopic("megadissertation", "description", 0, einstein, null);

		dissertationTopicRepository.save(dissertationTopic);

		ThesisExecution thesis = new ThesisExecution(einstein, dissertationTopic, "24/25", oppenheimer);

		thesisExecutionRepository.save(thesis);

		ThesisExecution foundThesis = thesisExecutionRepository.findByStudent(einstein).get(0);

		assertEquals(foundThesis.getStudent().getName(),einstein.getName());
	}

	void testfindByDissertationTopic() {
		Student einstein = new Student("aeinstein", "password", "einstein", 1935, 19.99, null);

		Professor oppenheimer = new Professor("oppenheimer", "password", "oppenheimer");

		userRepository.save(einstein);
		userRepository.save(oppenheimer);

		DissertationTopic dissertationTopic = new DissertationTopic("megadissertation", "description", 0, einstein, null);

		dissertationTopicRepository.save(dissertationTopic);

		ThesisExecution thesis = new ThesisExecution(einstein, dissertationTopic, "24/25", oppenheimer);

		thesisExecutionRepository.save(thesis);

		ThesisExecution foundThesis = thesisExecutionRepository.findByDissertationTopic(dissertationTopic).get(0);

		assertEquals(foundThesis.getStudent().getName(),einstein.getName());
	}

	@Test
	void testfindByYearOfExecution() {
		Student einstein = new Student("aeinstein", "password", "einstein", 1935, 19.99, null);

		Professor oppenheimer = new Professor("oppenheimer", "password", "oppenheimer");

		userRepository.save(einstein);
		userRepository.save(oppenheimer);

		DissertationTopic dissertationTopic = new DissertationTopic("megadissertation", "description", 0, einstein, null);

		dissertationTopicRepository.save(dissertationTopic);

		ThesisExecution thesis = new ThesisExecution(einstein, dissertationTopic, "24/25", oppenheimer);

		thesisExecutionRepository.save(thesis);

		ThesisExecution foundThesis = thesisExecutionRepository.findByYearOfExecution("24/25").get(0);

		assertEquals(foundThesis.getStudent().getName(),einstein.getName());
	}

	
}