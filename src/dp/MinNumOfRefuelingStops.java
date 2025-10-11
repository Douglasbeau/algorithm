package dp;

import java.util.HashMap;
import java.util.Map;

// 最少加油次数
// 2处10升油，5处6升油，10处9升油，18处10升油，25处没有油
public class MinNumOfRefuelingStops {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        if (stations.length < 1) {
            return startFuel >= target ? 0: -1;
        }
//        int curFuel = startFuel;
//        int preLocation = 0;
//        for (int[] station : stations) {
//            curFuel = curFuel - (station[0] - preLocation);
//            if (curFuel < 0) {
//                return -1;
//            }
//            preLocation = station[0];
//            curFuel += station[1];
//        }
//        if (curFuel - (target - preLocation) < 0) {
//            return -1;
//        }

        return reachWithStops(target, startFuel - stations[0][0], stations, 0, new HashMap<>());
    }

    // 返回 到达第pos个加油站，还需要加油的最少次数
    private int reachWithStops(int target, int curFuel, int[][] stations, int pos, Map<String, Integer> dp) {
        String k = pos + "_" + curFuel;
        if (dp.containsKey(k)) {
//            System.out.println("hit");
            return dp.get(k);
        }
        // 到达了
        if (pos == stations.length && curFuel >= 0) {
            dp.put(k, 0);
            return 0;
        }
        // 没油了
        if (curFuel<0) {
            dp.put(k, -1);
            return -1;
        }
        // 剩下的是 没到达

        int distToNext;
        if ( pos != stations.length-1) {
            distToNext = stations[pos+1][0] - stations[pos][0];
        } else {
            distToNext = target - stations[pos][0];
        }
        // 此处加油，消耗的是当前减去前一个的位置
        int y = reachWithStops(target, curFuel - distToNext + stations[pos][1], stations, pos + 1, dp);
        int n = -1;
        // 此处不加油
        if (curFuel >= distToNext) {
            n = reachWithStops(target, curFuel - distToNext, stations, pos + 1, dp);
        }
        if (y == -1 && n == -1) {
            dp.put(k, -1);
            return -1;
        }
        if (n == -1) {
            dp.put(k, y+1);
            return y + 1;
        }
        int result = Math.min(y + 1, n);
        dp.put(k, result);
        return result;
    }

    // 在stations[pos][0] 需要的最少油量是result[pos]，
//    private int[] leastFuelToArrive(int[][] stations, int target) {
//        int[] result = new int[stations.length + 1];
//        result[0] = stations[0][0];
//        result[stations.length] = target - stations[stations.length-1][0];
//        for (int i=stations.length-1; i>0; i--) {
//            result[i] = stations[i+1][0] - stations[i][0];
//        }
//        return result;
//    }
    public static void main(String[] args) {
        MinNumOfRefuelingStops minNumOfRefuelingStops = new MinNumOfRefuelingStops();
        int startFuel = 2;
        int target = 30;
        int[][] stations = new int[][] {
                {2, 10}, {5, 6}, {10, 9}, {18, 10}, {25, 10}
        };
        int[][] stations2 = new int[][]{};
        int[][] stations3 = new int[][]{{10, 60}, {20, 30}, {30, 30}, {60, 40}};

        int[][] stations4 = new int[][] {{31,195796},{42904,164171},{122849,139112},{172890,121724},{182747,90912},{194124,112994},{210182,101272},{257242,73097},{284733,108631},{369026,25791},{464270,14596},{470557,59420},{491647,192483},{516972,123213},{577532,184184},{596589,143624},{661564,154130},{705234,100816},{721453,122405},{727874,6021},{728786,19444},{742866,2995},{807420,87414},{922999,7675},{996060,32691}};
        int i = minNumOfRefuelingStops.minRefuelStops(1000000, 8663, stations4);
        System.out.println(i);
    }
}
