package pt.ul.fc.css.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pt.ul.fc.css.example.demo.entities.AppUser;
import pt.ul.fc.css.example.demo.entities.Application;
import pt.ul.fc.css.example.demo.entities.Consultant;
import pt.ul.fc.css.example.demo.entities.DissertationTopic;
import pt.ul.fc.css.example.demo.entities.Masters;
import pt.ul.fc.css.example.demo.entities.Student;
import pt.ul.fc.css.example.demo.entities.ThesisDefense;
import pt.ul.fc.css.example.demo.entities.ThesisExecution;
import pt.ul.fc.css.example.demo.services.DissertationTopicService;
import pt.ul.fc.css.example.demo.services.MastersService;
import pt.ul.fc.css.example.demo.services.ThesisDefenseService;
import pt.ul.fc.css.example.demo.services.ThesisExecutionService;
import pt.ul.fc.css.example.demo.services.ApplicationService;
import pt.ul.fc.css.example.demo.services.UserService;

@Controller
public class WebController {

	@Autowired
	private UserService userService;

	@Autowired
	private DissertationTopicService dissertationTopicService;

	@Autowired
	private ThesisDefenseService defenseService;

	@Autowired
	private ThesisExecutionService execService;

	@Autowired
	private MastersService mastersService;

	@Autowired
	private ApplicationService applicationService;

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
		//model.addAttribute("appuser", new Consultant());
		return "user_login";
	}

	@GetMapping("/user/home")
	public String home(final Model model) {
		return "consultant_home";
	}

	@GetMapping("/consultant/submit")
	public String newTopicByConsultant(final Model model) {
		model.addAttribute("dissertation_topic", new DissertationTopic());
		Set<Masters> masters = mastersService.getAllMasters();
		model.addAttribute("masters", masters);

		return "consultant_submit_topic";
	}

	@PostMapping("/consultant/submit")
	public String newTopicByConsultantAction(final Model model, @ModelAttribute DissertationTopic dt,
			Authentication authentication, @RequestParam List<Long> master_ids) {

		// Convert masterIds to Masters objects
		Set<Masters> compatibleMasters = master_ids.stream()
				.map(mastersService::findById)
				.collect(Collectors.toSet());

		System.out.println(compatibleMasters);

		// Get the logged-in consultant
		AppUser loggedInConsultant = null;
		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			String username = ((UserDetails) authentication.getPrincipal()).getUsername();
			loggedInConsultant = userService.findUsereByUsername(username);
		}

		DissertationTopic dt2;
		try {
			dt2 = dissertationTopicService.addTopic(dt.getTitle(), dt.getDescription(), dt.getSalary(),
					loggedInConsultant, compatibleMasters);

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
	public String newThesisDefenseSchedule(final Model model, @PathVariable Long id) {
		model.addAttribute("thesis_defense", new ThesisDefense());
		return "consultant_thesis_defense_scheduler";
	}

	@PostMapping("/consultant/thesis_defense/{id}")
	public String newThesisDefenseSubmition(final Model model, @PathVariable Long id, @ModelAttribute ThesisDefense td) {
		Optional<ThesisExecution> te = execService.getThesis(id);
		if (te.isPresent()) {
			defenseService.addDefense(te.get(), td.getLocation(), td.getTime());
		} else {
			model.addAttribute("error", "Thesis Execution not found.");
			return "error_page";
		}
		return "redirect:/consultant/thesis_defense";
	}

	@GetMapping("/statistics")
	public String getStats(final Model model) {
		ArrayList<ThesisDefense> defenses = (ArrayList<ThesisDefense>) defenseService.findAllDefenses();
		int total_defenses = defenses.size();
		int positives = defenseService.findAllPositives().size();
		double average = (defenses.stream().mapToInt(ThesisDefense::getGrade).sum())/(double)total_defenses;
		model.addAttribute("positives", positives);
		model.addAttribute("average", average);
		model.addAttribute("total_defenses", total_defenses);
		model.addAttribute("defenses", defenses);
		return "statistics";
	}

	@GetMapping("/topics")
	public String getApplications(final Model model) {
		//List<Application> applications = applicationService.findAllApplications();
		//List<DissertationTopic> topics = dissertationTopicService.getTopics();
		List<DissertationTopic> topics = dissertationTopicService.findFreeTopics();
		model.addAttribute("topics", topics);
		return "topics";
	}

	@GetMapping("/topic/{id}")
	public String getApplicationId(final Model model, @PathVariable Long id) {
		//List<AppUser> students = userService.findAllUsers();
		//List<AppUser> students = userService.findByType(Student.class);
		List<AppUser> students = userService.findFreeStudents();
		DissertationTopic topic = dissertationTopicService.getTopicById(id);
		model.addAttribute("students", students);
		model.addAttribute("topic", topic);
		model.addAttribute("id", id);
		return "students";
	}

	@PostMapping("/topic/{id}")
	public String saveThesis(final Model model, @PathVariable Long id, @RequestParam Long studentId) {
		DissertationTopic topic = dissertationTopicService.getTopicById(id);
		Student student = userService.findById(studentId);
		execService.createThesisExecution(topic,student);
		return "redirect:/user/home";
	}

	@GetMapping("/consultant/thesis_defense/grading")
	public String thesis_defense_grading(final Model model) {
		return "thesis_defense_grading";
	}

	@GetMapping("/consultant/thesis_defense/grading/{id}")
	public String thesis_defense_grading_save(final Model model) {
		int grade = 0;
		model.addAttribute("grade", grade);
		return "thesis_defense_grading_id";
	}

	@PutMapping("/consultant/thesis_defense/grading/{id}")
	public String thesis_defense_grading_save_put(final Model model, int grade) {
		return "thesis_defense_grading_id";
	}
}
