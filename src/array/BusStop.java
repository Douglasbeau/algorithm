package array;

import java.util.ArrayList;
import java.util.List;

// 环形公交站，s到dest最短距离
public class BusStop {

    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int l = Math.min(start, destination);
        int r = Math.max(start, destination);
        int result = 0;
        int l2r = 0;
        // 从0 经过起点遍历到终点
        for (int i=0; i< distance.length; i++) {
            // s->d之间
            if (i>=l && i<r) {
                l2r += distance[i];
            }
            result += distance[i];
        }

        return Math.min(l2r, result - l2r);
    }

    public static void main(String[] args) {
        BusStop busStop = new BusStop();
        int[] diss = new int[] {1,2,3,4};
        int i = busStop.distanceBetweenBusStops(diss, 1, 3);
        System.out.println(i);
    }
}
