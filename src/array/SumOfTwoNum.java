package array;

import util.RandomArrayGenerator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

// 给一个数组nums[]和一个目标值target，返回数组中两数的下标（不能相同），使两数和为target
// 复杂度怎么才能小于O(n^2)
// [3 2 4 5 0 1 7]， 找出10 -> [0 6]
// Mine: 可以先排序，为了记录原始顺序，封装（数+下标），二分查找
// Others: 可以使用HashMap ： 循环将num->index映射放到Map，如果target-num存在，那就直接返回
public class SumOfTwoNum {
    private static class NumIndex{
        int num;
        int index;
        NumIndex(int num, int index) {
            this.num = num;
            this.index = index;
        }
    }
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        // wrap
        NumIndex[] numIndices = new NumIndex[nums.length];
        for (int i=0; i<nums.length; i++) {
            numIndices[i] = new NumIndex(nums[i], i);
        }
        // sort
        Arrays.sort(numIndices, Comparator.comparingInt(a -> a.num));
        // binary search
        for (NumIndex numIndex : numIndices) {
            int first = numIndex.index;
            int toFind = target - numIndex.num;

            // 避免first == second，需要在search里做

            int second = binarySearch(numIndices, 0, nums.length -1, toFind, first);

            if (second == -1) {
                continue;
            }
            result[0] = first;
            result[1] = second;
            return result;
        }
        return result;
    }

    private int binarySearch(NumIndex[] numIndices, int left, int right, int dest, int avoid) {
        int p = (left + right)/2;
        int num = numIndices[p].num;
        if (left == right && dest != num) {
            return -1; // 没找到
        }

        if (num == dest) {
            if (numIndices[p].index != avoid)
                return numIndices[p].index;
            // hit avoid
            if (avoid<numIndices.length-1 && numIndices[avoid+1] == numIndices[avoid])
                return numIndices[avoid+1].index;
            if (avoid>0 && numIndices[avoid-1] == numIndices[avoid])
                return numIndices[avoid-1].index;
            return -1;
        }

        // 右边区
        if (num < dest) {
            return binarySearch(numIndices, p+1, right, dest, avoid);
        }
        // 左边区
        return binarySearch(numIndices, left, p, dest, avoid);
    }
    public int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>(nums.length, 1);
        int[] result = new int[2];
        for(int i=0; i< nums.length; i++){
            int diff = target - nums[i];
            if (map.containsKey(diff)) {
                result[0] = i;
                result[1] = map.get(diff);
                return result;
            }
            map.put(nums[i], i);
        }
        return result;
    }

        public static void main(String[] args) {
        int[] generate = RandomArrayGenerator.generate(6, 31);
        System.out.println(Arrays.toString(generate));
        int sum = generate[3] + generate[5];
        System.out.printf("need to find %d\n", sum);

        SumOfTwoNum sumOfTwoNum = new SumOfTwoNum();
        int[] twoSum = sumOfTwoNum.twoSum(generate, sum);
        System.out.println(Arrays.toString(twoSum));
    }
}
