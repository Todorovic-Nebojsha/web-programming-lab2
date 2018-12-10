package mk.finki.ukim.wp.studentsapi.model;

import javax.persistence.*;

@Entity
@Table(name="Students")
public class Student {

    @Id
    public String index;
    public String name,lastName;
    @ManyToOne
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

    public String getIndex(){
        return this.index;
    }
    public void setIndex(String index) {
        this.index = index;
    }

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


    public StudyProgram getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(StudyProgram studyProgram) {
        this.studyProgram = studyProgram;
    }



}
