package mk.finki.ukim.wp.studentsapi.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="study_program")
public class StudyProgram {
    public long id;
    public String name;
    public Set<Student> students;

    public StudyProgram() {
    }

    public StudyProgram(String name) {
        this.name = name;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @OneToMany(mappedBy = "studyProgram", cascade = CascadeType.ALL)
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
