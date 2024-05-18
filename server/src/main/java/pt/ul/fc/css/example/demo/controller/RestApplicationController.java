package pt.ul.fc.css.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import pt.ul.fc.css.example.demo.entities.DissertationTopic;
import pt.ul.fc.css.example.demo.services.DissertationTopicService;

@RestController()
@RequestMapping("api")
class RestApplication {

    @Autowired
    private ApplicationService topicService;

    @PostMapping("/createApplication")
    ResponseEntity<?> deleteApplication(@RequestBody Application application) {
        try {
            Application created = topicService.createApplication(application);

            // in case the student already has 5 applications
            if(created == null) {                
                return ResponseEntity.ok();
            }
            return ResponseEntity.ok().body(created);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError();
    }

    @PostMapping("/deleteApplication")
    ResponseEntity<?> deleteApplication(@RequestBody Application application) {
        try {
            topicService.deleteApplication(application);
            return ResponseEntity.ok();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError();
    }
}
