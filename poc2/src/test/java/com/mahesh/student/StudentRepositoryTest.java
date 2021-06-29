package com.mahesh.student;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.mahesh.student.entity.Student;
import com.mahesh.student.repository.StudentRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class StudentRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private StudentRepository repo;

	@Test
	public void testCreateStudent() {
		Student student = new Student();
		student.setEmail("ravikumar@gmail.com");
		student.setPassword("ravi2020");
		student.setFirstName("Ravi");
		student.setLastName("Kumar");
		student.setPhoneNumber("Kumar");
		Student savedStudent = repo.save(student);

		Student existStudent = entityManager.find(Student.class, savedStudent.getId());

		assertThat(student.getEmail()).isEqualTo(existStudent.getEmail());

	}

}
