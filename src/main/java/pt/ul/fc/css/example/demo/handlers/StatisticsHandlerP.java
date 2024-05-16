package pt.ul.fc.css.example.demo.handlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pt.ul.fc.css.example.demo.entities.ThesisDefense;
import pt.ul.fc.css.example.demo.repositories.DefenseRepository;

@Component
public class StatisticsHandlerP {
    @Autowired
    private DefenseRepository defenseRepository;

    public List<ThesisDefense> findAllDefenses() {
        return defenseRepository.findAll();
    }


}