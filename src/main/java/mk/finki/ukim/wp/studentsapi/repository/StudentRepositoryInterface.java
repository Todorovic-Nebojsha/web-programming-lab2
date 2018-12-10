package mk.finki.ukim.wp.studentsapi.repository;

import mk.finki.ukim.wp.studentsapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepositoryInterface extends JpaRepository<Student,String> {
}
