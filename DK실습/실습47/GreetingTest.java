package exam3;

import java.time.LocalTime;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class GreetingTest {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("exam3.xml");

        Greeting morningBean = (Greeting) ac.getBean("morningBean");
        Greeting afterBean = (Greeting) ac.getBean("afternoonBean");
        Greeting eveningBean = (Greeting) ac.getBean("eveningBean");
        Greeting nightBean = (Greeting) ac.getBean("nightBean");
        LocalTime localTime = (LocalTime) ac.getBean("getTimeBean");

        int hour = localTime.getHour();

        if(hour >= 6 && hour <= 12) {
            morningBean.greet();
        } else if(hour > 12 && hour <= 17) {
            afterBean.greet();
        } else if(hour > 17 && hour <= 22) {
            eveningBean.greet();
        } else {
            nightBean.greet();
        }
        ((ClassPathXmlApplicationContext)ac).close();

    }

}
