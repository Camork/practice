package algorithm;

import java.util.ArrayList;

public class FloydWarShall {
    public static int M = Integer.MAX_VALUE;

    public static int MAXSUM(int a, int b) {
        return (a != M && b != M) ? (a + b) : M;
    }

    public static ArrayList<Integer[][]> flody(Integer[][] dist) {
        Integer[][] path = new Integer[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                path[i][j] = i;
            }
        }
        for (int k = 0; k < 5; k++) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (dist[i][j] > MAXSUM(dist[i][k], dist[k][j])) {
                        path[i][j] = path[k][j];
                        dist[i][j] = MAXSUM(dist[i][k], dist[k][j]);
                    }
                }
            }
        }
        ArrayList<Integer[][]> list = new ArrayList<Integer[][]>();
        list.add(dist);
        list.add(path);
        return list;
    }

    public static Integer[] reverse(Integer[] chain, int count) {
        int temp;
        for (int i = 0, j = count - 1; i < j; i++, j--) {
            temp = chain[i];
            chain[i] = chain[j];
            chain[j] = temp;
        }
        return chain;
    }

    public static void display_path(ArrayList<Integer[][]> list) {
        Integer[][] dist = list.get(0);
        Integer[][] path = list.get(1);
        Integer[] chain = new Integer[5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i != j) {
                    System.out.print("\n   " + (i) + "->" + (j) + "     ");
                    if (dist[i][j] == M) {
                        System.out.print(" NA ");
                    }
                    else {
                        System.out.print(dist[i][j] + "      ");
                        int count = 0;
                        int k = j;
                        do {
                            k = chain[count++] = path[i][k];
                        } while (i != k);
                        chain = reverse(chain, count);
                        System.out.print(chain[0] + "");
                        for (k = 1; k < count; k++) {
                            System.out.print("->" + (chain[k]));
                        }
                        System.out.print("->" + j);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[][] dist = {
                {0, M, M, 3, 5},
                {10, 0, 18, M, M},
                {5, M, 0, M, M},
                {M, M, 2, 0, M},
                {M, M, 2, 2, 0},
        };
        ArrayList<Integer[][]> list = flody(dist);
        display_path(list);
    }
}