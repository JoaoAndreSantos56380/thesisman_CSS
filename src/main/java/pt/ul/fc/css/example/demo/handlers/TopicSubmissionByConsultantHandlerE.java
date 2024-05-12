package pt.ul.fc.css.example.demo.handlers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pt.ul.fc.css.example.demo.entities.AppUser;
import pt.ul.fc.css.example.demo.entities.Consultant;
import pt.ul.fc.css.example.demo.entities.DissertationTopic;
import pt.ul.fc.css.example.demo.entities.Masters;
import pt.ul.fc.css.example.demo.repositories.DissertationTopicRepository;
import pt.ul.fc.css.example.demo.repositories.UserRepository;

@Component
public class TopicSubmissionByConsultantHandlerE {

	@Autowired
	private DissertationTopicRepository dissertationTopicRepository;

	@Autowired
	private UserRepository userRepository;

	public DissertationTopic createTopic(String title, String description, double salary, AppUser submitter,
			Set<Masters> compatibleMasters) {
				//AppUser submitter2 = userRepository.findByName("joao").get(0);
				DissertationTopic dt = new DissertationTopic(title, description, salary, submitter, compatibleMasters);

				return dissertationTopicRepository.save(dt);
	}
}
