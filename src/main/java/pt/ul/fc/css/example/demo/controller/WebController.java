package pt.ul.fc.css.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pt.ul.fc.css.example.demo.entities.AppUser;
import pt.ul.fc.css.example.demo.entities.Consultant;
import pt.ul.fc.css.example.demo.entities.DissertationTopic;
import pt.ul.fc.css.example.demo.services.ApplicationException;
import pt.ul.fc.css.example.demo.services.DissertationTopicService;
import pt.ul.fc.css.example.demo.services.UserService;

@Controller
public class WebController {

	@Autowired
	private UserService userService;

	@Autowired
	private DissertationTopicService DissertationTopicService;

	// @RequestMapping("/")
	@GetMapping("/")
	public String getIndex(Model model) {
		return "layout";
	}

	@GetMapping("/consultant/new")
	public String newConsultant(final Model model) {
		model.addAttribute("consultant", new Consultant());
		return "consultant_register";
	}

	@PostMapping("/consultant/new")
	public String newConsultantAction(final Model model, @ModelAttribute Consultant c) {
		Consultant c2;
		try {
			c2 = userService.addConsultant(c.getUsername(), c.getName(), c.getPassword(), c.getCompany());
			// return "redirect:/customers/" + c2.getId();
			return "layout";
		} catch (ApplicationException e) {
			c2 = new Consultant();
			model.addAttribute("customer", c2);
			model.addAttribute("error", e.getMessage());
			return "consultant_register";
		}
	}

	@GetMapping({ "/consultant/login" })
	public String consultantLoginScreen(final Model model) {
		model.addAttribute("appuser", new Consultant());
		return "user_login";
	}

	@PostMapping("/consultant/login")
	public String login(final Model model, @ModelAttribute Consultant c) {
		return "redirect:/consultant/home";
	}

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
	public String newTopicByConsultantAction(final Model model, @ModelAttribute DissertationTopic dt, @ModelAttribute Consultant c) {
		DissertationTopic dt2;
		try {
			dt2 = DissertationTopicService.addTopic(dt.getTitle(), dt.getDescription(), dt.getSalary(),c, dt.getCompatibleMasters());
			// return "redirect:/customers/" + c2.getId();
			return "layout";
		} catch (/* Application */Exception e) {
			dt2 = new DissertationTopic();
			model.addAttribute("customer", dt2);
			model.addAttribute("error", e.getMessage());
			System.out.println(e.getMessage());
			return "consultant_home";
		}
	}
}
