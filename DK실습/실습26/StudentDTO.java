package mvclab.model;


public class StudentDTO {
    private String name;
    private int score;

    public StudentDTO(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }



}
