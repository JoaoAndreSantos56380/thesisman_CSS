package pt.ul.fc.css.example.demo.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pt.ul.fc.css.example.demo.entities.AppUser;
import pt.ul.fc.css.example.demo.entities.DissertationTopic;
import pt.ul.fc.css.example.demo.entities.Masters;
import pt.ul.fc.css.example.demo.handlers.TopicSubmissionByConsultantHandlerE;
@Component
public class DissertationTopicService {

	@Autowired
	TopicSubmissionByConsultantHandlerE dissertationTopicSubmission;

	public DissertationTopic addTopic(String title, String description, double salary, AppUser submitter,
			Set<Masters> compatibleMasters) {
				DissertationTopic dt = dissertationTopicSubmission.createTopic(title, description, salary, submitter, compatibleMasters);
				return dt;
	}

}
