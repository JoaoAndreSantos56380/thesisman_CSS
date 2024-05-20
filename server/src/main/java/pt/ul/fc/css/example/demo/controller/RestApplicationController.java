package pt.ul.fc.css.example.demo.controller;

import java.util.List;
import pt.ul.fc.css.example.demo.entities.Application;
import pt.ul.fc.css.example.demo.entities.Student;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/applications/{studentid}")
    List<Application> getStudentApplications(@PathVariable long studentid) {      
        return applicationService.findApplicationsByStudent(studentid);
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

    @PostMapping("/deleteApplication/{applicationid}")
    ResponseEntity<?> deleteApplication(@PathVariable long applicationid) {
        try {
            applicationService.deleteApplication(applicationid);
            return ResponseEntity.ok("Application deleted successfully");
        } catch(Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError().body("An error occurred while deleting the application");
    }
}
