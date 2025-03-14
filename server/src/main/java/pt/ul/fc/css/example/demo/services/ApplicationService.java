package pt.ul.fc.css.example.demo.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.entities.Application;
import pt.ul.fc.css.example.demo.entities.DissertationTopic;
import pt.ul.fc.css.example.demo.entities.Student;
import pt.ul.fc.css.example.demo.handlers.CreateApplicationHandler;
import pt.ul.fc.css.example.demo.handlers.ViewAndCancelApplicationsHandler;
import pt.ul.fc.css.example.demo.repositories.ApplicationRepository;

@Component
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ViewAndCancelApplicationsHandler viewAndCancelApplicationsHandler;

    @Autowired
    private CreateApplicationHandler createApplicationHandler;

    public Application addApplication(Application application) {
        List<Application> studentApplications = applicationRepository.findByStudent(application.getStudent());
        if(studentApplications.size() >= 5) {
            return null;
        }

        for (Application existingApplication : studentApplications) {
            if (existingApplication.getTopic().equals(application.getTopic())) {
                return null; // Return null if a matching application is found
            }
        }
        
        return applicationRepository.save(application);
    }

    public Application createApplication(long studentId, long topicId) {
        return createApplicationHandler.createApplication(studentId, topicId);
    }

    public List<Application> findApplicationsByStudent(long studentId) {
        return viewAndCancelApplicationsHandler.viewStudentApplications(studentId);
    }

    public List<Application> findApplicationsByTopic(DissertationTopic topic) {
        return applicationRepository.findByTopic(topic);
    }

    public void deleteApplication(long applicationId) {
        viewAndCancelApplicationsHandler.deleteApplication(applicationId);
    }
}

