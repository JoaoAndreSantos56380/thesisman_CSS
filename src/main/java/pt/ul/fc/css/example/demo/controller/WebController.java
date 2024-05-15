package pt.ul.fc.css.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import pt.ul.fc.css.example.demo.entities.AppUser;
import pt.ul.fc.css.example.demo.entities.Consultant;
import pt.ul.fc.css.example.demo.entities.DissertationTopic;
import pt.ul.fc.css.example.demo.entities.ThesisDefense;
import pt.ul.fc.css.example.demo.entities.ThesisExecution;
import pt.ul.fc.css.example.demo.services.DissertationTopicService;
import pt.ul.fc.css.example.demo.services.ThesisDefenseService;
import pt.ul.fc.css.example.demo.services.ThesisExecutionService;
import pt.ul.fc.css.example.demo.services.UserService;

@Controller
public class WebController {

	@Autowired
	private UserService userService;

	@Autowired
	private DissertationTopicService DissertationTopicService;

	@Autowired
	private ThesisDefenseService defenseService;

	@Autowired
	private ThesisExecutionService execService;

	@GetMapping("/")
	public String getIndex(Model model) {
		return "layout";
	}

	@GetMapping("/consultant/register")
	public String consultantRegister(final Model model) {
		model.addAttribute("consultant", new Consultant());
		return "consultant_register";
	}

	@PostMapping("/consultant/register")
	public String registerUserAccount(@ModelAttribute("consultant") Consultant consultant) {
		userService.addConsultant(consultant);
		return "redirect:/user/login";
	}

	@GetMapping("/user/login")
	public String userLoginScreen(final Model model) {
		model.addAttribute("appuser", new Consultant());
		return "user_login";
	}

	@GetMapping("/user/home")
	public String home(final Model model) {
		return "consultant_home";
	}

	@GetMapping("/consultant/submit")
	public String newTopicByConsultant(final Model model) {
		model.addAttribute("dissertation_topic", new DissertationTopic());
		return "consultant_submit_topic";
	}

	@PostMapping("/consultant/submit")
	public String newTopicByConsultantAction(final Model model, @ModelAttribute DissertationTopic dt,
			Authentication authentication) {
		// Get the logged-in consultant
		AppUser loggedInConsultant = null;
		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			String username = ((UserDetails) authentication.getPrincipal()).getUsername();
			loggedInConsultant = userService.findUsereByUsername(username);
		}

		DissertationTopic dt2;
		try {
			dt2 = DissertationTopicService.addTopic(dt.getTitle(), dt.getDescription(), dt.getSalary(),
					loggedInConsultant,
					dt.getCompatibleMasters());
			return "consultant_home";
		} catch (Exception e) {
			dt2 = new DissertationTopic();
			model.addAttribute("dissertationTopic", dt2);
			model.addAttribute("error", e.getMessage());
			System.out.println(e.getMessage());
			return "error_page";
		}
	}

	@GetMapping("/consultant/thesis_defense")
	public String newThesisDefense(final Model model, Authentication auth) {
		AppUser loggedinUser = null;
		if (auth != null && auth.getPrincipal() instanceof UserDetails) {
			String username = ((UserDetails) auth.getPrincipal()).getUsername();
			loggedinUser = userService.findUsereByUsername(username);
		}
		List<ThesisExecution> theses = execService.getThesisIAmOrienting(loggedinUser);
		model.addAttribute("theses", theses);
		return "consultant_thesis_defense";
	}

	@GetMapping("/consultant/thesis_defense/{id}")
	public String newThesisDefenseSchedule(final Model model, @PathVariable("id") Long id) {
		model.addAttribute("thesis_defense", new ThesisDefense());
		model.addAttribute("id", id);
		return "consultant_thesis_defense_scheduler";
	}

	@PostMapping("/consultant/thesis_defense/{id}")
	public String newThesisDefenseSubmition(final Model model, @PathVariable Long id/* , Authentication auth */, @ModelAttribute ThesisDefense td ) {
		Optional<ThesisExecution> te = execService.getThesis(id);
		if (te.isPresent()) {
			defenseService.addDefense(te.get(), td.getLocation(), td.getTime()/* new Date() */);
		} else {
			model.addAttribute("error", "Thesis Execution not found.");
			return "error_page";
		}
		return "redirect:/consultant/thesis_defense";
		//return "layout";
	}
}
