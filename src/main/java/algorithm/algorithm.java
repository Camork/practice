package algorithm;


import leetcode.TestCase;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class algorithm extends TestCase {

	public static void main(String[] args) {
	}

	int[] quickSort(int[] array, int L, int R) {
		int i = L;
		int j = R;

		int pivot = array[(L + R) / 2];

		while (i < j) {
			//找大于的去换
			while (array[i] < pivot) {
				i++;
			}
			//找小于的去换
			while (array[j] > pivot) {
				j--;
			}
			//停在2边都需要切换的点,然后交换
			if (i <= j) {
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				i++;
				j--;
			}
		}

		if (L < j) {
			quickSort(array, L, j);
		}

		if (i < R) {
			quickSort(array, i, R);
		}

		System.out.println(Arrays.toString(array));
		return array;
	}

	@Test
	void quickSort() {
		int[] array = {75, 84, 26, 33, 92, 15};
		//             L       ij           R
		System.out.println(Arrays.toString(array));
		int[] result = quickSort(array, 0, 5);
		System.out.println(Arrays.toString(result));

		assertArraysAscending(result);
	}

	@Test
	void ShellSort() {

		int[] array = randomNumbers();
		int j, temp;

		System.out.println(Arrays.toString(array));
		//gap为步长,每次取半
		for (int gap = array.length / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < array.length; i++) {
				temp = array[i];
				//第三个循环为每个步长的分组进行排序,算法和插入排序一样
				for (j = i; j >= gap && temp - array[j - gap] < 0; j -= gap)
					array[j] = array[j - gap];
				array[j] = temp;
				System.out.println(Arrays.toString(array));
			}
		}
	}

	@Test
	void bubbleSort() {
		int[] array = randomNumbers(100);

		for (int i = 0; i < array.length; i++) {
			for (int j = i; j < array.length; j++) {
				if (array[i] > array[j]) {
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}

//
//        int i, j;
//
//        for (i = 0; i < array.length - 1; i++) {
//            int k = i;
//            for (j = i; j < array.length; j++) {
//
//                if (array[j] < array[k]) {
//                    k = j;
//                }
//            }
//            int temp = array[i];
//            array[i] = array[k];
//            array[k] = temp;
//
//
//        }


//        for (int i = 0; i < array.length - 1; i++) {
//            for (int j = 0; j < array.length - 1; j++) {
//                if (array[j] > array[j + 1]) {
//                    int temp = array[j];
//                    array[j] = array[j + 1];
//                    array[j + 1] = temp;
//                }
//            }
//        }
		System.out.println(Arrays.toString(array));

		assertArraysAscending(array);
	}

}
