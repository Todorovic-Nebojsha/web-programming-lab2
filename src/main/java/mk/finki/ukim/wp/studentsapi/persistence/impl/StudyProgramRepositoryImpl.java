package mk.finki.ukim.wp.studentsapi.persistence.impl;

import mk.finki.ukim.wp.studentsapi.model.Student;
import mk.finki.ukim.wp.studentsapi.model.StudyProgram;
import mk.finki.ukim.wp.studentsapi.model.exceptions.StudentDuplicateException;
import mk.finki.ukim.wp.studentsapi.model.exceptions.StudyProgramNotFoundException;
import mk.finki.ukim.wp.studentsapi.persistence.StudyProgramRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import java.util.stream.Stream;
@Repository
public class StudyProgramRepositoryImpl implements StudyProgramRepository{

    private List<StudyProgram> studies;
    @PostConstruct
    public void init(){

        /*StudyProgram s1=new StudyProgram("KNI");
        StudyProgram s2= new StudyProgram("PET");
        StudyProgram s3=new StudyProgram("IKI");
        studies= Stream.of(s1,s2,s3).collect(Collectors.toList());*/
    }
    @Override
    public List<StudyProgram> listAllStudyPrograms() {
       return studies;
    }

    @Override
    public StudyProgram getStudyProgramByIndex(long index) {
        Optional<StudyProgram> sp=studies.stream().filter(i->i.id==index).findFirst();
        if(!sp.isPresent()){
            throw new StudyProgramNotFoundException();
        }
        return sp.get();
    }

    @Override
    public StudyProgram getStudyProgramByName(String name) {
        Optional<StudyProgram> sp=studies.stream().filter(i->i.name.equals(name)).findFirst();
        if(!sp.isPresent()){
            throw new StudyProgramNotFoundException();
        }
        return sp.get();
    }

    @Override
    public StudyProgram addNew(StudyProgram studyProgram) {
        //DALI ID E OK SS ANOTACIE SAMO??
        if(studies.stream().map(i->i.name).collect(Collectors.toSet()).contains(studyProgram.name)){
            throw new StudentDuplicateException();
        }
       studies.add(studyProgram);
        return studyProgram;
    }

    @Override
    public StudyProgram delete(long index) {

            Optional<StudyProgram> s=studies.stream().filter(i->i.id==index).findFirst();
            if(!s.isPresent()){
                throw new StudyProgramNotFoundException();
            }
            StudyProgram rez=s.get();
            studies.remove(rez);
            return rez;

    }
    @Override
    public StudyProgram deleteByName(String name) {

        Optional<StudyProgram> s=studies.stream().filter(i->i.name.equals(name)).findFirst();
        if(!s.isPresent()){
            throw new StudyProgramNotFoundException();
        }
        StudyProgram rez=s.get();
        studies.remove(rez);
        return rez;

    }
    @Override
    public StudyProgram update(String oldName,String newName) {
        Optional<StudyProgram> s=studies.stream().filter(i->i.name.equals(oldName)).findFirst();
        if(!s.isPresent()){
            throw new StudyProgramNotFoundException();
        }
        StudyProgram rez=s.get();
        studies.remove(rez);
        rez.name=newName;
        studies.add(rez);
        return rez;
    }
}
