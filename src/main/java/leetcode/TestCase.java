package leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by camork on 2018/9/4.
 */
public class TestCase extends Assertions {

	public static void assertArraysAscending(int[] array) {
		for (int i = 1; i < array.length - 1; i++) {
			assertTrue(array[i] >= array[i - 1], array[i] + ">" + array[i - 1]);
		}
	}

	public static void assertArraysDescending(int[] array) {
		for (int i = 0; i < array.length - 2; i++) {
			assertTrue(array[i] >= array[i + 1], array[i] + ">" + array[i + 1]);
		}
	}

	public static int[] randomNumbers() {
		return randomNumbers(20);
	}

	public static int[] randomNumbers(int size) {
		int[] array = new int[size];
		Random r = new Random();
		for (int i = 0; i < size; i++) {
			array[i] = r.nextInt(size);
		}

		System.out.println(Arrays.toString(array));
		return array;
	}

}
