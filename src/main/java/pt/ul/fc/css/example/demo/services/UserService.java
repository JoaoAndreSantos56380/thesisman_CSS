package pt.ul.fc.css.example.demo.services;

/* import java.util.Optional;
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import pt.ul.fc.css.example.demo.entities.AppUser;
import pt.ul.fc.css.example.demo.entities.Consultant;
/* import pt.ul.fc.css.example.demo.handlers.ConsultantRegisterHandlerB; */
import pt.ul.fc.css.example.demo.handlers.TopicSubmissionByConsultantHandlerE;
import pt.ul.fc.css.example.demo.repositories.UserRepository;

@Component
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

/* 	@Autowired
	private ConsultantRegisterHandlerB consultantRegister; */

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

}
