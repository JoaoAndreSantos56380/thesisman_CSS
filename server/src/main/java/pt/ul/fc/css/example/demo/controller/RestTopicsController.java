package pt.ul.fc.css.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.ul.fc.css.example.demo.entities.DissertationTopic;
import pt.ul.fc.css.example.demo.services.DissertationTopicService;

@RestController()
@RequestMapping("api")
class RestMasters {

    @Autowired
    private DissertationTopicService topicService;


    @GetMapping("/dissertationTopics")
    List<DissertationTopic> all() {
        return topicService.getTopics();
    }
}
