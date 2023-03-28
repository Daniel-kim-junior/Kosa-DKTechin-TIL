package day17;

public class ThreadThree implements Runnable {
    @Override
    public void run() {
        int cnt = 1;
        try {
            while(true) {
                System.out.println(cnt++);
                Thread.sleep(1000);
                if(cnt == 31) {
                    return;
                }
            }
        } catch (InterruptedException e) {
            System.out.println("자식 스레드 3 예외");
        }

    }
}
