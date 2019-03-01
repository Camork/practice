package concurrency;

/**
 * Created by camork on 2019-02-01.
 */
public class DeadLock {

	public static void main(String[] args) {
		Lock lock = new Lock();

		Runner1 runner1 = new Runner1(lock);
		Runner2 runner2 = new Runner2(lock);

		lock._runner1 = runner1;
		lock._runner2 = runner2;

		new Thread(runner1).start();
		new Thread(runner2).start();
	}

}

class Runner1 implements Runnable {

	private final Lock _lock;

	public Runner1(Lock lock) {
		_lock = lock;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (this) {
				synchronized (_lock._runner2) {
					System.out.println("locked runner2");
				}
			}
		}
	}
}

class Runner2 implements Runnable {

	private final Lock _lock;

	public Runner2(Lock lock) {
		_lock = lock;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (this) {
				synchronized (_lock._runner1) {
					System.out.println("locked runner1");
				}
			}
		}
	}
}

class Lock {
	Runner1 _runner1;
	Runner2 _runner2;
}
