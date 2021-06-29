package com.mahesh.student.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mahesh.student.entity.Student;
import com.mahesh.student.repository.StudentRepository;

public class StudentDetailService implements UserDetailsService {
	@Autowired
	private StudentRepository studentRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Student user = studentRepo.findByEmail(username);
	        if (user == null) {
	            throw new UsernameNotFoundException("Student not found");
	        }
	        return new StudentDetails(user);
	    }
		
	}

