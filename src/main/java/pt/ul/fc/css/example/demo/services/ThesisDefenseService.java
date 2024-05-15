package pt.ul.fc.css.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pt.ul.fc.css.example.demo.entities.ThesisDefense;
import pt.ul.fc.css.example.demo.entities.ThesisExecution;
import pt.ul.fc.css.example.demo.repositories.DefenseRepository;

import java.util.Date;
import java.util.List;

@Component
public class ThesisDefenseService {
	@Autowired
	private DefenseRepository defenseRepository;

	public List<ThesisDefense> getAllPersons() {
		return defenseRepository.findAll();
	}

	public ThesisDefense addDefense(ThesisExecution te, String location, Date time) {
		return defenseRepository.save(new ThesisDefense(te, location, time));
	}
}
