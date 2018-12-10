package mk.finki.ukim.wp.studentsapi.web.rest;

import mk.finki.ukim.wp.studentsapi.model.StudyProgram;
import mk.finki.ukim.wp.studentsapi.model.exceptions.StudentDuplicateException;
import mk.finki.ukim.wp.studentsapi.service.StudyProgramService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/study_programs",produces = MediaType.APPLICATION_JSON_VALUE)
public class StudyProgramResource {
    StudyProgramService service;
    public StudyProgramResource(StudyProgramService s){
        service=s;
    }

    @GetMapping()
    public List<StudyProgram> listAllStudyPrograms(){
        return service.listAllStudyPrograms();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNew(@RequestBody Map<String,String> payload)
            throws StudentDuplicateException, IOException {

        service.addNew(payload.get("studyProgram"));

         }

    @DeleteMapping("/study_programs/{id}")
    public void deleteStudyProgram(@PathVariable("id") long id){
        service.delete(id);
    }


}
