package exercise;

public class Student extends Human {
    private String number;
    private String major;



    public Student(String name, int age, int height, int weight, String number, String major) {
        super(name, age, height, weight);
        this.number = number;
        this.major = major;
    }

    @Override
    public String printInformation() {
        return super.printInformation() + " " + number + " " + major;
    }
}
