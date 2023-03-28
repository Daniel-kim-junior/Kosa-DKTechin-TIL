package exercise;

public class Human {
    private String name;
    private int age;
    private int height;
    private int weight;

    public Human() {}

    public Human(String name, int age, int height, int weight) {
        this.age = age;
        this.name = name;
        this.height = height;
        this.weight = weight;
    }


    public String printInformation() {
        return name + " " + age + " " + height + " " + weight + " ";
    }
}
