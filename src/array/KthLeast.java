package array;

import util.RandomArrayGenerator;

import java.util.Arrays;

// 第k小的数是多少
public class KthLeast {
    public int kthLeast(int[] arr, int k) {
        if (k > arr.length || k < 1)
            return -1;
        int l = 0, r = arr.length - 1;
        int index = -1;

        // 荷兰国旗，返回
        while(index == -1) {
            int rand = (int)(Math.random() * (r - l + 1) + l);
            int[] pos = partition(arr, rand, l, r);
            if (pos[0] <= k - 1 && k - 1 <= pos[1]) { // k-1在等于区
                index = pos[0];
            } else if (pos[0] > k - 1) {
                r = pos[0] - 1;
            } else {
                l = pos[1] + 1;
            }
        }
        return arr[index];
    }

    // 在arr[L, R]区域将小于、等于、大于pivot的数分区 返回=区边界
    int[] partition(int[] arr, int pi, int L, int R) {
        if (L == R)
            return new int[] {L, L};
        int pivot = arr[pi];
        int i = L;
        int l = L-1;
        int g = R+1;
        // 2, [1 2 1 4 3]
        while(i < g) {
            if (arr[i] < pivot) {
                swap(arr, i++, ++l);
            } else if (arr[i] > pivot) {
                swap(arr, i, --g);
            } else {
                i++;
            }
        }
        return new int[] {l+1, g-1};
    }

    void swap(int[] arr, int i, int j) {
        if (i == j)
            return;
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 2000; i++) {
            int len = 20;
            int k = (int)(Math.random() * len) + 1;
            System.out.printf("round:%d k: %d\n", i+1, k);
            final KthLeast kthLeast = new KthLeast();
            final int[] generate = RandomArrayGenerator.generate(20, 50);
            System.out.println(Arrays.toString(generate));
            final int ans = kthLeast.kthLeast(generate, k);
            Arrays.sort(generate);
            assert ans == generate[k-1];
        }
    }
}
