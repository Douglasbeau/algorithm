package strings;

import java.util.ArrayList;
import java.util.List;

// n个k镜像数字之和，k镜像数字：k进制及其对应十进制数都是回文
class KMirror {
    List<Integer> digits = new ArrayList<>();
    public long kMirror(int k, int n) {
        long sum = 0;

        long part = 1; // 半边数
        long limit = 10; // 在b变成偶数时更新，回到奇数才使用
        long full;

        while (n != 0) {
            for (int op = 1; op <= 2; op++) { // 1 奇数 2 偶数
                // 根据part和b构造full
                for (long num = part; num < limit; num++) {
                    long remain = op % 2 == 1 ? num / 10 : num;
                    full = num;
                    while (remain != 0) {
                        full = full * 10 + remain % 10;
                        remain /= 10;
                    }
                    if (isKMirror(full, k)) {
                        sum += full;
                        n--;
                        if (n == 0) {
                            return sum;
                        }
                    }
                }
            }
            part = limit;
            limit *= 10;
        }
        return sum;
    }

    // 转为k进制，看看是否回文
    private boolean isKMirror(long l, int k) {
        boolean mirror = true;
        long tmp = l;
        while (tmp != 0) {
            digits.add((int)(tmp % k));
            tmp /= k;
        }

        int left = 0, right = digits.size() - 1;
        while(left < right) {
            if (!digits.get(left++).equals(digits.get(right--))) {
                mirror = false;
                break;
            }
        }
        digits.clear();
        return mirror;
    }

    public static void main(String[] args) {
        KMirror kMirror = new KMirror();
        System.out.println(kMirror.kMirror(7, 17));
    }
}