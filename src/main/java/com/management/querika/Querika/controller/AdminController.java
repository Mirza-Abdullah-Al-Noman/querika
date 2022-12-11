package com.management.querika.Querika.controller;

import java.math.BigInteger;
import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.management.querika.Querika.model.Course;
import com.management.querika.Querika.model.UserDtls;
import com.management.querika.Querika.repository.CourseRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private CourseRepository courseRepository;
	
	@GetMapping("/")
	public String home() {
		return "admin/home";
	}
	
	@GetMapping("/addCourse")
	public String addCourse() {
		return "admin/addCourse";
	}
	
	@GetMapping("/addConcept")
	public String addConcept() {
		return "admin/addConcept";
	}
	
	@PostMapping("/createCourse")
	public String createCourse(@ModelAttribute Course course, HttpSession session) {

		BigInteger f = courseRepository.existsByCourseCode(course.getCourseCode());

		if (f.equals(BigInteger.valueOf(1))) {
			session.setAttribute("msg", "Course alreday exists");
		}

		else {
			Course newCourse = courseRepository.save(course);
			if (newCourse != null) {
				session.setAttribute("msg", "Course Added Sucessfully");
			} else {
				session.setAttribute("msg", "Something went wrong");
			}
		}

		return "redirect:/admin/addCourse";
	}

}
