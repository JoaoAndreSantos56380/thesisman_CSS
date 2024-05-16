package pt.ul.fc.css.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pt.ul.fc.css.example.demo.entities.Masters;
import pt.ul.fc.css.example.demo.entities.ThesisDefense;
import pt.ul.fc.css.example.demo.entities.ThesisExecution;
import pt.ul.fc.css.example.demo.handlers.StatisticsHandlerP;
import pt.ul.fc.css.example.demo.repositories.DefenseRepository;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
public class ThesisDefenseService {
    @Autowired
    private DefenseRepository defenseRepository;

    @Autowired
    private StatisticsHandlerP statsHandler;

    public List<ThesisDefense> findAllDefenses() {
        return statsHandler.findAllDefenses();
    }

    public ThesisDefense addDefense(ThesisExecution te, String location, Date time) {
        return defenseRepository.save(new ThesisDefense(te, location, time));
    }

    public List<ThesisDefense> findAllPositives() {
        return statsHandler.findAllPositives();
    }
}