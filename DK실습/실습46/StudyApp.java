package exam2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudyApp {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean2.xml");
        Student st1 = ac.getBean("st1", Student.class);

        Student st2 = ac.getBean("st2", Student.class);
        Student st3 = ac.getBean("st3", Student.class);

        System.out.println(st1);
        System.out.println(st2);
        System.out.println(st3);

    }
}
