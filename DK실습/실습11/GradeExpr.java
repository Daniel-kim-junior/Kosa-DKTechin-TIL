package exam;

import java.util.Arrays;

public class GradeExpr {
    private int[] jumsu;


    GradeExpr(int[] jumsu) {
        this.jumsu = jumsu;
    }

    double getAverage() {
        return Arrays.stream(jumsu).average().orElse(0.0);
    }

    int getTotal() {
        return Arrays.stream(jumsu).sum();
    }

    int getGoodScore() {
        return Arrays.stream(jumsu).max().getAsInt();
    }

    int getBadScore() {
        return Arrays.stream(jumsu).min().getAsInt();
    }

    @Override
    public String toString() {
        return "총점 : " + getTotal() + "\n" + "평균 : " +
                getAverage() + "\n"  + "최고 점수 : " +
                getGoodScore() + "\n" + "최저 점수 : " + getBadScore();
    }
}
