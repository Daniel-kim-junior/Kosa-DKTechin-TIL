package exam;

public class SalaryExpr {
    int bonus;

    public SalaryExpr() {
    }

    public SalaryExpr(int bonus) {
        this.bonus = bonus;
    }

    public int getSalary(int grade) {
        switch(grade) {
            case 1:
                return bonus + 100;
            case 2:
                return bonus + 90;
            case 3:
                return bonus + 80;
            case 4:
                return bonus + 70;
        }
        return bonus;
    }
}
