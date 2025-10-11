package number;

public class NoZeroInt {
    public int[] getNoZeroIntegers(int n) {
        int a = 0;
        int base = 1;
        boolean flag = false;
        int remain = n;
        while(remain != 0) {
            int right = remain % 10 - (flag ? 1 : 0);
            if (right == -1) {
                right = 9;
                remain -= 1;
            }
            remain = remain / 10;
            if (right == 0 && remain == 0)
                break;

            if (remain == 0) {
                a += base;
                break;
            }

            if (right == 1) {
                a += base * 2;
                flag = true;
            } else {
                a += base;
                flag = right == 0;
            }
            base *= 10;
        }
        return new int[] {a, n - a};
    }

    public static void main(String[] args) {
        final NoZeroInt noZeroInt = new NoZeroInt();
        int[] a = noZeroInt.getNoZeroIntegers(10000);
        System.out.printf("%d, %d\n", a[0], a[1]);
    }
}
