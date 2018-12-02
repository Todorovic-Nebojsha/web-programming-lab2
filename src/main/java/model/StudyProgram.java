package model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class StudyProgram {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public long id;
    public String name;
}
