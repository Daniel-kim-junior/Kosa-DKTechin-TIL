package exam;

public class SalaryExam {
    public static void main(String[] args) {
        int month = getRandom(12, 1);
        int grade = getRandom(4, 1);
// 보너스달
        if(month % 2 == 0) {
            SalaryExpr sal = new SalaryExpr(100);
            System.out.println(month + "월 " + grade + "등급의 월급은 " + sal.getSalary(grade) + "입니다.");
        } else {
            SalaryExpr sal = new SalaryExpr();
            System.out.println(month + "월 " + grade + " 등급의 월급은 " + sal.getSalary(grade) + "입니다.");

        }

    }

    public static int getRandom(int n, int m) {
        return (int) (Math.random() * n) + m;
    }
}
