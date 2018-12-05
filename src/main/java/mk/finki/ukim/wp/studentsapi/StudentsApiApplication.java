package mk.finki.ukim.wp.studentsapi;

import mk.finki.ukim.wp.studentsapi.persistence.StudentRepository;
import mk.finki.ukim.wp.studentsapi.persistence.StudyProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

@ServletComponentScan
@SpringBootApplication

public class StudentsApiApplication {

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private StudyProgramRepository studyProgramRepository;

	public static void main(String[] args) {

		SpringApplication.run(StudentsApiApplication.class, args);
	}
}
