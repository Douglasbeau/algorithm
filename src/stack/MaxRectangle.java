package stack;

// LC 84 柱状图的最大矩形面积
public class MaxRectangle {
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
        final MaxRectangle maxRectangle = new MaxRectangle();
        int[] h = new int[]{1, 0};
        maxRectangle.maxArea(h);
    }
}
