package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// return 数组里最小绝对差 对应的元素对
public class MinAbsDifference {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> result = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (int i=0; i<arr.length-1; i++) {
            int delta = arr[i+1] - arr[i];
            if (delta > min) {
                continue;
            }
            if (delta < min) {
                result.clear();
                min = delta;
            }
            List<Integer> pair = new ArrayList<>(2);
            pair.add(arr[i]);
            pair.add(arr[i+1]);
            result.add(pair);
        }

        return result;
    }

    public static void main(String[] args) {
        MinAbsDifference minAbsDifference = new MinAbsDifference();
        List<List<Integer>> lists = minAbsDifference.minimumAbsDifference(new int[]{5, 2, 1});
        System.out.println(lists);
    }
}
