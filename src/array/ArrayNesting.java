package array;

import java.util.HashSet;
import java.util.Set;

public class ArrayNesting {
    public int arrayNesting(int[] nums) {
        Set<Integer> done = new HashSet<Integer>();
        int result = 0;
        int numGoneThrough = 0;
        int counter = 0;
        for (int i=0; i<nums.length; i++) {
            // 已经遍历了
            if (done.contains(i)) {
                continue;
            }
            counter = 0;

            int cur = i;
            // 遍历并计数
            while(!done.contains(cur)) {
                done.add(cur);
                cur = nums[cur];
                counter++;
            }
            result = Math.max(counter, result);
            // 当剩余的元素不足以产生比 当前S更大时
            numGoneThrough += counter;
            if(nums.length - numGoneThrough <= result) {
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ArrayNesting submission = new ArrayNesting();
        int[] arr = new int[] {0,2,1};
        int m = submission.arrayNesting(arr);
        System.out.println(m);
    }
}
