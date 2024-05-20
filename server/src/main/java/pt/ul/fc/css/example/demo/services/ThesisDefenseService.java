package pt.ul.fc.css.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pt.ul.fc.css.example.demo.entities.AppUser;
import pt.ul.fc.css.example.demo.entities.FinalDefense;
import pt.ul.fc.css.example.demo.entities.Professor;
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

    /* public ThesisDefense addDefense(ThesisExecution te, String location, Date time) {
        return defenseRepository.save(new ThesisDefense(te, location, time));
    } */

    public List<ThesisDefense> findAllPositives() {
        return statsHandler.findAllPositives();
    }

	public ThesisDefense findById(Long id) {
		return defenseRepository.findById(id).orElseThrow();
	}

	public List<ThesisDefense> getDefensesOfTheThesesIAmOrienting(AppUser loggedinUser) {
		return defenseRepository.getDefensesOfTheThesesIAmOrienting((Professor) loggedinUser);
	}

	public void addDefense(ThesisDefense defense) {
		defenseRepository.save(defense);
	}

	public ThesisDefense addDefense(ThesisExecution thesisExecution, String location, Date time, Professor arguente) {
		return defenseRepository.save(new ThesisDefense(thesisExecution, location, time, arguente));

	}

	public FinalDefense addFinalDefense(ThesisExecution te, String location, Date time, Professor arguente, Professor president) {
		return defenseRepository.save(new FinalDefense(te, location, time, president, arguente));
	}
}
