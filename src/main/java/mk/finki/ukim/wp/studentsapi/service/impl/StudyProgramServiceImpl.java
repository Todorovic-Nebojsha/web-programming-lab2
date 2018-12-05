package mk.finki.ukim.wp.studentsapi.service.impl;

import mk.finki.ukim.wp.studentsapi.model.StudyProgram;
import mk.finki.ukim.wp.studentsapi.model.exceptions.StudyProgramDuplicateException;
import mk.finki.ukim.wp.studentsapi.model.exceptions.StudyProgramNotFoundException;
import mk.finki.ukim.wp.studentsapi.persistence.StudyProgramRepository;
import mk.finki.ukim.wp.studentsapi.service.StudyProgramService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudyProgramServiceImpl implements StudyProgramService {
    final StudyProgramRepository repo;
    public StudyProgramServiceImpl(StudyProgramRepository repo){
        this.repo=repo;
    }
    @Override
    public List<StudyProgram> listAllStudyPrograms() {
        return repo.listAllStudyPrograms();
    }

    @Override
    public StudyProgram getStudyProgramByIndex(long index) throws StudyProgramNotFoundException {
        return repo.getStudyProgramByIndex(index);
    }

    @Override
    public StudyProgram getStudyProgramByName(String name) throws StudyProgramNotFoundException {
        return repo.getStudyProgramByName(name);
    }

    @Override
    public StudyProgram addNew(StudyProgram studyProgram) throws StudyProgramDuplicateException {
        return repo.addNew(studyProgram);
    }

    @Override
    public StudyProgram delete(long index) throws StudyProgramNotFoundException {
        return repo.delete(index);
    }

    @Override
    public StudyProgram deleteByName(String name) throws StudyProgramNotFoundException {
        return repo.deleteByName(name);
    }

    @Override
    public StudyProgram update(String oldName, String newName) throws StudyProgramNotFoundException {
        return repo.update(oldName,newName);
    }
}
