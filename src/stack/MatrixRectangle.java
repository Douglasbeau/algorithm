package stack;

// LC 85 矩阵中全是1的最大矩形
public class MatrixRectangle {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] heights = new int[n];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++){
                int val = matrix[i][j] - '0';
                if (val == 1) {
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }
            ans = Math.max(ans, maxArea(heights));
        }
        return ans;
    }

    // 有连续1的高度，求面积
    // [2 1 2 3] 单调栈（递增），找两边比[i]矮的
    private int maxArea(int[] h) {
        int n = h.length;
        int[] stack = new int[n];
        int si = -1;
        int max = 0;
        for (int i = 0; i < n; i++) {
            while(si > -1 && h[stack[si]] >= h[i]) {
                int pop = stack[si--];
                if (h[pop] > h[i]) {
                    int width = si == -1 ? i : i - stack[si] - 1;
                    max = Math.max(max, h[pop] * width);
                }
            }
            stack[++si] = i;
        }
        while (si > -1) {
            int pop = stack[si--];
            int width = si == -1 ? n : n - stack[si] - 1;
            max = Math.max(max, h[pop] * width);
        }
        return max;
    }

    public static void main(String[] args) {
        char[][] mat= new char[][]{{'1', '0', '1', '1'}, {'1', '0', '1', '1'}};
        MatrixRectangle matrix = new MatrixRectangle();
        int i = matrix.maximalRectangle(mat);
        System.out.println(i);
    }
}
