package prefix_sum;

import java.util.Arrays;

// LC 2536 差分数组、前缀和
public class IncrementSubMatrices {
    // 法二 直接二维差分
    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] diff = new int[n+2][n+2];
        for (int[] q: queries) {
            int r1 = q[0] + 1;
            int c1 = q[1] + 1;
            int r2 = q[2] + 1;
            int c2 = q[3] + 1;
            diff[r1][c1]++;
            diff[r1][c2+1]--;
            diff[r2+1][c1]--;
            diff[r2+1][c2+1]++;
        }
        int[][] ans = new int[n][n];
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                diff[i][j] +=  diff[i-1][j] + diff[i][j-1] - diff[i-1][j-1];
                ans[i-1][j-1] = diff[i][j];
            }
        }
        return ans;
    }
    // 法一、一维差分
    // public int[][] rangeAddQueries(int n, int[][] queries) {
    //     int[][] ans = new int[n][n];
    //     for (int[] query : queries) {
    //         // 所有涉及的行 怎么优化？
    //         for (int l=query[0]; l<=query[2]; l++) {
    //             int[] line = ans[l];
    //             line[query[1]]++;
    //             if (query[3] < n-1)
    //                 line[query[3] + 1]--;
    //         }
    //     }
    //     // 遍历和累加
    //     for (int i=0; i<n; i++) {
    //         for (int j=1; j<n; j++) {
    //             ans[i][j] += ans[i][j-1];
    //         }
    //     }
    //     return ans;
    // }

    public static void main(String[] args) {
        IncrementSubMatrices incrementSubMatrices = new IncrementSubMatrices();
        int[][] ans = incrementSubMatrices.rangeAddQueries(3, new int[][]{{0, 0, 1, 1}, {1, 1, 2, 2}});
        for (int[] an : ans) {
            System.out.println(Arrays.toString(an));
        }
    }
}
