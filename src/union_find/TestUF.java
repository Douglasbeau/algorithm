package union_find;

import java.util.Arrays;

// 测试并查集
public class TestUF {
    public static void main(String[] args) {
        Integer[] nums = new Integer[] {1, 2, 3, 4, 5, 6};
//        int[] nums1 = new int[] {1, 2, 3, 4, 5, 6};

        final UnionFind<Integer> uf = new UnionFind<>(nums);

        boolean same = uf.sameUnion(1, 2);
        System.out.println(same);

        uf.union(1, 2);
        same = uf.sameUnion(1, 2);
        System.out.println(same);

        same = uf.sameUnion(4, 5);
        System.out.println(same);
        uf.union(4, 5);
        same = uf.sameUnion(4, 5);
        System.out.println(same);

        uf.union(4, 3);

        System.out.println(uf.sameUnion(5, 3));

        System.out.println("size: " + uf.size());
        System.out.println(uf.groupSize(1) + " " + uf.groupSize(4) + " " + uf.groupSize(6));

        System.out.println("=====");
        int a = gcd(16, 36);
        System.out.println(a);
    }
    private static int gcd(int a, int b) {
        if (a <= 0 || b <= 0)
            return -1;
        if (a == 1 || b == 1)
            return 1;
        if (a == b)
            return a;
        int d = Math.min(a, b);
        int dividend = Math.max(a, b);

        int r;
        while((r = dividend % d) != 0) {
            dividend = d;
            d = r;
        }
        return d;
    }
}
