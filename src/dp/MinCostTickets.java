package dp;

import java.util.Arrays;

public class MinCostTickets {
    public int mincostTickets(int[] days, int[] costs) {
        int[] result = new int[366];
        Arrays.fill(result, -1);
        return min(days, 0, costs, result);
    }

    // calc from index to the end of days
    private int min(int[] days, int index, int[] costs, int[] result) {
        if (index == days.length) {
            return 0;
        }
        if (result[days[index]] != -1) {
            return result[days[index]];
        }

        // use tickets of 1, 7, 30 days respectively
        int cost1, cost7, cost30;
        int nextIndex;

        // check the covered days
        nextIndex = nextIndex(days, index, 1);
        cost1 = costs[0] + min(days, nextIndex, costs, result);

        nextIndex = nextIndex(days, index, 7);
        cost7 = costs[1] + min(days, nextIndex, costs, result);

        nextIndex = nextIndex(days, index, 30);
        cost30 = costs[2] + min(days, nextIndex, costs, result);

        result[days[index]] = Math.min(Math.min(cost1, cost7), cost30);
        return result[days[index]];
    }

    private int nextIndex(int[] days, int i, int ticketDays) {
        int unreachable = days[i] + ticketDays;
        for (; i < days.length && days[i] < unreachable; i++) {
        }
        return i;
    }

    public static void main(String[] args) {
        MinCostTickets minCostTickets = new MinCostTickets();
        int[] days = {1,4,6,7,8,20};
        int[] costs = {2, 7, 15};
        long start = System.currentTimeMillis();
        int res = minCostTickets.mincostTickets(days, costs);
        System.out.printf("result: %s\ntime cost:%s\n",  res, System.currentTimeMillis() - start);
    }
}
