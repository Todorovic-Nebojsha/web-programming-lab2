package mk.finki.ukim.wp.studentsapi.service.impl;

import mk.finki.ukim.wp.studentsapi.model.Student;
import mk.finki.ukim.wp.studentsapi.model.StudyProgram;
import mk.finki.ukim.wp.studentsapi.model.exceptions.BadParametarsException;
import mk.finki.ukim.wp.studentsapi.model.exceptions.StudyProgramDuplicateException;
import mk.finki.ukim.wp.studentsapi.model.exceptions.StudyProgramNotFoundException;
import mk.finki.ukim.wp.studentsapi.repository.StudentRepositoryInterface;
import mk.finki.ukim.wp.studentsapi.repository.StudyProgramRepositoryInterface;
import mk.finki.ukim.wp.studentsapi.service.StudyProgramService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudyProgramServiceImpl implements StudyProgramService {
    final StudyProgramRepositoryInterface repo;
    final StudentRepositoryInterface studentRepo;
    public StudyProgramServiceImpl(StudyProgramRepositoryInterface repo,StudentRepositoryInterface repo1){
        this.repo=repo;
        this.studentRepo=repo1;
    }
    @Override
    public List<StudyProgram> listAllStudyPrograms() {
        return repo.findAll();
    }




    @Override
    public void addNew(String studyProgram) throws StudyProgramDuplicateException {
        if(repo.findAll().stream().filter(i->i.name.equals(studyProgram)).count()!=0)
            throw new StudyProgramDuplicateException();
         repo.save(new StudyProgram(studyProgram));
    }

    @Override
    public void delete(long index) throws StudyProgramNotFoundException {
        Optional<StudyProgram> program=repo.findById(index);
        if(!program.isPresent())
            throw new StudyProgramNotFoundException();

        Long students=studentRepo.findAll().stream().filter(i->i.studyProgram.name.equals(program.get().name)).count();
        if(students==0){
            repo.deleteById(index);
        }
        else{
            throw new BadParametarsException();
        }
}

}
