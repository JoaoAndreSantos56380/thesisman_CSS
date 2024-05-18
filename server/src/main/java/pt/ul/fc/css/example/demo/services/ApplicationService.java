package pt.ul.fc.css.example.demo.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ul.fc.css.example.demo.entities.Application;
import pt.ul.fc.css.example.demo.entities.DissertationTopic;
import pt.ul.fc.css.example.demo.entities.Student;
import pt.ul.fc.css.example.demo.repositories.ApplicationRepository;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public Application addApplication(Application application) {
        return applicationRepository.save(application);
    }

    public List<Application> findApplicationsByStudent(Student student) {
        return applicationRepository.findByStudent(student);
    }

    public List<Application> findApplicationsByTopic(DissertationTopic topic) {
        return applicationRepository.findByTopic(topic);
    }

    public Application deleteApplication(Application application) {
        return applicationRepository.delete(application);
    }
}

