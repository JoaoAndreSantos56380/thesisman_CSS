package pt.ul.fc.css.example.demo.controller;

import java.util.List;
import pt.ul.fc.css.example.demo.entities.Application;
import pt.ul.fc.css.example.demo.entities.Student;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import pt.ul.fc.css.example.demo.entities.DissertationTopic;
import pt.ul.fc.css.example.demo.services.ApplicationService;

@RestController()
@RequestMapping("api")
class RestApplication {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/getStudentApplications")
    List<Application> getStudentApplications(@RequestBody Student student) {      
        return applicationService.findApplicationsByStudent(student);
    }

    @PostMapping("/createApplication")
    ResponseEntity<?> createApplication(@RequestBody Application application) {
        try {
            Application created = applicationService.addApplication(application);
            
            // in case the student already has 5 applications
            if(created == null) {                
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.ok().body(created);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/deleteApplication")
    ResponseEntity<?> deleteApplication(@RequestBody Application application) {
        try {
            applicationService.deleteApplication(application);
            return ResponseEntity.ok("Application deleted successfully");
        } catch(Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError().body("An error occurred while deleting the application");
    }
}
