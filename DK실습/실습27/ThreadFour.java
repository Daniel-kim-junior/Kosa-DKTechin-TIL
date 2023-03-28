package day17;

public class ThreadFour implements Runnable {

    @Override
    public void run() {
        try {
            int cnt = 0;
            while(cnt++ < 10) {
                System.out.println("JAVA");
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            System.out.println("자식 스레드4 예외");
        }

    }
}
