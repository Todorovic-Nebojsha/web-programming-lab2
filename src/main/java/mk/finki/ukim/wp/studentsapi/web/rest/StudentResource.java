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
        return studentService.listAllStudents()
                .stream()
                .filter(i->i.studyProgram.id==id)
                .collect(Collectors.toList());
    }
    //DALI E OK Set code ???
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNew(HttpServletRequest request, HttpServletResponse response)
            throws StudentDuplicateException, IOException {
        String index = request.getParameter("index");
        String name=request.getParameter("name");
        String lastName=request.getParameter("lastName");
        String studyProgram=request.getParameter("studyProgramName");


        if(name == null || lastName ==null || studyProgram==null ||index==null ){
            response.setStatus(400);
        }
        else if(index.length()!=6){
            response.setStatus(400);
        }
        else {
            Boolean notDigits = index.chars().map(c -> (char) c).allMatch(i -> Character.isDigit(i));
            if (notDigits) {
            response.setStatus(400);
            }
            else if(!studyProgramWithNameExists(studyProgram)){
                response.setStatus(400);
            }
            else {
                StudyProgram program=studyProgramService.getStudyProgramByName(studyProgram);
                Student s=new Student(name,lastName,program);
                studentService.addNew(s);
                response.setStatus(201);
                response.setHeader("Location","/students/"+index);

            }
        }

    }
    @PatchMapping("/{index}")
    @ResponseStatus(HttpStatus.OK)
    public Student update(@RequestBody String name,@RequestBody String lastName,
                       @RequestBody String studyProgramName,@PathVariable String index)
            throws StudentNotFoundException, IOException {
            if(!studyProgramWithNameExists(studyProgramName)){
                //kako da vrat status kod 400
                // return ???
            }
            else {
                try{
                    StudyProgram program=studyProgramService.getStudyProgramByName(studyProgramName);
                    return studentService.update(index,name,lastName,program);
                }
                catch(Exception e){
                    // kod 404 ???
                }
            }

    }
    @DeleteMapping("/{index}")
    public Student delete(@PathVariable String index){
        try{
            return studentService.delete(index);
        }
        catch (Exception e){
            // kako da vrati kod 404
        }
    }
    @GetMapping("/study_programs")
    public List<StudyProgram> listAllStudyPrograms(){
        return studyProgramService.listAllStudyPrograms();
    }

    @PostMapping("/study_programs")
    @ResponseStatus(HttpStatus.CREATED)
    public StudyProgram addNewStudyProgram(HttpServletRequest request, HttpServletResponse response){
        try{
            StudyProgram s=new StudyProgram(request.getParameter("studyProgramName"));
            response.setStatus(201);
            return studyProgramService.addNew(s);
        }
        catch(Exception e){
            //error studyProgramAlreadyExists return HTTP code
        }
    }

    @DeleteMapping("/study_programs/{id}")
    public StudyProgram deleteStudyProgram(@PathVariable("id") long id){
        try{
            return studyProgramService.delete(id);
        }
        catch(Exception e){
            //http status code ??
        }
    }
    public Boolean studyProgramWithNameExists(String name){
        return studyProgramService
                .listAllStudyPrograms()
                .stream()
                .map(i->i.name)
                .filter(i->i.equals(name))
                .count()!=0;
    }

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