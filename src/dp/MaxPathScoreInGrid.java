package dp;

public class MaxPathScoreInGrid {
    public int maxPathScore(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][][] score = new int[m][n][k+1]; // 从i,j出发，往end能获得的最大分数
        // 跳到目标位置才有相应的分数和花费，所以end一定没有花销
        // int end = grid[m-1][n-1];
        // for (int x=0; x<=k; x++) {
        //     score[m-1][n-1][x] = end == 0 ? 0 : (x > 0 ? end : -1);
        // }

        // last row
        for (int j=n-2; j>=0; j--) {
            int r = grid[m-1][j+1];
            score[m-1][j][0] = r == 0 ? score[m-1][j+1][0] : -1;
            for (int x=1; x<=k; x++)
                score[m-1][j][x] = r == 0 ?
                        score[m-1][j+1][x] :
                        (score[m-1][j+1][x-1] < 0 ? -1: r + score[m-1][j+1][x-1]);
        }
        // last col
        for (int i=m-2; i>=0; i--) {
            int d = grid[i+1][n-1];
            score[i][n-1][0] = d == 0 ? score[i+1][n-1][0] : -1;
            for (int x=1; x<=k; x++)
                score[i][n-1][x] = d == 0 ?
                        score[i+1][n-1][x] :
                        (score[i+1][n-1][x-1] < 0 ? -1: d + score[i+1][n-1][x-1]);
        }
        for (int i=m-2; i>=0; i--) {
            for (int j=n-2; j>=0; j--) {
                // 往右或往下 取大者
                int r = grid[i][j+1];
                int d = grid[i+1][j];
                for (int x=0; x<=k; x++) {
                    score[i][j][x] = Math.max(
                            r == 0 ? score[i][j+1][x] : (x==0 || score[i][j+1][x-1]==-1?-1: r + score[i][j+1][x-1]),
                            d == 0 ? score[i+1][j][x] : (x==0 || score[i+1][j][x-1]==-1?-1: d + score[i+1][j][x-1])
                    );
                }
            }
        }

        return score[0][0][k];
    }
}
