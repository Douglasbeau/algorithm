package array;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MaxEventsToAttend {
    public int maxEvents(int[][] events) {
        Arrays.sort(events,
                (e1, e2) -> e1[0] == e2[0] ? e1[1] - e2[1]: e1[0] - e2[0]); // start小的 end小的
        PriorityQueue<Integer> endQ = new PriorityQueue<>((i1, i2) -> i1 - i2); //end小的
        int[] cur = events[0];
        int i = 1;
        int s = cur[0];
        int ans = 1;
        while (i < events.length) {
            // 拿出所有s+1及其前面开始的，按照end排序
            while (i < events.length && events[i][0] <= s + 1){
                cur = events[i++];
                if (cur[1] > s)
                    endQ.add(cur[1]);
            }
            while (!endQ.isEmpty()) {
                int curEnd = endQ.poll();
                if (curEnd > s) {
                    ans++;
                    s++;
                    if (i < events.length && events[i][0] <= s + 1)
                        break;
                }
            }
            if (i < events.length) {
                if (events[i][0] > s + 1) {
                    cur = events[i++];
                    s = cur[0];
                    ans++;
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        final MaxEventsToAttend m = new MaxEventsToAttend();
        int[][] es = new int[5][];
        es[0] = new int[] {1, 2};
        es[1] = new int[] {1, 5};
        es[2] = new int[] {3, 3};
        es[3] = new int[] {1, 2};
        es[4] = new int[] {1, 5};
        final int i = m.maxEvents(es);
        System.out.println(i);
    }
}
