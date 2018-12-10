package mk.finki.ukim.wp.studentsapi.service.impl;

import mk.finki.ukim.wp.studentsapi.model.StudyProgram;
import mk.finki.ukim.wp.studentsapi.model.exceptions.StudyProgramDuplicateException;
import mk.finki.ukim.wp.studentsapi.model.exceptions.StudyProgramNotFoundException;
import mk.finki.ukim.wp.studentsapi.persistence.StudyProgramRepository;
import mk.finki.ukim.wp.studentsapi.repository.StudyProgramRepositoryInterface;
import mk.finki.ukim.wp.studentsapi.service.StudyProgramService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudyProgramServiceImpl implements StudyProgramService {
    final StudyProgramRepositoryInterface repo;
    public StudyProgramServiceImpl(StudyProgramRepositoryInterface repo){
        this.repo=repo;
    }
    @Override
    public List<StudyProgram> listAllStudyPrograms() {
        return repo.findAll();
    }




    @Override
    public void addNew(String studyProgram) throws StudyProgramDuplicateException {
         repo.save(new StudyProgram(studyProgram));
    }

    @Override
    public void delete(long index) throws StudyProgramNotFoundException {
         repo.deleteById(index);
    }

}
