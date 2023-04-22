package exam3;

import org.springframework.stereotype.Component;

@Component("morningBean")
public class MorningGreetingImpl implements Greeting {
    @Override
    public void greet() {
        System.out.println("상쾌한 아침입니다.");
    }
}
