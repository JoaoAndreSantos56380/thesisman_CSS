package pt.ul.fc.css.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pt.ul.fc.css.example.demo.entities.AppUser;
import pt.ul.fc.css.example.demo.entities.Consultant;
import pt.ul.fc.css.example.demo.handlers.ConsultantRegisterHandlerB;
import pt.ul.fc.css.example.demo.repositories.UserRepository;

/* import mvcexample.business.entities.AppUser;
import mvcexample.business.handlers.ConsultantRegisterHandlerB;
import mvcexample.business.repository.UserRepository; */

@Component
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ConsultantRegisterHandlerB consultantRegister;

	public Consultant addConsultant(String username, String name, String password, String company) throws EmptyFieldsException {
		Consultant c = (Consultant) consultantRegister.createConsultant(username, password, name, company);
		return c;

	}

}
