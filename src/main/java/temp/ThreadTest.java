package temp;

public class ThreadTest {
	private boolean running = true;

	public synchronized void test() {
		new Thread(
			() -> {
				int counter = 0;
				A s = () -> running;

				while (s.get()) {
					counter++;
				}

				System.out.println("Thread 1 finished. Counted up to " + counter);
			}).start();

	}

	interface A{
		public boolean get();
	}


	public static synchronized void doss(ThreadTest threadTest){
		try {
			Thread.sleep(500);
		}
		catch (InterruptedException ignored) {
			// catch block
		}
		System.out.println("Thread 2 finishing");
		threadTest.running = false;
	}

	public static void main(String[] args) {
		ThreadTest threadTest = new ThreadTest();
		threadTest.test();

		doss(threadTest);
	}
}