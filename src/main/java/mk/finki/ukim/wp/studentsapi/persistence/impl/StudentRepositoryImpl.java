package mk.finki.ukim.wp.studentsapi.persistence.impl;

import mk.finki.ukim.wp.studentsapi.model.exceptions.StudentDuplicateException;
import mk.finki.ukim.wp.studentsapi.model.exceptions.StudentNotFoundException;
import mk.finki.ukim.wp.studentsapi.persistence.StudentRepository;
import mk.finki.ukim.wp.studentsapi.model.*;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Repository
public class StudentRepositoryImpl implements StudentRepository {

    private static List<Student> students;

    @PostConstruct
    public void init(){
        /*Student s1=new Student("161047","Nebojsha","Todorovic");
        Student s2=new Student("171047","Name","lastName");
        Student s3=new Student("181253","ANA","ANA");

        students= Stream.of(s1,s2,s3).collect(Collectors.toList());*/
    }
    @Override
    public List<Student> listAllStudents() {
        return students;
    }

    @Override
    public Student getStudentByIndex(String index) {
        Optional<Student> s= students.stream().filter(i->i.index.equals(index)).findFirst();
        if(!s.isPresent()){
            throw new StudentNotFoundException();
        }
        return s.get();
    }

    @Override
    public Student addNew(Student student) throws StudentDuplicateException {
        if(students.stream().map(i->i.index).collect(Collectors.toSet()).contains(student.index)){
            throw new StudentDuplicateException();
        }
        students.add(student);
        return student;
    }

    @Override
    public Student delete(String index) {
        Optional<Student> s=students.stream().filter(i->i.index.equals(index)).findFirst();
        if(!s.isPresent()){
            throw new StudentNotFoundException();
        }
        Student rez=s.get();
        students.remove(rez);
        return rez;
    }

    @Override
    public Student update(String index, String name, String lastName, StudyProgram studyProgram) {
        Optional<Student> s=students.stream().filter(i->i.index.equals(index)).findFirst();
        if(!s.isPresent()){
            throw new StudentNotFoundException();
        }
        Student rez=s.get();
        if(name.length()>0)
        rez.name=name;
        if(lastName.length()>0)
        rez.lastName=lastName;

        rez.studyProgram=studyProgram;
        students.remove(s.get());
        students.add(rez);
        return rez;

    }
}
