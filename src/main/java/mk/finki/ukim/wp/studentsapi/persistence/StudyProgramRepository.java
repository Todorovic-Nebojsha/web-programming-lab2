package mk.finki.ukim.wp.studentsapi.persistence;

import mk.finki.ukim.wp.studentsapi.model.*;

import java.util.List;

public interface StudyProgramRepository {
    List<StudyProgram> listAllStudyPrograms();

    StudyProgram getStudyProgramByIndex(long index);

    StudyProgram getStudyProgramByName(String name);

    StudyProgram addNew(StudyProgram studyProgram);

    StudyProgram delete(long index);

    StudyProgram deleteByName(String name);

    StudyProgram update(String oldName,String newName);
}

