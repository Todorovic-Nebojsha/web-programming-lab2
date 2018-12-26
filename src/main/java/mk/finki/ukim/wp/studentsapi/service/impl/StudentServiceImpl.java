package mk.finki.ukim.wp.studentsapi.service.impl;

import mk.finki.ukim.wp.studentsapi.model.Student;
import mk.finki.ukim.wp.studentsapi.model.StudyProgram;
import mk.finki.ukim.wp.studentsapi.model.exceptions.BadParametarsException;
import mk.finki.ukim.wp.studentsapi.model.exceptions.StudentDuplicateException;
import mk.finki.ukim.wp.studentsapi.model.exceptions.StudentNotFoundException;
import mk.finki.ukim.wp.studentsapi.repository.StudentRepositoryInterface;
import mk.finki.ukim.wp.studentsapi.repository.StudyProgramRepositoryInterface;
import mk.finki.ukim.wp.studentsapi.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepositoryInterface studentRepository;
    private final StudyProgramRepositoryInterface programRepository;
    public StudentServiceImpl(StudentRepositoryInterface rep,StudyProgramRepositoryInterface program){
        studentRepository=rep;
        programRepository =program;
    }

    @Override
    public List<Student> listAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentByIndex(String index) throws StudentNotFoundException {
        if(studentRepository.findById(index).isPresent())
        return studentRepository.findById(index).get();
        else
            throw new StudentNotFoundException();
    }

    @Override
    public Student addNew(String index, String name, String lastName, String programName) throws StudentDuplicateException {
        if(index==null || name==null || lastName==null || programName==null || index.length()!=6){
            throw new BadParametarsException();
        }
        else if(!index.chars().allMatch(i->Character.isDigit(i))){
            throw new BadParametarsException();
        }
        List<StudyProgram> listPrograms=programRepository.findAll();
        Optional<StudyProgram> programa=listPrograms.stream().filter(i->i.name.equals(programName)).findAny();
        if(!programa.isPresent()){
            throw new BadParametarsException();
        }
        studentRepository.save(new Student(index,name,lastName,programa.get()));

        return new Student(index,name,lastName,programa.get());

    }

    @Override
    public Student delete(String index) throws StudentNotFoundException {
        Optional<Student> student=studentRepository.findById(index);
        if(!student.isPresent()){
            throw new StudentNotFoundException();
        }

        studentRepository.delete(student.get());
        return student.get();
    }

    @Override
    public List<Student> getAllStudentsFromStudyProgram(Long id) {
        return studentRepository.findAll().stream()
        .filter(i->i.studyProgram.id==id)
                .collect(Collectors.toList());
    }

    @Override
    public Student update(String index, String name, String lastName, String studyProgram) throws StudentNotFoundException {
        List<StudyProgram> listPrograms=programRepository.findAll();
        Optional<StudyProgram> programa=listPrograms.stream().filter(i->i.name.equals(studyProgram)).findAny();
        if(!programa.isPresent()){
            throw new BadParametarsException();
        }
        Optional<Student> student = studentRepository.findById(index);
        if(!student.isPresent()){
            throw new StudentNotFoundException();
        }

        if(name!=null){
            student.get().setName(name);
        }
        if(lastName!=null){
            student.get().setLastName(lastName);
        }
        student.get().setStudyProgram(programa.get());

        studentRepository.save(student.get());
        return student.get();
    }
}
