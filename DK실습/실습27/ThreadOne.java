package day17;

public class ThreadOne implements Runnable {
    @Override
    public void run() {
        char c = 'A';
        try {
            while(true) {
                System.out.println(c++);
                Thread.sleep(2000);
                if(c == 'M') return;
            }
        } catch (InterruptedException e) {
            System.out.println("자식 스레드 1 예외");
        }

    }
}
