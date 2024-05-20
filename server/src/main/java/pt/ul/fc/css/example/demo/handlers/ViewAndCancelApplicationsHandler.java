package pt.ul.fc.css.example.demo.handlers;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pt.ul.fc.css.example.demo.entities.AppUser;
import pt.ul.fc.css.example.demo.entities.Application;
/* import pt.ul.fc.css.example.demo.entities.Consultant; */
import pt.ul.fc.css.example.demo.entities.DissertationTopic;
import pt.ul.fc.css.example.demo.entities.Masters;
import pt.ul.fc.css.example.demo.entities.Student;
import pt.ul.fc.css.example.demo.repositories.ApplicationRepository;
import pt.ul.fc.css.example.demo.repositories.DissertationTopicRepository;
import pt.ul.fc.css.example.demo.repositories.UserRepository;

@Component
public class ViewAndCancelApplicationsHandler {

	@Autowired
	private DissertationTopicRepository dissertationTopicRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ApplicationRepository applicationRepository;


	public List<Application> viewStudentApplications(long studentId) {
				//AppUser submitter2 = userRepository.findByName("joao").get(0);
		AppUser user = userRepository.findById(studentId).orElse(null);
		if(user == null) {
			return null;
		}		

		if(!(user instanceof Student)) {
			return null;
		}

		Student student = (Student) user;
		
		return applicationRepository.findByStudent(student);
	}	
}
 
