package mk.finki.ukim.wp.studentsapi.service;

import mk.finki.ukim.wp.studentsapi.model.StudyProgram;
import mk.finki.ukim.wp.studentsapi.model.exceptions.StudyProgramDuplicateException;
import mk.finki.ukim.wp.studentsapi.model.exceptions.StudyProgramNotFoundException;

import java.util.List;

public interface StudyProgramService {

    List<StudyProgram> listAllStudyPrograms();


    void addNew(String studyProgram) throws StudyProgramDuplicateException;

    void delete(long index) throws StudyProgramNotFoundException;


}
