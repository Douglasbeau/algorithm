package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EarliestAndLatestRounds {
    Map<Integer, int[]> map;
    public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
        map = new HashMap<>();
        return process(n , firstPlayer, secondPlayer);
    }

    // first和second将最快、最慢在第几回合遭遇
    int[] process(int n, int first, int second) {
        if (first + second == n + 1) {
            return new int[]{1, 1};
        }
        if (first > n + 1 - second) { // 重心放左边
            int tmp = first;
            first = n + 1 - second;
            second = n + 1 - tmp;
        }
        int key = getkey(n, first, second);
        if (map.containsKey(key)) {
            return map.get(key);
        }
        int left = first - 1; // 左边可fail的
        int fail = n >> 1;
        int rest = n - fail;
        int min = Integer.MAX_VALUE;
        int max = 0;

        // first和second之间 最少最多淘汰数
        int floor;
        int ceiling;
        if (second <= (n + 1)/2) { // second在左边
            floor = 0;
            ceiling = second - first - 1;
        } else { // second 在右边
            floor = second - (n+1)/2;
            ceiling = n/2 - first;
        }
        // 中间fail的个数
        for (int i = floor; i <= ceiling; i++) {
            // 左边fail个数
            for (int j = 0; j <= left; j++) {
                int[] rounds = process(rest, first - j, second - i - j);
                min = Math.min(min, rounds[0] + 1);
                max = Math.max(max, rounds[1] + 1);
            }
        }
        map.put(key, new int[] {min, max});
        return new int[] {min, max};
    }

    int getkey(int n, int f, int s) {
        return (n << 16) | (f << 4) | s;
    }

    public static void main(String[] args) {
        final EarliestAndLatestRounds el = new EarliestAndLatestRounds();
        final int[] ints = el.earliestAndLatest(9, 2, 6);
        System.out.println(Arrays.toString(ints));
    }
}
