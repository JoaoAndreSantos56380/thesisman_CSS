package pt.ul.fc.css.example.demo.handlers;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.entities.AppUser;
import pt.ul.fc.css.example.demo.entities.Application;
import pt.ul.fc.css.example.demo.entities.DissertationTopic;
import pt.ul.fc.css.example.demo.entities.Masters;
import pt.ul.fc.css.example.demo.entities.Student;
import pt.ul.fc.css.example.demo.entities.ThesisDefense;
import pt.ul.fc.css.example.demo.entities.ThesisExecution;
import pt.ul.fc.css.example.demo.repositories.ApplicationRepository;
import pt.ul.fc.css.example.demo.repositories.DefenseRepository;
import pt.ul.fc.css.example.demo.repositories.DissertationTopicRepository;
import pt.ul.fc.css.example.demo.repositories.ThesisExecutionRepository;
import pt.ul.fc.css.example.demo.repositories.UserRepository;
import pt.ul.fc.css.example.demo.services.ThesisDefenseService;

@Component
public class ViewDefensesHandler {

	@Autowired
	private DissertationTopicRepository dissertationTopicRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ApplicationRepository applicationRepository;

    @Autowired
    private ThesisExecutionRepository executionRepository;

    @Autowired
    private DefenseRepository defenseRepository;


	public List<ThesisDefense> getStudentDefenses(long studentId) {
        AppUser user = userRepository.findById(studentId).orElse(null);
        if(user == null) {
            System.err.println("User is null");
            return null;
        }

        if(!(user instanceof Student)) {
            System.err.println("User is not a student!");
            return null;
        }

        Student student = (Student) user;
        return defenseRepository.findByStudent(student);
    }
}
