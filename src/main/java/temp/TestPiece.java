package temp;

import leetcode.TestCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by camork on 2019-01-19.
 */
public class TestPiece extends TestCase {

	@Test
	void testCollection() {
		Set<String> listeners = new LinkedHashSet<>();

		listeners.add("11");
		listeners.add("22");
		listeners.add("33");
		listeners.add("44");

		Collection<String> _listeners;

		_listeners = new ArrayList<>(listeners);

		for (String listener : _listeners) {
			listeners.remove(listener);
		}
	}

	@Test
	void testSwap() {
		Intw a = new Intw(1);
		Intw b = new Intw(2);
		System.out.println(a + "" + b);
		swap(a, b);
		System.out.println(a + "" + b);
	}

	void swap(Intw a, Intw b) {
		int c = a._a;
		a._a = b._a;
		b._a = c;
	}

	class Intw {
		int _a;
		public Intw(int a) { _a = a; }

		public String toString() { return "Intw{" + "_a=" + _a +'}'; }
	}

}
