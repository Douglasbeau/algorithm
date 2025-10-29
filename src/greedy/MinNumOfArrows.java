package greedy;

import java.util.Arrays;
import java.util.Comparator;

// LC 452
// 注意：针对测试用例的数据范围，比较器不能写成(a, b) -> a[0] - b[0])，数据溢出！
public class MinNumOfArrows {
    public int findMinArrowShots(int[][] points) {
        // 有交集区间问题
        // 起点从小到大排序，
        Arrays.sort(points, Comparator.comparingInt(a -> a[0]));
        // 记录最小的终点，它一定需要一支箭，贪心——带上所有与之有交集的气球
        int n = points.length;
        int minEnd = points[0][1];
        int ans = 1;
        for (int i = 1; i < n; i++) {
            // 起点靠右，需要另一支箭，记录新的end
            if (points[i][0] > minEnd) {
                ans++;
                minEnd = points[i][1];
            }
            // 更新minEnd
            else {
                minEnd = Math.min(minEnd, points[i][1]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        final MinNumOfArrows minNumOfArrows = new MinNumOfArrows();
        int minArrowShots = minNumOfArrows.findMinArrowShots(new int[][]{{-2147483646, -2147483645}, {2147483646, 2147483647}});
        System.out.println(minArrowShots);
    }
}
