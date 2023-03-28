package day17;

public class ThreadTwo implements Runnable {
    @Override
    public void run() {
        char c = 'a';
        try {
            while(true) {
                System.out.println(c++);
                Thread.sleep(3000);
                if(c == 'i') {
                    return;
                }
            }
        } catch (InterruptedException e) {
            System.out.println("자식 스레드 2 예외");
        }

    }
}
