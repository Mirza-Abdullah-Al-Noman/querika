package com.management.querika.Querika.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/studentTutor")
public class StudentTutorController {
	
	@Autowired
	public QuestionRepository questionRepository;
	@Autowired
	public CourseRepository courseRepository;
	@Autowired
	public QuestionViewRepository questionViewRepository;
	@Autowired
	public UserRepository userRepository;
	
	@GetMapping("/")
	public String home() {
		return "studentTutor/home";
	}
	
	@GetMapping("/viewUnansweredQuestions")
	public String viewUnansweredQuestions(Model m) {
		List <Object []> uQuestions = questionViewRepository.viewUnansweredQuestions();
		m.addAttribute("uQuestions",uQuestions);
		return "studentTutor/viewUnansweredQuestions";
	}
	
	@PostMapping("/answerThis") 
	public String answerAQuestion(@RequestParam String selectedQuestion, Model m) {
		List  <Object []> q = questionViewRepository.viewUnansweredQuestionById(Integer.parseInt(selectedQuestion));
		Object [] t = q.toArray();
		m.addAttribute("sQuestion", t);
		return "studentTutor/answerAQuestion";
	}
	
	@PostMapping("/answer") 
	public String saveAnswer(@RequestParam String answerText, @RequestParam String sQid, Principal p) {
		String email = p.getName(); 
		UserDtls user = userRepository.findByEmail(email);
		Question q = questionRepository.findById(Integer.parseInt(sQid));
		q.setAnswerText(answerText);
		q.setAnsweredBy(user.getId());
		questionRepository.save(q);
		return "studentTutor/viewUnansweredQuestions";
	}
	
	@GetMapping("/viewMyAnsweredQuestions")
	public String viewMyAnsweredQuestions(Model m, Principal p) {
		String email = p.getName(); 
		UserDtls user = userRepository.findByEmail(email);
		List <Object []> mQuestions = questionViewRepository.viewMyAnsweredQuestions(user.getId());
		m.addAttribute("mQuestions",mQuestions);
		return "studentTutor/viewMyAnsweredQuestions";
	}
	
	@GetMapping("/browseCourses")
	public String browseCourses(Model m) {
		List <Course> allCourses = courseRepository.findAll();
		m.addAttribute("allCourses", allCourses);
		return "studentTutor/browseCourses";
	}
	
	@GetMapping("/viewQuestions")
	public String viewQuestions(Model m, Principal p) {
		String email = p.getName(); 
		UserDtls user = userRepository.findByEmail(email);
		List <Object []> value = questionViewRepository.viewQuestionsByUserId(user.getId());
		m.addAttribute("value",value);
		return "studentTutor/viewQuestions";
	}
	
	@PostMapping("/viewQuestions")
	public String viewQuestionsByCourse(@RequestParam String selectedCourse, Model m) {
		List  <Object []> q = questionViewRepository.viewQuestionsByCourseId(Integer.parseInt(selectedCourse));
		m.addAttribute("value", q);
		return "studentTutor/viewQuestions";
	}
	
	@PostMapping("/view")
	public String viewQuestion (@RequestParam String selectedQuestion, Model m) {
		List  <Object []> q = questionViewRepository.viewQuestionsByQuestionId(Integer.parseInt(selectedQuestion));
		Object [] t = q.toArray();
		m.addAttribute("sQuestion", t);
		return "studentTutor/question";
	}
	
	

}
