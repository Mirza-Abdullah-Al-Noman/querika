package com.management.querika.Querika.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.management.querika.Querika.model.Course;
import com.management.querika.Querika.model.Question;
import com.management.querika.Querika.model.UserDtls;
import com.management.querika.Querika.repository.CourseRepository;
import com.management.querika.Querika.repository.QuestionRepository;
import com.management.querika.Querika.repository.QuestionViewRepository;
import com.management.querika.Querika.repository.UserRepository;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private QuestionViewRepository questionViewRepository;
	
	@GetMapping("/")
	public String home() {
		return "student/home";
	}
	
	
	@GetMapping("/askAQuestion")
	public String askAQuestion(Model m) {
		
		List <Course> courses = courseRepository.findAll();
		m.addAttribute("courses", courses);
		return "student/askAQuestion";
	}
	
	@GetMapping("/viewQuestions")
	public String viewQuestions(Model m, Principal p) {
		String email = p.getName(); 
		UserDtls user = userRepository.findByEmail(email);
		List <Object []> value = questionViewRepository.viewQuestionsByUserId(user.getId());
		m.addAttribute("value",value);
		return "student/viewQuestions";
	}
	
	@GetMapping("/browseCourses")
	public String browseCourses(Model m) {
		List <Course> allCourses = courseRepository.findAll();
		m.addAttribute("allCourses", allCourses);
		return "student/browseCourses";
	}
	
	@PostMapping("/ask")
	public String askAQuestion(@ModelAttribute Question question, Principal p) {
		String email = p.getName(); 
		UserDtls user = userRepository.findByEmail(email);
		question.setAskedBy(user.getId());
		question.setIfAnswered(false);
		questionRepository.save(question);
		return "redirect:/student/askAQuestion";
	}
	@PostMapping("/view")
	public String viewQuestion (@RequestParam String selectedQuestion, Model m) {
		List  <Object []> q = questionViewRepository.viewQuestionsByQuestionId(Integer.parseInt(selectedQuestion));
		Object [] t = q.toArray();
		m.addAttribute("sQuestion", t);
		return "student/question";
	}
	
	@PostMapping("/viewQuestions")
	public String viewQuestionsByCourse(@RequestParam String selectedCourse, Model m) {
		List  <Object []> q = questionViewRepository.viewQuestionsByCourseId(Integer.parseInt(selectedCourse));
		m.addAttribute("value", q);
		return "student/viewQuestions";
	}
}
