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
import pt.ul.fc.css.example.demo.entities.FinalDefense;
import pt.ul.fc.css.example.demo.entities.Masters;
import pt.ul.fc.css.example.demo.entities.Professor;
import pt.ul.fc.css.example.demo.entities.Student;
import pt.ul.fc.css.example.demo.entities.ThesisDefense;
import pt.ul.fc.css.example.demo.entities.ThesisExecution;
import pt.ul.fc.css.example.demo.services.DissertationTopicService;
import pt.ul.fc.css.example.demo.services.MastersService;
import pt.ul.fc.css.example.demo.services.ThesisDefenseService;
import pt.ul.fc.css.example.demo.services.ThesisExecutionService;
import pt.ul.fc.css.example.demo.services.ApplicationService;
import pt.ul.fc.css.example.demo.services.UserService;
import org.springframework.web.bind.annotation.RequestBody;

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
	public String home(final Model model) {
		return "index";
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

	@GetMapping("/login")
	public String userLoginScreen(final Model model) {
		// model.addAttribute("appuser", new Consultant());
		return "login";
	}

	@GetMapping("/submit-thesis-topic")
	public String newTopicByConsultant(final Model model) {
		model.addAttribute("dissertation_topic", new DissertationTopic());
		Set<Masters> masters = mastersService.getAllMasters();
		model.addAttribute("masters", masters);
		return "submit-thesis-topic";
	}

	@PostMapping("/submit-thesis-topic")
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

			return "index";
		} catch (Exception e) {
			dt2 = new DissertationTopic();
			model.addAttribute("dissertationTopic", dt2);
			model.addAttribute("error", e.getMessage());
			System.out.println(e.getMessage());
			return "error";
		}
	}

	@GetMapping("/thesis-i-advise")
	public String newThesisDefense(final Model model, Authentication auth) {
		AppUser loggedinUser = null;
		if (auth != null && auth.getPrincipal() instanceof UserDetails) {
			String username = ((UserDetails) auth.getPrincipal()).getUsername();
			loggedinUser = userService.findUsereByUsername(username);
		}
		List<ThesisExecution> unscheduledTheses = execService.getUnscheduledTheses(loggedinUser);
		List<ThesisDefense> scheduledTheses = defenseService.getScheduledTheses((Professor) loggedinUser);
		List<ThesisExecution> unscheduledFinalTheses = execService.findUnscheduledFinalTheses(loggedinUser);
		List<FinalDefense> scheduledFinal = defenseService.getScheduledFinal(loggedinUser);
		model.addAttribute("unscheduledTheses", unscheduledTheses);
		model.addAttribute("scheduledTheses", scheduledTheses);
		model.addAttribute("unscheduledFinalTheses", unscheduledFinalTheses);
		model.addAttribute("scheduledFinal", scheduledFinal);
		model.addAttribute("id", loggedinUser.getId());
		return "thesis-i-advise";
	}

	@GetMapping("/thesis-i-advise/{id}")
	public String newThesisDefenseSchedule(final Model model, @PathVariable Long id) {
		model.addAttribute("thesis_defense", new ThesisDefense());
		List<AppUser> profs = userService.getAllProfessorsExceptMe(id);
		model.addAttribute("profs", profs);
		return "thesis-i-advise-id-schedule";
	}

	@PostMapping("/thesis-i-advise/{id}")
	public String newThesisDefenseSubmition(final Model model, @PathVariable Long id,
			@ModelAttribute ThesisDefense td) {
		ThesisExecution te = execService.getThesis(id);
		defenseService.addDefense(te, td.getLocation(), td.getTime(), td.getArguente());
		return "redirect:/thesis-i-advise";
	}

	@GetMapping("/thesis-i-advise/grade/{id}")
	public String thesis_defense_grading_save(final Model model, @PathVariable Long id) {
		model.addAttribute("id", id);
		model.addAttribute("gradeDefense", new ThesisDefense());
		return "thesis_defense_grading_id";
	}

	@PutMapping("/thesis-i-advise/grade/{id}")
	public String thesis_defense_grading_save_put(final Model model, @PathVariable Long id,
			@ModelAttribute ThesisDefense gradeDefense) {
		ThesisDefense defense = defenseService.findById(id);
		defense.setGrade(gradeDefense.getGrade());
		defenseService.addDefense(defense);
		return "redirect:/thesis-i-advise";
	}

	@GetMapping("/thesis-i-advise/final-defense/{id}")
	public String newFinalDefenseSchedule(final Model model, @PathVariable Long id) {
		model.addAttribute("final_defense", new FinalDefense());
		List<AppUser> profs = userService.getAllProfessorsExceptMe(id);
		model.addAttribute("profs", profs);
		return "thesis-i-advise_final-defense_id";
	}

	@PostMapping("/thesis-i-advise/final-defense{id}")
	public String newFinalDefenseSubmition(final Model model, @PathVariable Long id, @ModelAttribute FinalDefense td) {
		ThesisExecution te = execService.getThesis(id);
		defenseService.addFinalDefense(te, td.getLocation(), td.getTime(), td.getArguente(), td.getPresident());
		return "redirect:/thesis-i-advise";
	}

	@GetMapping("/thesis-i-advise/final-defense/grade/{id}")
	public String final_defense_grading_save(final Model model, @PathVariable Long id) {
		model.addAttribute("id", id);
		model.addAttribute("gradeDefense", new FinalDefense());
		return "final_defense_grading_id";
	}

	@PutMapping("/thesis-i-advise/final-defense/grade/{id}")
	public String final_defense_grading_save_put(final Model model, @PathVariable Long id,
			@ModelAttribute FinalDefense gradeDefense) {
		FinalDefense defense = (FinalDefense) defenseService.findById(id);
		defense.setGrade(gradeDefense.getGrade());
		defenseService.addDefense(defense);
		return "redirect:/thesis-i-advise";
	}

	@GetMapping("/statistics")
	public String getStats(final Model model) {
		ArrayList<ThesisDefense> defenses = (ArrayList<ThesisDefense>) defenseService.findAllDefenses();
		int total_defenses = defenses.size();
		int positives = defenseService.findAllPositives().size();
		double average = (defenses.stream().mapToInt(ThesisDefense::getGrade).sum()) / (double) total_defenses;
		model.addAttribute("positives", positives);
		model.addAttribute("average", average);
		model.addAttribute("total_defenses", total_defenses);
		model.addAttribute("defenses", defenses);
		return "statistics";
	}

	@GetMapping("/topics")
	public String getApplications(final Model model) {
		List<DissertationTopic> topics = dissertationTopicService.findFreeTopics();
		model.addAttribute("topics", topics);
		return "topics";
	}

	@GetMapping("/topic/{id}")
	public String getApplicationId(final Model model, @PathVariable Long id) {
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
		execService.createThesisExecution(topic, student);
		return "redirect:/user/home";
	}
}
