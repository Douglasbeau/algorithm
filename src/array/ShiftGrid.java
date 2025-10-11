package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShiftGrid {

    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int numRow = grid.length;
        int numCol = grid[0].length;
        int total = grid.length * grid[0].length;
        int[] starts = new int[k]; // 暂存从数组最后来前面 挤掉的数字
        k = k%total; // 防止k大于格子数
        // 对于每个k，都遍历一次
        int i = 0;
        while(i<k) {
            // 每个k对应于不同的起点
            int j = i;
            int next = grid[j/numCol][j%numCol];
            int cur;
            while(j<total) {
                cur = next;
                int nextPos = j + k;
                // 如果到头了
                if(nextPos >= total) {
                    starts[nextPos%total] = next;
                    break;
                }
                next = grid[nextPos/numCol][nextPos%numCol];
                // 当前数 cover下一个位置
                grid[nextPos/numCol][nextPos%numCol] = cur;
                j = nextPos;
            }

            i++;
        }
        // 最后将starts放放过来
        for(i=0; i<starts.length; i++) {
            grid[i/numCol][i%numCol] = starts[i];
        }
        // result
        List<List<Integer>> result = new ArrayList<>(numRow);
        for (i=0; i<numRow; i++) {
            List<Integer> list = new ArrayList<>(numCol);
            for (int j = 0; j < numCol; j++) {
                list.add(grid[i][j]);
            }
            result.add(list);
        }
        return result;
    }

    public static void main(String[] args) {
        ShiftGrid shiftGrid = new ShiftGrid();
        int[][] grid = new int[][]{{1, 2, 3, 4, 5}, {6,7,8,9,10}};
        int[][] grid2 = new int[][]{{1, 2, 3}, {4,5,6},{7,8,9}};
        int[][] grid3 = new int[][]{{1}, {2}, {3}, {4},{5},{6},{7}};
        int k = 2;
        List<List<Integer>> lists = shiftGrid.shiftGrid(grid3, k);
        System.out.println(lists);
    }
}
