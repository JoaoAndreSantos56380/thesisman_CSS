package pt.ul.fc.css.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import pt.ul.fc.css.example.demo.entities.Professor;

@ControllerAdvice
public class GlobalControllerAdvice {

	@ModelAttribute("isAdmin")
	public boolean isAdmin(@AuthenticationPrincipal Professor professor) {
		return professor != null && professor.getIsAdmin();
	}
}
