package dp;

public class StockWithTXFee {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[] buy = new int[n]; // 第i天作为结尾持有的maxProfit
        buy[0] = -prices[0];
        int[] sell = new int[n]; // 第i天作为结尾空仓的maxProfit
        for (int i = 1; i < n; i++) {
            int cur = prices[i];
            buy[i] = Math.max(sell[i-1] - cur, buy[i-1]);
            sell[i] = Math.max(cur - fee + buy[i-1], sell[i-1]);
        }
        return sell[n-1];
    }

    public static void main(String[] args) {
        final StockWithTXFee stockWithTXFee = new StockWithTXFee();
        int[] p = new int[] {1, 3, 2, 8, 4, 9, 2, 4, 5, 6, 2, 1, 3};
        stockWithTXFee.maxProfit(p, 3);
    }
}
