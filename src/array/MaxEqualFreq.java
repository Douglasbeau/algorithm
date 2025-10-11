package array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MaxEqualFreq {
    public int maxEqualFreq(int[] nums) {
        // 1 2 3 3 2 1 3 4 5
        // 如何在统计时 对count有个全局了解
        // 1 1 2 3 3, 3 1 2 2 4,
        // 出现一次的：1
        // 两次的：1，一次的：2
        // 两次的：1和3，一次的：2
        // 两次的：1，一次的：2， 三次的：3

        // map 表示 occur -> nums set 如果里面有俩元素，key大小相差 1，且大key对应set大小=1
        Map<Integer, Set<Integer>> occurToNumberSet = new HashMap<>();
        Map<Integer, Integer> numToOccur = new HashMap<>();
        int maxOfOccur = 0;
        int result = 1;
        for (int i = 0; i < nums.length; i++) {
            int c = nums[i];
            int occur;

            if (numToOccur.containsKey(c)) {
                occur = numToOccur.get(c);
                occurToNumberSet.get(occur).remove(c);
                if (occurToNumberSet.get(occur).size() == 0)
                    occurToNumberSet.remove(occur);
            } else {
                occur = 0;
            }
            numToOccur.put(c, occur + 1);
            if (occurToNumberSet.containsKey(occur + 1)) {
                occurToNumberSet.get(occur + 1).add(c);
            } else {
                Set<Integer> newSet = new HashSet<>();
                newSet.add(c);
                occurToNumberSet.put(occur + 1, newSet);
            }
            maxOfOccur = Math.max(maxOfOccur, occur + 1);

            // 全都出现一次
            if (
                    (maxOfOccur == 1)
                            || // 有的出现n次，仅有一个数出现1次
                            (occurToNumberSet.size() == 2 &&
                                    occurToNumberSet.containsKey(maxOfOccur - 1) &&
                                    occurToNumberSet.get(maxOfOccur).size() == 1)
                            ||  // n-1有若干个， n 次仅有1个
                            (occurToNumberSet.size() == 2 &&
                                    occurToNumberSet.containsKey(1) &&
                                    occurToNumberSet.get(1).size() == 1)
                            || // 只有一个数出现 x次
                            (occurToNumberSet.size() == 1 && occurToNumberSet.get(maxOfOccur).size() == 1)
            ) {
                result = i + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        MaxEqualFreq maxEqualFreq = new MaxEqualFreq();
        int[] ints = {1, 3,3,3,3, 3};
        int[] ints2 = {3,3,3,3, 3};
        int[] ints3 = {1,1,3, 3,2};
        int[] ints4 = {1,1,2,3, 3,2};
        int prefix = maxEqualFreq.maxEqualFreq(ints4);
        System.out.println(prefix);
    }
}
