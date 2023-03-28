package emp;

public class Company {
    public static void main(String[] args) {
        Employee employees[] = new Employee[2];
        employees[0] = new Secretary("Duke", 1,"Secretary" ,800 );
        employees[1] = new Sales("Tuxi", 2,"Sales" ,1200 );

        printEmployee(employees, false);
        ((Secretary) employees[0]).incentive(100);
        System.out.println("[인센티브 100 지급]");
        ((Sales) employees[1]).incentive(100);
        printEmployee(employees, true);


    }
    public static void printEmployee(Employee[] emp, boolean isTax) {
        if(isTax) {
            System.out.println("name\tdepartment\tsalary\ttax\textra pay");
            for(int i = 0; i < emp.length; i++) {
                if(emp[i] instanceof Sales) {
                    System.out.println(emp[i].getName() + "\t" + emp[i].getDepartment() + "\t"+ emp[i].getSalary() + "\t" + emp[i].tax() + "\t" + ((Sales)emp[i]).getExtraPay());
                } else {
                    System.out.println(emp[i].getName() + "\t" + emp[i].getDepartment() + "\t"+ emp[i].getSalary() + "\t" +  + emp[i].tax());
                }
            }
        } else {
            System.out.println("name\tdepartment\tsalary\textra pay");
            for(int i = 0; i < emp.length; i++) {
                if(emp[i] instanceof Sales) {
                    System.out.println(emp[i].getName() + "\t" + emp[i].getDepartment() + "\t"+ emp[i].getSalary() + "\t" + ((Sales) emp[i]).getExtraPay());
                } else {
                    System.out.println(emp[i].getName() + "\t" + emp[i].getDepartment() + "\t"+ emp[i].getSalary() + "\t");
                }
            }
        }

    }
}
