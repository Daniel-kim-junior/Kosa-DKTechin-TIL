package jpamvcexam.model.vo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "student")
public class Student {
    @Id
    private String name;

    @Override
    public String toString() {
        return "Student{" +
               "name='" + name + '\'' +
               ", score=" + score +
               '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    private Integer score;
}
