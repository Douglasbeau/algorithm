package number;

import java.util.ArrayList;
import java.util.List;

// LC 1018 可以被5整除的0...i的二进制数，左为高位
public class DividableBy5 {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        int cur = 0;
        List<Boolean> list = new ArrayList<>(nums.length);
        for (int num : nums) {
            cur = cur << 1 | num;
            cur %= 5; // 关键！
            list.add(cur == 0);
        }
        return list;
    }

    public static void main(String[] args) {
        DividableBy5 dividableBy5 = new DividableBy5();
        System.out.println(dividableBy5.prefixesDivBy5(new int[]{1, 0, 1, 0}));
    }
}
