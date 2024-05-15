package pt.ul.fc.css.example.demo.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pt.ul.fc.css.example.demo.entities.Masters;
import pt.ul.fc.css.example.demo.repositories.MastersRepository;

@Component
public class MastersService {

	@Autowired
	private MastersRepository mastersRepository;

	public Set<Masters> getAllMasters() {

		Set<Masters> mastersSet = new HashSet<>();
		for (Masters master : mastersRepository.findAll()) {
			mastersSet.add(master);
		}

		return mastersSet;
	}

	public Masters findById(Long id) {
		return mastersRepository.findById(id).orElseThrow();
	}

}
