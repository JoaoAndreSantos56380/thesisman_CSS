package pt.ul.fc.css.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pt.ul.fc.css.example.demo.entities.AppUser;
import pt.ul.fc.css.example.demo.entities.Consultant;
import pt.ul.fc.css.example.demo.entities.DissertationTopic;
import pt.ul.fc.css.example.demo.entities.ThesisDefense;
import pt.ul.fc.css.example.demo.entities.ThesisExecution;
import pt.ul.fc.css.example.demo.services.DissertationTopicService;
import pt.ul.fc.css.example.demo.services.ThesisDefenseService;
import pt.ul.fc.css.example.demo.services.UserService;

@Controller
public class WebController {

	@Autowired
	private UserService userService;

	@Autowired
	private DissertationTopicService DissertationTopicService;

	@Autowired
	private ThesisDefenseService defenseService;

	// @RequestMapping("/")
	@GetMapping("/")
	public String getIndex(Model model) {
		return "layout";
	}

	/*
	 * @GetMapping("/consultant/new")
	 * public String newConsultant(final Model model) {
	 * model.addAttribute("consultant", new Consultant());
	 * return "consultant_register";
	 * }
	 */

	@GetMapping("/consultant/register")
	public String consultantRegister(final Model model) {
		model.addAttribute("consultant", new Consultant());
		return "consultant_register";
	}

	/*
	 * @PostMapping("/consultant/register")
	 * public String newConsultantAction(final Model model, @ModelAttribute
	 * Consultant c) {
	 * try {
	 * userService.addConsultant(c.getUsername(), c.getName(), c.getPassword(),
	 * c.getCompany());
	 * return "user_login";
	 * } catch (ApplicationException e) {
	 * model.addAttribute("error", e.getMessage());
	 * return "consultant_register";
	 * }
	 * }
	 */

	@PostMapping("/consultant/register")
	public String registerUserAccount(@ModelAttribute("consultant") Consultant consultant) {
		userService.addConsultant(consultant);
		return "redirect:/consultant/login";
	}

	@GetMapping({ "/user/login" })
	public String consultantLoginScreen(final Model model) {
		model.addAttribute("appuser", new Consultant());
		return "user_login";
	}

	/*
	 * @PostMapping("/consultant/login")
	 * public String login(final Model model, @ModelAttribute Consultant c) {
	 * return "redirect:/consultant/home";
	 * }
	 */

	@GetMapping({ "/consultant/home" })
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
			// Assuming you have a method to find Consultant by username
			// loggedInConsultant = userService.findConsultantByUsername(username);
			loggedInConsultant = userService.findUsereByUsername(username);
		}

		DissertationTopic dt2;
		try {
			// Use the retrieved logged-in consultant
			dt2 = DissertationTopicService.addTopic(dt.getTitle(), dt.getDescription(), dt.getSalary(),
					loggedInConsultant,
					dt.getCompatibleMasters());
			// return "redirect:/customers/" + dt2.getId(); // Uncomment to redirect to the
			// specific customer ID page
			return "layout";
		} catch (Exception e) {
			dt2 = new DissertationTopic();
			model.addAttribute("dissertationTopic", dt2);
			model.addAttribute("error", e.getMessage());
			System.out.println(e.getMessage());
			return "consultant_home";
		}
	}

	@GetMapping("/consultant/thesis_defense")
	public String newThesisDefense(final Model model) {
		List<ThesisDefense> defenses = defenseService.getAllPersons();
		model.addAttribute("defenses", defenses);
		return "consultant_thesis_defense";
	}

	@PostMapping("/consultant/thesis_defense")
	public String addNewThesisDefense(final Model model, @ModelAttribute("thesis_defense") ThesisDefense thesisDefense) {
		defenseService.addDefense(new ThesisExecution()/* thesisDefense.getThesisExecution() */, thesisDefense.getLocation(), thesisDefense.getTime());
		//model.addAttribute("thesis_defense", new ThesisDefense());
		return "consultant_thesis_defense";
	}
}
