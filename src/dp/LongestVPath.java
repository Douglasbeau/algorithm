package dp;

public class LongestVPath {
    public int lenOfVDiagonal(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] len = new int[4][m * n]; // 右下 右上 左上 左下 对角线段长度
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1)
                    continue;
                int idx = i * m + j;
                if (i == 0) {
                    len[1][idx] = 1;
                    len[2][idx] = 1;
                }
                else if (j == 0) {
                    len[2][idx] = 1;
                    boolean flag = grid[i][j] + grid[i-1][1] == 2;
                    len[1][idx] = flag ? len[1][idx - m + 1] + 1 : 1;
                }
                else if (j == m - 1) {
                    len[1][idx] = 1;
                    boolean flag = grid[i][j] + grid[i-1][m-2] == 2;
                    len[2][idx] = flag ? len[2][idx - m - 1] + 1 : 1;
                } else {
                    boolean flag = grid[i][j] + grid[i-1][j+1] == 2;
                    len[1][idx] = flag ? len[1][idx - m + 1] + 1 : 1;
                    flag = grid[i][j] + grid[i-1][j-1] == 2;
                    len[2][idx] = flag ? len[2][idx - m - 1] + 1 : 1;
                }
            }
        }
        // 右下 左下
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1)
                    continue;
                int idx = i * m + j;
                if (i == n - 1) {
                    len[0][idx] = 1;
                    len[3][idx] = 1;
                }
                else if (j == 0) {
                    len[3][idx] = 1;
                    boolean flag = grid[i][j] + grid[i+1][1] == 2;
                    len[0][idx] = flag ? len[0][idx + m + 1] + 1 : 1;
                }
                else if (j == m - 1) {
                    len[0][idx] = 1;
                    boolean flag = grid[i][j] + grid[i+1][m-2] == 2;
                    len[3][idx] = flag ? len[3][idx + m - 1] + 1 : 1;
                } else {
                    boolean flag = grid[i][j] + grid[i+1][j+1] == 2;
                    len[0][idx] = flag ? len[0][idx + m + 1] + 1 : 1;
                    flag = grid[i][j] + grid[i+1][j-1] == 2;
                    len[3][idx] = flag ? len[3][idx + m - 1] + 1 : 1;
                }
            }
        }
        int ans = 0;
        // 1 开始的 可以带转向的
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    int idx = i * m + j;
                    int l = 1;
                    // 右下
                    if (i < n - 1 && j < m - 1 && grid[i+1][j+1] == 2) {
                        for (int k = 1; k <= len[0][idx+m+1]; k++) {
                            int v = 0;
                            if (i + k < n && j + k < m)
                                v = len[3][(i+k)*m + j+k] - 1;

                            l = Math.max(l, 1 + k + v);
                        }
                    }
                    // 左下
                    if (i < n - 1 && j > 0 && grid[i+1][j-1] == 2) {
                        for (int k = 1; k <= len[3][idx+m-1]; k++) {
                            int v = 0;
                            if (i + k < n && j >= k)
                                v = len[2][(i+k)*m + j-k] - 1;
                            l = Math.max(l, 1 + k + v);
                        }
                    }
                    // 左上
                    if (i > 0 && j > 0 && grid[i-1][j-1] == 2) {
                        for (int k = 1; k<=len[2][idx-m-1]; k++) {
                            int v = 0;
                            if (i >= k && j >= k)
                                v = len[1][(i-k)*m + j-k] - 1;
                            l = Math.max(l, 1 + k + v);
                        }
                    }
                    // 右上
                    if (i > 0 && j < m - 1 && grid[i-1][j+1] == 2) {
                        for (int k = 1; k <= len[1][idx-m+1]; k++) {
                            int v = 0;
                            if (i >= k && j + k < m)
                                v = len[0][(i-k)*m + j+k] - 1;
                            l = Math.max(l, 1 + k + v);
                        }
                    }
                    ans = Math.max(ans, l);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        final LongestVPath longestVPath = new LongestVPath();
        int[][] grid = new int[][] {{1,2,2,2,2},{2,2,2,2,0},{2,0,0,0,0},{0,0,2,2,2},{2,0,0,2,0}};
        longestVPath.lenOfVDiagonal(grid);
    }
}

