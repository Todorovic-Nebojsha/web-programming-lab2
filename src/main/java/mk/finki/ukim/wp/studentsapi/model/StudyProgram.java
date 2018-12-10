package mk.finki.ukim.wp.studentsapi.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="study_program")
public class StudyProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    public String name;


    public StudyProgram() {
    }

    public StudyProgram(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
