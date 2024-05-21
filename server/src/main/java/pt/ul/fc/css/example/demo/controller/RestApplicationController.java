package pt.ul.fc.css.example.demo.controller;

import java.util.List;
import java.util.Set;

import pt.ul.fc.css.example.demo.entities.Application;
import pt.ul.fc.css.example.demo.entities.Student;
import pt.ul.fc.css.example.demo.entities.ThesisExecution;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import groovyjarjarantlr4.v4.parse.ANTLRParser.finallyClause_return;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import pt.ul.fc.css.example.demo.entities.DissertationTopic;
import pt.ul.fc.css.example.demo.entities.Document;
import pt.ul.fc.css.example.demo.services.ApplicationService;
import pt.ul.fc.css.example.demo.services.ThesisExecutionService;
import pt.ul.fc.css.example.demo.services.Storage.FileSystemStorageService;

@RestController()
@RequestMapping("api")
class RestApplication {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/applications/{studentid}")
    List<Application> getStudentApplications(@PathVariable long studentid) {      
        return applicationService.findApplicationsByStudent(studentid);
    }    

    @PostMapping("/createApplication/{topicId}")
    ResponseEntity<?> createApplication(@PathVariable long topicId, @RequestBody long studentId) {
        System.err.println("Create application endpoint reached!");
        System.err.println("TopicID: " + topicId);
        System.err.println("StudentID: " + studentId);
        Application created = applicationService.createApplication(studentId, topicId);
            
        // in case the student already has 5 applications
        if(created == null) {                
            System.err.println("It's null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(created);        
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

    @Autowired
    private ThesisExecutionService executionService;
    
    @PostMapping("/uploadProposal/{executionid}")
	ResponseEntity<?> handleProposalUpload(@RequestParam("file") MultipartFile file, @PathVariable long executionid) {

		ThesisExecution exec = executionService.uploadProposal(executionid, file);
        if(exec == null) {
            return ResponseEntity.internalServerError().body("Failed to create document");
        }

        return ResponseEntity.ok().build();
	}

    @PostMapping("/uploadFinal/{executionid}")
	ResponseEntity<?> handleFinalUpload(@RequestParam("file") MultipartFile file, @PathVariable long executionid) {

		ThesisExecution exec = executionService.uploadFinal(executionid, file);
        if(exec == null) {
            return ResponseEntity.internalServerError().body("Failed to create document");
        }

        return ResponseEntity.ok().build();
	}

    @GetMapping("/getproposals/{executionid}")
    ResponseEntity<?> getProposals(@PathVariable long executionid) {
        Set<Document> proposals = executionService.getProposals(executionid);
        if(proposals == null) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().body(proposals);
    }

    @GetMapping("/getfinal/{executionid}")
    ResponseEntity<?> getFinal(@PathVariable long executionid) {
        Document finalName = executionService.getFinal(executionid);
        if(finalName == null) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().body(finalName);
    }

    @Autowired
    private FileSystemStorageService storageService;

    @GetMapping("/getdocument/{documentname}") 
    ResponseEntity<Resource> getProposals(@PathVariable String documentname) {
        Resource file = storageService.loadAsResource(documentname);
        if (file != null) {
            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    



}
