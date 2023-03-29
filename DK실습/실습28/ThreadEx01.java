package lamdalab;

class ThreadEx01 {
	public static void main(String args[]) {



		Thread t1 = new Thread(() -> {
			for(int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName());
			}
		}); // 생성자 Thread(Runnable target)

		Thread t2 = new Thread(() -> {
			for(int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName());
			}
		});

		t1.start();
		t2.start();
	}
}
