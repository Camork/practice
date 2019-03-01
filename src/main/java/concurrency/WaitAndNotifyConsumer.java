package concurrency;

/**
 * Created by camork on 2019-02-01.
 */
public class WaitAndNotifyConsumer {

	public static void main(String[] args) {
		Counter counter = new Counter();

		new Thread(new Odd(counter)).start();
		new Thread(new Even(counter)).start();
	}

}

class Odd implements Runnable {

	private final Counter _counter;

	public Odd(Counter counter) {
		_counter = counter;
	}

	@Override
	public void run() {
		try {
			while (_counter.num < 100000) {
				synchronized (_counter) {
					if (_counter.num % 2 == 1) {
						System.out.println(_counter.num);
						_counter.num += 1;
						_counter.notify();
					}

					_counter.wait();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

class Even implements Runnable {

	private final Counter _counter;

	public Even(Counter counter) {
		_counter = counter;
	}

	@Override
	public void run() {
		try {
			while (_counter.num < 100000) {
				synchronized (_counter) {
					if (_counter.num % 2 == 0) {
						System.out.println(_counter.num);
						_counter.num += 1;
						_counter.notify();
					}

					_counter.wait();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

class Counter {
	int num = 1;
}
