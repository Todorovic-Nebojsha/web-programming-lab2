package mk.finki.ukim.wp.studentsapi;


import mk.finki.ukim.wp.studentsapi.repository.StudentRepositoryInterface;
import mk.finki.ukim.wp.studentsapi.repository.StudyProgramRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

@ServletComponentScan
@SpringBootApplication

public class StudentsApiApplication {

	@Autowired
	private StudentRepositoryInterface studentRepository;
	@Autowired
	private StudyProgramRepositoryInterface studyProgramRepository;

	public static void main(String[] args) {

		SpringApplication.run(StudentsApiApplication.class, args);
	}
}
