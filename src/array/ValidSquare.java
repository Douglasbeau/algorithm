package array;
// 给四个点，判断是否构成正方形
public class ValidSquare {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        // 找出垂直的线段， p2、p3、p4轮流作为 p1的伙伴，需要垂直
        return combineInOrder(p1, p2, p3, p4) ||
                combineInOrder(p1, p3, p2, p4) ||
                combineInOrder(p1, p4, p3, p2);
    }

    boolean combineInOrder(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[] seg1 = new int[2];
        int[] seg2 = new int[2];
        seg1[0] = p1[0] - p2[0];
        seg1[1] = p1[1] - p2[1];
        seg2[0] = p3[0] - p4[0];
        seg2[1] = p3[1] - p4[1];

        // 向量垂直
        if (seg1[0] * seg2[0] + seg1[1] * seg2[1] == 0) {
            // 向量长度不能为零
            if ((seg1[0] == 0 && seg1[1] == 0) || (seg2[0] == 0 && seg2[1] == 0)) {
                return false;
            }
            int len12 = seg1[0] * seg1[0] + seg1[1] * seg1[1];
            // 保证 是对角线
            int[] va = new int[2];
            va[0] = p1[0] - p3[0];
            va[1] = p1[1] - p3[1];
            int len13 = va[0] * va[0] + va[1] * va[1];
            if (len12 != len13 << 1)
                return false;

            // 乘积是0前提下，长度相等
            return len12 == seg2[0] * seg2[0] + seg2[1] * seg2[1];
        }
        return false;
    }

    public static void main(String[] args) {
        ValidSquare validSquare = new ValidSquare();
        int[] a = new int[] {1,0};
        int[] b = new int[] {0,0};
        int[] c = new int[] {2,0};
        int[] d = new int[] {2,1};
        int[] a2 = new int[] {1,0};
        int[] b2 = new int[] {0,0};
        int[] c2 = new int[] {0,1};
        int[] d2 = new int[] {1,1};
        int[] a3 = new int[] {2,1};
        int[] b3 = new int[] {0,0};
        int[] c3 = new int[] {2,0};
        int[] d3 = new int[] {1,2};
        boolean valid = validSquare.validSquare(a3, b3, c3, d3);
        System.out.println(valid);
    }
}
