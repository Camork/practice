package concurrency;

/**
 * Created by camork on 2019-02-01.
 */
public class VolatileConsumer {

	public static void main(String[] args) {
		Counter1 counter = new Counter1();

		new Thread(new Odd1(counter)).start();
		new Thread(new Even1(counter)).start();
	}

}

class Odd1 implements Runnable {

	private Counter1 _counter;

	public Odd1(Counter1 counter) {
		_counter = counter;
	}

	@Override
	public void run() {
		while (true) {
			while (_counter.num % 2 == 1 && _counter.num < 100000) {
				System.out.println(_counter.num + "---" + Thread.currentThread());
				_counter.num += 1;
			}
		}
	}
}

class Even1 implements Runnable {

	private Counter1 _counter;

	public Even1(Counter1 counter) {
		_counter = counter;
	}

	@Override
	public void run() {
		while (true) {
			while (_counter.num % 2 == 0 && _counter.num < 100000) {
				System.out.println(_counter.num + "---" + Thread.currentThread());

				_counter.num += 1;
			}
		}
	}
}

class Counter1 {
	volatile int num = 1;
}
