package algorithm;

import leetcode.TestCase;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class SortAlgorithms extends TestCase {

    int[] quickSort(int[] array, int L, int R) {
        int i = L;
        int j = R;

        int pivot = (L + R) / 2;

        while (i < j) {
            while (array[i] < array[pivot]) {
                i++;
            }
            while (array[j] > array[pivot]) {
                j--;
            }
            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;

                i++;
                j--;
            }

            if (L < j) {
                quickSort(array, L, j);
            }

            if (i < R) {
                quickSort(array, i, R);
            }
        }

        return array;
    }

    @Test
    void quickSort() {
        //                                 L       ij           R
        int[] result = quickSort(new int[]{75, 84, 26, 33, 92, 15}, 0, 5);
        assertArraysAscending(result);

        int[] result2 = quickSort(randomNumbers(1000), 0, 999);
        assertArraysAscending(result2);
    }

    @Test
    void insertionSort() {
        int[] arrays = randomNumbers(100);

        for (int i = 1; i < arrays.length; i++) {
            int temp = arrays[i];
            int j = i-1;//pre
            for (; j >= 0 && temp < arrays[j]; j--) {
                arrays[j+1] = arrays[j];
            }

            arrays[j+1] = temp;
        }

        assertArraysAscending(arrays);
    }

    @Test
    void selectionSort() {
        int[] arrays = randomNumbers(100);

        for (int i = 0; i < arrays.length; i++) {
            int minIndex = i;
            for (int j = i; j < arrays.length; j++) {
                if (arrays[j] < arrays[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arrays[minIndex];
            arrays[minIndex] = arrays[i];
            arrays[i] = temp;
        }

        assertArraysAscending(arrays);
    }

    @Test
    void shellSort() {
        int[] array = randomNumbers(10);
        int j, temp;

        //gap为步长,每次取半
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                temp = array[i];
                //第三个循环为每个步长的分组进行排序,算法和插入排序一样
                for (j = i; j >= gap && temp - array[j - gap] < 0; j -= gap) {
                    array[j] = array[j - gap];
                }
                array[j] = temp;
                System.out.println(Arrays.toString(array));
            }
        }

        assertArraysAscending(array);
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

        assertArraysAscending(array);
    }

}
