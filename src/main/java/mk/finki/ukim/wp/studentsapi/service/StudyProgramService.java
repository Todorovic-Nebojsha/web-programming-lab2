package mk.finki.ukim.wp.studentsapi.service;

import mk.finki.ukim.wp.studentsapi.model.StudyProgram;
import mk.finki.ukim.wp.studentsapi.model.exceptions.StudyProgramDuplicateException;
import mk.finki.ukim.wp.studentsapi.model.exceptions.StudyProgramNotFoundException;

import java.util.List;

public interface StudyProgramService {
    List<StudyProgram> listAllStudyPrograms();

    StudyProgram getStudyProgramByIndex(long index) throws StudyProgramNotFoundException;

    StudyProgram getStudyProgramByName(String name) throws StudyProgramNotFoundException;

    StudyProgram addNew(StudyProgram studyProgram) throws StudyProgramDuplicateException;

    StudyProgram delete(long index) throws StudyProgramNotFoundException;

    StudyProgram deleteByName(String name) throws StudyProgramNotFoundException;

    StudyProgram update(String oldName,String newName) throws StudyProgramNotFoundException ;
}
