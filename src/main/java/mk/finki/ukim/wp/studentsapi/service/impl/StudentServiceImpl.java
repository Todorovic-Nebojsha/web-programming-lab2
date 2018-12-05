package mk.finki.ukim.wp.studentsapi.service.impl;

import mk.finki.ukim.wp.studentsapi.model.Student;
import mk.finki.ukim.wp.studentsapi.model.StudyProgram;
import mk.finki.ukim.wp.studentsapi.model.exceptions.StudentDuplicateException;
import mk.finki.ukim.wp.studentsapi.model.exceptions.StudentNotFoundException;
import mk.finki.ukim.wp.studentsapi.persistence.StudentRepository;
import mk.finki.ukim.wp.studentsapi.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository rep){
        studentRepository=rep;
    }

    @Override
    public List<Student> listAllStudents() {
        return studentRepository.listAllStudents();
    }

    @Override
    public Student getStudentByIndex(String index) throws StudentNotFoundException {
        return studentRepository.getStudentByIndex(index);
    }

    @Override
    public Student addNew(Student student) throws StudentDuplicateException {
        return studentRepository.addNew(student);
    }

    @Override
    public Student delete(String index) throws StudentNotFoundException {
        return studentRepository.delete(index);
    }

    @Override
    public Student update(String index, String name, String lastName, StudyProgram studyProgram) throws StudentNotFoundException {
        return studentRepository.update(index,name,lastName,studyProgram);
    }
}
