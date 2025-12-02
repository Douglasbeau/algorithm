package map;

import java.util.HashMap;
import java.util.Map;

// LC 3623 给你点的集合 统计水平梯形的数目
// 关键在于分层统计线段数，遍历每层时，从它选一个边，从遍历过的所有层中选另一边
public class Trapezoids {
    public int countTrapezoids(int[][] points) {
        Map<Integer, Integer> levelDots = new HashMap<>();
        int MOD = 1000000007;
        for (int[] point: points) {
            int cnt = levelDots.getOrDefault(point[1], 0);
            levelDots.put(point[1], cnt + 1);
        }
        // 有1层
        if (levelDots.size() < 2)
            return 0;
        int ans = 0;
        int preSum = 0;
        // 遍历每层，计算 以当前层为底边能拼成的梯形数：cur * (cnt1 + cnt2)
        for (int val : levelDots.values()) {
            if (val == 1)
                continue;
            long cur = (long)val * (val - 1) / 2; // 层的线段数量
            ans = (int)((ans + preSum * cur) % MOD); // 以当前层为底边，另一边来自遍历过的层
            preSum += (int) cur;
        }
        return ans;
    }

    public static void main(String[] args) {
        Trapezoids trapezoids = new Trapezoids();
        int i = trapezoids.countTrapezoids(new int[][]{{1, 0}, {2, 0}, {3, 0}, {1, 2}, {2, 2}});
        System.out.println(i);
    }
}
