package matrix;

// LC 59 螺旋填充
public class SpiralMatrix {
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int num = 1;
        int x = 0, y = 0; // start point
        int len = n - 1;
        while(len > 0) {
            int i, j;
            // 横向：x行，列从y增
            for (j=0; j<len; j++) {
                ans[x][y+j] = num++;
            }
            // 纵向：y+len列，行从x增
            for (i=0; i<len; i++) {
                ans[x+i][y+len] = num++;
            }
            // 横向：x+len行，列从y+len减
            for (j=0; j<len; j++) {
                ans[x+len][y+len-j] = num++;
            }
            // 纵向：y列，行从x+len减
            for (i=0; i<len; i++) {
                ans[x+len-i][y] = num++;
            }
            x++;
            y++;
            len -= 2;
        }
        // 奇数，最后填补中心
        if ((n & 1) == 1) {
            ans[x][y] = num;
        }
        return ans;
    }
}
