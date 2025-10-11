package array;
// LC 904
// 自能选两种水果
public class FruitIntoBask {
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        if (n <= 2)
            return n;
        int first = 0;
        int second = -1;
        int nextFirst = 0;
        int ans = 1;
        for (int i = 1; i < n; i++) {
            // 窗口行成
            if (second == -1 && fruits[first] != fruits[i]) {
                second = i;
                nextFirst = i;
            } else if (second != -1 && fruits[first] != fruits[i] && fruits[second] != fruits[i]) {
                ans = Math.max(ans, i - first);
                first = nextFirst;
                nextFirst = i;
                second = i;
            } else if (fruits[i] != fruits[i-1]) {
                nextFirst = i;
            }
        }
        return Math.max(ans, n - first);
    }

    public static void main(String[] args) {
        final FruitIntoBask fruitIntoBask = new FruitIntoBask();
        final int i = fruitIntoBask.totalFruit(new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4});
        System.out.println(i);
    }
}

