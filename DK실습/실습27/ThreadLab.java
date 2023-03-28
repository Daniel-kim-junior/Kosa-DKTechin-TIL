package day17;

public class ThreadLab {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new ThreadOne());
        Thread thread2 = new Thread(new ThreadTwo());
        Thread thread3 = new Thread(new ThreadThree());
        Thread thread4 = new Thread(new ThreadFour());

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();


        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();

        System.out.println("MAIN  수행 종료");
    }
}
