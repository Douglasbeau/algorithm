package array;

// 选两个高度，装最多水
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int len = height.length;
        int l = 0;
        int r = len - 1;
        int leftMax = 0;
        int rightMax = 0;
        int ans = 0;

        while (l < r) {
            if (height[l] < leftMax) {
                l++;
                continue; // 只取更大的作为左
            }
            leftMax = height[l];
            if (height[r] < rightMax) {// 只取更大的作为右
                r--;
                continue;
            }
            rightMax = height[r];
            ans = Math.max(ans, (r - l) * Math.min(height[l], height[r]));
            if (height[l] < height[r]) {
                l++;
            } else if (height[l] == height[r]) {
                l++;
                r--;
            } else {
                r--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        final ContainerWithMostWater c = new ContainerWithMostWater();
        c.maxArea(new int[] {1, 8, 6, 2, 5, 4, 8, 3, 7});
    }
}
