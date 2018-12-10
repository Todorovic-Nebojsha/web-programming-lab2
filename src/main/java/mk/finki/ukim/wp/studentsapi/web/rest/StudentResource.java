package mk.finki.ukim.wp.studentsapi.web.rest;



import mk.finki.ukim.wp.studentsapi.model.Student;
import mk.finki.ukim.wp.studentsapi.model.StudyProgram;
import mk.finki.ukim.wp.studentsapi.model.exceptions.StudentDuplicateException;
import mk.finki.ukim.wp.studentsapi.model.exceptions.StudentNotFoundException;
import mk.finki.ukim.wp.studentsapi.service.StudentService;
import mk.finki.ukim.wp.studentsapi.service.StudyProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Null;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(value="/students",produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentResource {
    private final StudentService studentService;
    private final StudyProgramService studyProgramService;
    @Autowired
    public StudentResource(StudentService service,StudyProgramService studyProgramService){
        this.studentService=service;
        this.studyProgramService=studyProgramService;
    }

    @GetMapping
    public List<StudentNoProgram> listAllStudents() {

        return studentService.listAllStudents()
                .stream()
                .map(i->new StudentNoProgram(i.index,i.name,i.lastName))
                .collect(Collectors.toList());
    }
    @GetMapping("/{index}")
    public Student getStudentByIndex(@PathVariable("index")String index){
         // KAKO DA VRATI STATUS CODE 400 ili 404
            return studentService.getStudentByIndex(index);


    }
    @GetMapping("/by_study_program/{id}")
    public List<Student> studentsWithStudyProgramId(@PathVariable("id")Long id){
        //PROBLEM SO NULL??
        return studentService.getAllStudentsFromStudyProgram(id);
    }
    //DALI E OK Set code ???
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNew(@RequestBody Map<String,String> payload,HttpServletResponse response)
            throws StudentDuplicateException, IOException {
        String index = payload.get("index");
        String name=payload.get("name");
        String lastName=payload.get("lastName");
        String studyProgram=payload.get("studyProgramName");

        studentService.addNew(index,name,lastName,studyProgram);
        response.setHeader("Location","/students/"+payload.get("index"));

    }
    @PatchMapping("/{index}")
    @ResponseStatus(HttpStatus.OK)
    public void updateStudent(@RequestBody Map<String,String> payload)
            throws StudentDuplicateException, IOException {
        String index = payload.get("index");
        String name=payload.get("name");
        String lastName=payload.get("lastName");
        String studyProgram=payload.get("studyProgramName");

        studentService.update(index,name,lastName,studyProgram);
    }
    @DeleteMapping("/{index}")
    public void delete(@PathVariable String index){
        studentService.delete(index);
    }
//
}
class StudentNoProgram{
    public String index;
    public String name,lastName;

    public StudentNoProgram(String index, String name, String lastName) {
        this.index = index;
        this.name = name;
        this.lastName = lastName;
    }
}