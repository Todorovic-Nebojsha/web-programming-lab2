package mk.finki.ukim.wp.studentsapi.persistence;

import mk.finki.ukim.wp.studentsapi.model.*;
import mk.finki.ukim.wp.studentsapi.model.exceptions.StudentDuplicateException;

import java.util.List;

public interface StudentRepository {
    List<Student> listAllStudents();

    Student getStudentByIndex(String index);

    Student addNew(Student student) throws StudentDuplicateException;

    Student delete(String index);

    Student update(String index,String name,String lastName,StudyProgram studyProgram);
}
