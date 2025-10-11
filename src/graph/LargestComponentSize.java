package graph;

import union_find.UnionFind;

import java.util.Arrays;
import java.util.Map;

// 该场景，用数组代替map
public class LargestComponentSize {
    private static class UnionFind<V> {
        static final int MAX_NUM = 100001;
        private final int[] size = new int[MAX_NUM];
        private final int[] parents = new int[MAX_NUM];
        private final int[] stack = new int[MAX_NUM];

        public int maxGroupSize() {
            int max = 0;
            for (Integer i : size) {
                max = Math.max(max, i);
            }
            return max;
        }

        public UnionFind(int[] values) {
            for (int v : values) {
                size[v] = 1;
                parents[v] = v;
            }
        }
        public boolean sameUnion(int a, int b) {
            return findParent(a) == findParent(b);
        }
        //
        private int findParent(int v) {
            int sp = -1;
            int p;
            while ((p = parents[v]) != v) {
                stack[++sp] = v;
                v = parents[p];
            }
            if (sp > -1)
                sp--;
            while (sp > -1) {
                parents[stack[sp--]] = p;
            }
            return p;
        }
        //
        public void union(int a, int b) {
            if (sameUnion(a, b)) {
                return;
            }

            int ap = findParent(a);
            int bp = findParent(b);
            final Integer aSize = size[ap];
            final Integer bSize = size[bp];

            int big = aSize >= bSize ? ap : bp;
            int small = aSize >= bSize ? bp : ap;
            parents[small] = big;
            size[big] = aSize + bSize;
            size[small] = 0;
        }

        public int size() {
            return size.length;
        }
    }

    public int largestComponentSize(int[] nums) {
        final int m = Arrays.stream(nums).max().getAsInt();
        int[] enlarged = new int[m+1];
        for (int i = 0; i <= m; i++) {
            enlarged[i] = i;
        }
        UnionFind<Integer> uf = new UnionFind<>(enlarged);

        // TODO 保持uf和nums一致
        // 预分解所有数字 生成因数集合
        for (int num: nums) {
            for (int j=2; j * j <= num; j++) {
                if (num % j == 0) {
                    uf.union(num, j);
                    uf.union(num, num / j);
                }
            }
        }
        int[] cs = new int[m+1];
        int ans = 0;
        for (int n : nums) {
            int root = uf.findParent(n);
            cs[root]++;
            ans = Math.max(cs[root], ans);
        }
        return ans;
    }

    int gcd(int a, int b) {
        if (a <= 0 || b <= 0)
            return -1;
        if (a == 1 || b == 1)
            return 1;
        if (a == b)
            return a;
        int d = Math.min(a, b);
        int dividend = Math.max(a, b);

        int r;
        while((r = dividend % d) > 1) {
            dividend = d;
            d = r;
        }
        return r == 0 ? d : 1;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {4,6,15,35};
        final LargestComponentSize lcs = new LargestComponentSize();
        final int i = lcs.largestComponentSize(nums);
        System.out.println(i);
        final int gcd = lcs.gcd(6, 3);
        System.out.println(gcd);
        System.out.println(lcs.gcd(4134,126));
    }
}
