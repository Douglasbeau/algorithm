package matrix;

// LC 994
public class RottingOrange {
    public int orangesRotting(int[][] grid) {
        int fresh = 0;
        int rot = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1)
                    fresh++;
                else if (grid[i][j] == 2)
                    rot++;
            }
        }
        if (fresh == 0)
            return 0;
        if (rot == 0)
            return -1;

        int totalInfected = 0;
        int infected = 0;
        int m = 0;
        do {
            infected = infect(grid);
            if (infected != 0)
                m++;
            totalInfected += infected;
        } while(infected != 0);

        return totalInfected == fresh ? m : -1;
    }

    int infect(int[][] grid) {
        int infected = 0;
        int row = grid.length;
        int col = grid[0].length;
        int[] newlyInf = new int[(row * col + 31) >> 5];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (getBit(newlyInf, col, i, j))
                    continue;
                if (grid[i][j] == 2) {
                    // 上
                    if (i - 1 >= 0 && grid[i-1][j] == 1) {
                        infected++;
                        grid[i-1][j] = 2;
                        setBit(newlyInf, i-1, col, j);
                    }
                    // 下
                    if (i + 1 < grid.length && grid[i+1][j] == 1) {
                        infected++;
                        grid[i+1][j] = 2;
                        setBit(newlyInf, i+1, col, j);
                    }
                    // 左
                    if (j - 1 >= 0 && grid[i][j-1] == 1) {
                        infected++;
                        grid[i][j-1] = 2;
                        setBit(newlyInf, i, col, j-1);
                    }
                    // 右
                    if (j + 1 < grid[0].length && grid[i][j+1] == 1) {
                        infected++;
                        grid[i][j+1] = 2;
                        setBit(newlyInf, i, col, j+1);
                    }
                }
            }
        }
        return infected;
    }
    boolean getBit(int[] bitMap, int i, int n, int j) {
        int idx = (i * n + j) >> 5;
        int offset = (i * n + j) & 0x1f;
        return (bitMap[idx] >> offset & 1) == 1;
    }

    void setBit(int[] bitMap, int i, int n, int j) {
        int idx = (i * n + j) >> 5;
        int offset = (i * n + j) & 0x1f;
        int upd = bitMap[idx] | (1 << offset);
        bitMap[idx] = upd;
    }

    public static void main(String[] args) {
        final RottingOrange rottingOrange = new RottingOrange();
        final int i = rottingOrange.orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}});
        System.out.println(i);
    }
}
