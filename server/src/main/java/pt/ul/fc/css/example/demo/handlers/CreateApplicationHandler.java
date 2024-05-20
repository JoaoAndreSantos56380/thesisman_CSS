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
public class CreateApplicationHandler {

	@Autowired
	private DissertationTopicRepository dissertationTopicRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ApplicationRepository applicationRepository;

    


	public Application createApplication(long studentId, long topicId) {
        AppUser user = userRepository.findById(studentId).orElse(null);

        if(user == null) {
            System.err.println("user is null");
            return null;
        }

        if(!(user instanceof Student)) {
            System.err.println("User is not student");
            return null;
        }

        Student student = (Student) user;
        List<Application> studentApplications = applicationRepository.findByStudent(student);
        if(studentApplications.size() >= 5) {
            System.err.println("student has at least 5 applications");
            return null;
        }

        for (Application existingApplication : studentApplications) {
            if (existingApplication.getTopic().getId() == topicId) {
                System.err.println("Similar application already exists");
                return null; // Return null if a matching application is found
            }
        }
        
        DissertationTopic topic = dissertationTopicRepository.findById(topicId).orElse(null);
        if(topic == null) {
            System.err.println("could not find topic");
            return null;
        }

        Application application = new Application(student, topic);
        applicationRepository.save(application);

        return application;
    }
}
 
