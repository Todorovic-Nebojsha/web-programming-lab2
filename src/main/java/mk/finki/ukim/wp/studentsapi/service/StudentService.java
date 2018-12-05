package mk.finki.ukim.wp.studentsapi.service;


import mk.finki.ukim.wp.studentsapi.model.Student;
import mk.finki.ukim.wp.studentsapi.model.StudyProgram;
import mk.finki.ukim.wp.studentsapi.model.exceptions.StudentDuplicateException;
import mk.finki.ukim.wp.studentsapi.model.exceptions.StudentNotFoundException;

import java.util.List;

public interface StudentService {
    List<Student> listAllStudents();

    Student getStudentByIndex(String index) throws StudentNotFoundException;

    Student addNew(Student student) throws StudentDuplicateException;

    Student delete(String index) throws StudentNotFoundException;

    Student update(String index,String name,String lastName,StudyProgram studyProgram) throws StudentNotFoundException;
}
