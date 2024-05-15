package pt.ul.fc.css.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pt.ul.fc.css.example.demo.entities.AppUser;
import pt.ul.fc.css.example.demo.entities.Professor;
import pt.ul.fc.css.example.demo.entities.ThesisExecution;
import pt.ul.fc.css.example.demo.repositories.ThesisExecutionRepository;

@Component
public class ThesisExecutionService {
	@Autowired
	ThesisExecutionRepository thesisRep;

	public List<ThesisExecution> getThesisIAmOrienting(AppUser advisor) {
		return thesisRep.findByInternalAdvisor((Professor)advisor);

	}

	public Optional<ThesisExecution> getThesis(Long id) {
		return thesisRep.findById(id);
	}

}
