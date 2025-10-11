package backtrace;

import java.util.ArrayList;
import java.util.List;

public class Game24 {
    public boolean judgePoint24(int[] cards) {
        double[] doubles = new double[4];
        for (int i = 0; i < 4; i++)
            doubles[i] = (double)cards[i];
        List<double[]> permutations = permute(doubles, 0);
        for (double[] nums : permutations) {
            if (process(nums))
                return true;
        }
        return false;
    }

    public boolean process(double[] nums) {
        int n = nums.length;
        if (n == 2) {
            return nums[0] + nums[1] == 24.0 ||
                    nums[0] - nums[1] == 24.0 ||
                    nums[0] * nums[1] == 24.0 ||
                    nums[0] / nums[1] == 24.0;
        }
        for (int i = 0; i < n - 1; i++) {
            double merge = nums[i] + nums[i+1];
            double[] reduced = reduce(nums, i, merge);
            if (process(reduced))
                return true;
            merge = nums[i] - nums[i+1];
            reduced = reduce(nums, i, merge);
            if (process(reduced))
                return true;
            merge = nums[i] * nums[i+1];
            reduced = reduce(nums, i, merge);
            if (process(reduced))
                return true;
            merge = nums[i] / nums[i+1];
            reduced = reduce(nums, i, merge);
            if (process(reduced))
                return true;
        }
        return false;
    }

    double[] reduce(double[] nums, int start, double replace) {
        int n = nums.length;
        double[] result = new double[n-1];
        result[start] = replace;
        System.arraycopy(nums, 0, result, 0, start);
        if (n - (start + 2) > 0)
            System.arraycopy(nums, start + 2, result, start + 1, n - (start + 2));
        return result;
    }

    List<double[]> permute(double[] doubles, int start) {
        if (start == doubles.length) {
            double[] copy = new double[start];
            System.arraycopy(doubles, 0, copy, 0, start);
            List<double[]> result = new ArrayList<>(1);
            result.add(copy);
            return result;
        }
        List<double[]> result = new ArrayList<>();
        for (int i = start; i < doubles.length; i++) {
            if (start != i && doubles[i] == doubles[start])
                continue;
            swap(doubles, start, i);
            List<double[]> items = permute(doubles, start+1);
            swap(doubles, start, i);
            result.addAll(items);
        }
        return result;
    }

    void swap(double[] arr, int i, int j) {
        if (i == j)
            return;
        double tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}

