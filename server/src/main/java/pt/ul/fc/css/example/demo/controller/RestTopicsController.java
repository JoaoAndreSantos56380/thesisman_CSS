package pt.ul.fc.css.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.ul.fc.css.example.demo.entities.DissertationTopic;
import pt.ul.fc.css.example.demo.entities.Student;
import pt.ul.fc.css.example.demo.services.DissertationTopicService;
import pt.ul.fc.css.example.demo.services.UserService;

@RestController()
@RequestMapping("api")
class RestMasters {

    @Autowired
    private DissertationTopicService topicService;

    @Autowired
    private UserService userService;

    @GetMapping("/dissertationTopics")
    List<DissertationTopic> all() {
        return topicService.getTopics();
    }

    @GetMapping("/dissertationTopics/{studentid}")
    List<DissertationTopic> all(@PathVariable long studentid) {        
        return topicService.getCompatibleTopicsForStudent(studentid);
    }

    @GetMapping("/login/{username}")
	public long loginOrCreateStudentId(@PathVariable String username) {
        Student s = userService.loginOrCreateStudent(username);
        if(s == null) {
            return -1;
        } else {
            return s.getId();
        }
	}
}
