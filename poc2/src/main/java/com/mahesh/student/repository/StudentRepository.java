package com.mahesh.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mahesh.student.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	 @Query("SELECT u FROM User u WHERE u.email = ?1")
	    public Student findByEmail(String email);
}
