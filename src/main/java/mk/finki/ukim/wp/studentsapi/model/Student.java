package mk.finki.ukim.wp.studentsapi.model;

import javax.persistence.*;

@Entity
public class Student {

    public String index;
    public String name,lastName;
    public StudyProgram studyProgram;

    public Student() {
    }

    public Student(String index, String name, String lastName, StudyProgram studyProgram) {
        this.index = index;
        this.name = name;
        this.lastName = lastName;
        this.studyProgram = studyProgram;
    }
    public Student( String name, String lastName, StudyProgram studyProgram) {
        this.name = name;
        this.lastName = lastName;
        this.studyProgram = studyProgram;
    }
    public Student(String index, String name, String lastName) {
        this.index = index;
        this.name = name;
        this.lastName = lastName;
    }
    @Id
    public String getIndex(){
        return this.index;
    }
    /*public void setIndex(String index) {
        this.index = index;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @ManyToOne
    @JoinColumn(name = "study_program_id")
    public StudyProgram getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(StudyProgram studyProgram) {
        this.studyProgram = studyProgram;
    }



}
