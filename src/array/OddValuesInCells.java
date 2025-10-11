package array;

import java.util.HashSet;
import java.util.Set;

public class OddValuesInCells {

    public int oddCells(int m, int n, int[][] indices) {
        Set<Integer> hori = new HashSet<>();
        Set<Integer> vert = new HashSet<>();
        for (int[] index : indices) {
            // 存在横线则删掉，否则添加
            if (hori.contains(index[0]))
                hori.remove(index[0]);
            else
                hori.add(index[0]);
            // 存在竖线则删掉，否则添加
            if (vert.contains(index[1]))
                vert.remove(index[1]);
            else
                vert.add(index[1]);
        }
        // 横线数*竖线数 以及  都是偶数，
        int a = hori.size();
        int b = vert.size();
        return m*n - a * b - (m-a)*(n-b);
    }

    public static void main(String[] args) {
        OddValuesInCells o = new OddValuesInCells();
        int i = o.oddCells(48, 37, new int[][]{{40, 5}});
        System.out.println(i);
    }
}
