package pt.ul.fc.css.example.demo.services;

import java.util.Date;
/* import java.util.Optional;
 */
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import pt.ul.fc.css.example.demo.entities.AppUser;
import pt.ul.fc.css.example.demo.entities.Consultant;
import pt.ul.fc.css.example.demo.entities.Student;
/* import pt.ul.fc.css.example.demo.handlers.ConsultantRegisterHandlerB; */
import pt.ul.fc.css.example.demo.handlers.TopicSubmissionByConsultantHandlerE;
import pt.ul.fc.css.example.demo.repositories.MastersRepository;
import pt.ul.fc.css.example.demo.repositories.UserRepository;

@Component
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MastersRepository mastersRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private TopicSubmissionByConsultantHandlerE topicsub;

	public Consultant addConsultant(Consultant consultant) {
		String encodedPassword = passwordEncoder.encode(consultant.getPassword());
		consultant.setPassword(encodedPassword);
		return userRepository.save(consultant);
	}

	public Consultant registerNewConsultant(Consultant consultant) {
		String encodedPassword = passwordEncoder.encode(consultant.getPassword());
		consultant.setPassword(encodedPassword);
		return userRepository.save(consultant);
	}

	public AppUser registerNewUser(AppUser user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		return userRepository.save(user);
	}

	@Override
	public AppUser loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
	}

	public Student loginOrCreateStudent(String username) {
		AppUser user = userRepository.findByUserName(username).orElse(null);

		// no user exists, create it!
		if(user == null) {						
			int studentNumber = (int) new Date().getTime();
			Student s = new Student(username, "password", "alberto "+studentNumber, studentNumber, mastersRepository.findAll().get(0));
			userRepository.save(s);
			return s;
		}
		// username exists but belongs to someone else
		if(!(user instanceof Student)) {
			return null;
		}

		return (Student)user;
	}

	public Consultant findConsultantByUsername(String username) {
		AppUser user = topicsub.findConsultantByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found!"));
		return (Consultant) user;
	}

	public AppUser findUsereByUsername(String username) {
		AppUser user = topicsub.findConsultantByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found!"));
		return user;
	}

	public List<AppUser> findAllUsers() {
		return userRepository.findAll();
	}

	public Student findById(Long id) {
		return (Student) userRepository.findById(id).orElseThrow();
	}

	public List<AppUser> findByType(Class<? extends AppUser> type) {
		return userRepository.findByType(Student.class);
	}

	public List<AppUser> findFreeStudents() {
		return userRepository.findFreeStudents();
	}

}
