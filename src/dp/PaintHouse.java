package dp;

// 一排三个房子，每个房子刷红、蓝、绿三色的价格不同，有数组costs给出
// 要求相邻房子不同色，返回都粉刷的最小花费
// TODO 递归改成动态规划
public class PaintHouse {
    public int minCost(int[][] costs) {
        int min = Integer.MAX_VALUE;
        // 缓存一下第m个刷的颜色是n时，后续花销的最小值
        int[][] dp = new int[costs.length][3];
        for (int m=0; m<costs.length; m++) {
            for (int n=0; n<3; n++) {
                dp[m][n] = -1;
            }
        }
        for (int i=0; i<3; i++) {
            min = Math.min(
                    min,
                    costs[0][i] + minCostForRemainder(costs, 1, i, dp)
                    );
        }
        return min;
    }

    // 假设已刷前p个，则第p个（从0开始）房子要做选择，使总花销最小
    // 当前p的颜色color，会决定后续的花销
    private int minCostForRemainder(int[][] costs, int p, int preColor, int[][] dp) {
        // 已经结束
        if (p == costs.length) {
            return 0;
        }
        int result;
        // 查询缓存 第p-1个是c色对应的结果
        if (dp[p-1][preColor] != -1) {
            return dp[p-1][preColor];
        }

        // 选颜色的优化——环形数组，去掉if else
        int curColor = (preColor+1) % 3;
        int curColor2= (curColor+1) % 3;
        int cur1 = minCostForRemainder(costs, p+1, curColor, dp);
        int cur2 = minCostForRemainder(costs, p+1, curColor2, dp);
        dp[p][curColor] = cur1;
        dp[p][curColor2] = cur2;
        result = Math.min(costs[p][curColor] + cur1, costs[p][curColor2] + cur2);
        return result;
    }

    public static void main(String[] args) {
        PaintHouse paintHouse = new PaintHouse();
        int[][] costs = new int[][] {
               //[[17,2,17],[16,16,5],[14,3,19]]
                {17, 2, 17},
                {16, 16, 5},
                {14, 30, 19}
        };

        int[][] costs1 = new int[][]{{18,12,18},{14,9,2},{10,9,13},{2,17,14},{18,18,6},{2,1,15},{19,20,2},{18,15,16},{20,18,18},{15,10,10},{2,20,18},{14,5,15},{18,10,12},{9,17,19},{9,1,6},{4,4,10},{7,8,15},{16,17,4},{16,16,13},{12,7,10},{14,13,8},{16,6,18},{10,5,10},{3,5,11},{9,9,6},{10,15,19},{4,5,19},{12,17,17}};
        long start = System.currentTimeMillis();
        int min = paintHouse.minCost(costs1);
        long end = System.currentTimeMillis();
        System.out.println(min);
        System.out.println("cost time: " + (end - start));
    }
}
