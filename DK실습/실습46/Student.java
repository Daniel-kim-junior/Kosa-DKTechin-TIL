package exam2;

public class Student {
    private String name;
    private Homework homework;

    public Student() {}

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName()+"는 " + homework.getHomeworkName() + "(을/를) 학습합니다.";
    }

    public void setName(String name) {
        this.name = name;
    }

    public Homework getHomework() {
        return homework;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }
}
