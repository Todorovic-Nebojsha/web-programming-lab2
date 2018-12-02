package model;

import javax.persistence.Id;

public class Student {
    @Id
    public String index;
    public String name,lastName;
    public StudyProgram studyProgram;
}
