package greedy;

// LC 采购的最低花费
public class MinCost {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        // 贪心：3好用则尽量用；甚至买过剩的类型1or2物品
        long ans = (long) cost1 * need1 + (long) cost2 * need2;//不用3
        ans = Math.min(ans, (long) costBoth * Math.max(need1, need2)); // 全用3
        // 部分用3
        long part3 = (long) costBoth * Math.min(need1, need2) +
                (need1 > need2 ? (long) cost1 * (need1 - need2) : (long) cost2 * (need2 - need1));
        ans = Math.min(ans, part3);
        return ans;
    }

    public static void main(String[] args) {
        MinCost mc = new MinCost();
        long c = mc.minimumCost(1, 2, 2, 4, 5);
        System.out.println(c);
    }
}
