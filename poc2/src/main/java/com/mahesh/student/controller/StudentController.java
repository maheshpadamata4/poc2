package com.mahesh.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahesh.student.entity.Student;
import com.mahesh.student.repository.StudentRepository;
@Controller
public class StudentController {
	@Autowired
	private StudentRepository studentRepo;
	@GetMapping("")
    public String viewHomePage() {
        return "index";
    }
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
	    model.addAttribute("student", new Student());
	     
	    return "signup_form";
	}
	@PostMapping("/process_register")
	public String processRegister(Student student) {
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(student.getPassword());
	    student.setPassword(encodedPassword);
	     
	    studentRepo.save(student);
	     
	    return "register_success";
	}
	@GetMapping("/users")
	public String listStudents(Model model) {
	    List<Student> listStudents = studentRepo.findAll();
	    model.addAttribute("listStudents", listStudents);
	     
	    return "users";
	}

}
