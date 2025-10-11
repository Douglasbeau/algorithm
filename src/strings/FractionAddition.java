package strings;

import java.util.HashMap;
import java.util.Map;

// 将字符串中的分数相加，得到分数
public class FractionAddition {
    public String fractionAddition(String expression) {
        Map<Integer, Boolean> fractionPositive = new HashMap<>();
        // 放 分母分子的拼接
        int[] combinations = new int[154];
        // 当前数是正还是负
        boolean positive = expression.charAt(0) != '-';
        int start = positive ? 0 : 1;
        for(int i=start; i<expression.length()-1; i+=3) {
            int up = expression.charAt(i);
            int down = expression.charAt(i+2);
            int key = (down << 4) | up;
            // 存在 且跟当前同号
            if (fractionPositive.get(key) == positive) {
                combinations[key]++;
            } else if (combinations[key] == 0) { // 不存在则 记录
                combinations[key] = 1;
                fractionPositive.put(key, positive);
            } else { // 存在且不同号 减掉
                if (combinations[key] == 1) { //减没了
                    combinations[key] = 0;
                    fractionPositive.remove(key);
                } else {
                    combinations[key]--;
                }
            }
            if (i + 3 < expression.length())
                positive = expression.charAt(i+3) == '+';
        }
        int i = 1;
        while (combinations[i] == 0) {
            i++;
        }
        int curUp = (i & 0xF) * combinations[i]; // 分子
        int curDown = i >> 4;
        boolean curPosit = fractionPositive.get(i); // 分母

        int nextUp;
        int nextDown;
        boolean nextPosit;
        int cnt;
        // 取出下一个数，分母相同则分子相+/-
        for (; i<combinations.length; i++) {
            cnt = combinations[i];
            if (cnt == 0)
                continue;
            fractionPositive.get(i);
            nextUp = i & 0xF;
            nextDown = i >> 4;

        }
        return null;
    }

}
