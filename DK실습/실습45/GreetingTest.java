package exam1;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sample4.AbstractTest;
import sample4.Friday;
import sample4.Monday;
import sample4.Saturday;
import sample4.Sunday;
import sample4.Thursday;
import sample4.Tuesday;
import sample4.Wednesday;

public abstract class GreetingTest {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("exam1.xml");

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

    }

}
