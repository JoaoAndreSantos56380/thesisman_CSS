package pt.ul.fc.css.example.demo.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.example.demo.entities.AppUser;
import pt.ul.fc.css.example.demo.entities.DissertationTopic;
import pt.ul.fc.css.example.demo.entities.Professor;
import pt.ul.fc.css.example.demo.entities.Student;
import pt.ul.fc.css.example.demo.entities.ThesisExecution;
import pt.ul.fc.css.example.demo.handlers.StudentExecutionHandler;
import pt.ul.fc.css.example.demo.repositories.ThesisExecutionRepository;

@Component
public class ThesisExecutionService {
  @Autowired ThesisExecutionRepository thesisRep;

  @Autowired private StudentExecutionHandler executionHandler;

  public List<ThesisExecution> getThesisIAmOrienting(AppUser advisor) {
    return thesisRep.findByInternalAdvisor((Professor) advisor);
  }

  public List<ThesisExecution> getStudentExecutions(long studentId) {
    return executionHandler.getStudentExecutions(studentId);
  }

  public ThesisExecution getThesis(Long id) {
    return thesisRep.findById(id).orElseThrow();
  }

  public void createThesisExecution(DissertationTopic topic, Student student) {
    int currentYear = LocalDate.now().getYear();
    int nextYear = currentYear + 1;
    String currentYearLast2Digits = String.format("%02d", currentYear);
    String nextYearLast2Digits = String.format("%02d", nextYear);

    String result = currentYearLast2Digits + "/" + nextYearLast2Digits;

	thesisRep.save(new ThesisExecution(student, topic, result));
  }
	public List<ThesisExecution> getUnscheduledTheses(AppUser loggedinUser) {
		return thesisRep.findUnscheduledTheses((Professor) loggedinUser);
	}

	public List<ThesisExecution> getScheduledFinal(AppUser loggedinUser) {
		return thesisRep.findScheduledFinal((Professor) loggedinUser);
	}

	public List<ThesisExecution> findUnscheduledFinalTheses(AppUser user) {
		return thesisRep.findUnscheduledFinalTheses((Professor) user);
	}

}
