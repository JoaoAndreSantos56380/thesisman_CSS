package pt.ul.fc.css.example.demo.handlers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/* import pt.ul.fc.css.example.demo.entities.AppUser; */
import pt.ul.fc.css.example.demo.entities.Consultant;
import pt.ul.fc.css.example.demo.repositories.UserRepository;


@Component
public class ConsultantRegisterHandlerB {

	@Autowired
	private UserRepository userRepository;

	public Consultant createConsultant(String username, String password, String name, String company) throws Exception {
		if (username.isEmpty() || username == null) {
			throw new Exception("Name is a required field");
		}
		if (password.isEmpty() || password == null) {
			throw new Exception("Vat is a required field");
		}
		if (name.isEmpty() || name == null) {
			throw new Exception("Vat is a required field");
		}
		if (company.isEmpty() || company == null) {
			throw new Exception("Vat is a required field");
		}
		Consultant c = new Consultant(username,password,name,company);
		return (Consultant) userRepository.save(c);
	}

}
