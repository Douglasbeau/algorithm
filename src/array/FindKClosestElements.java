package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 给一个数组arr和 整数k、x，返回数组中跟x大小最接近的k个数
public class FindKClosestElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // like merge sort, we firstly find the border that splits the arr into two
        int first = findTheLeftPivotPosition(arr, k, x);
        System.out.println(first);
        // first pos <-, first pos + 1 ->
        return findKClosest(arr, k, x, first);
    }

    private List<Integer> findKClosest(int[] arr, int k, int x, int first) {
        int left = first;
        int right = first + 1;
        int cnt = 0;
        // find the left border
        while(cnt < k && left >= 0 && right < arr.length) {
            if(arr[left] == x)
                left--;
            else if (x - arr[left] <= arr[right] - x )
                left--;
            else
                right++;
            cnt++;
        }
        System.out.println(left + " is the left of result");
        System.out.println("k = " + k);
        System.out.println("cnt = " + cnt);
        List<Integer> result = new ArrayList<>(k);

        if (right == arr.length) {
            while (cnt != k) {
                cnt++;
                left--;
            }
        } else {
            System.out.println("left " + left);
        }

        System.out.println("left = " + left);
        while (k-- != 0)
            result.add(arr[++left]);

        return result;
    }

    private int findTheLeftPivotPosition(int[] arr, int k, int x) {
        if (x <= arr[0])
            return 0;
        if (x >= arr[arr.length - 1])
            return arr.length - 1;
        // binary search the equal or less num's position
        return binarySearch(arr, 0, arr.length-1, x);
    }
    private int binarySearch(int[] arr, int l, int r, int x) {
        // l r碰撞
        if (l == r)
            return arr[l] <= x ? l : l - 1;
        // x不在arr的 min max范围内了
        if (arr[l] > x)
            return l - 1;
        if (arr[r] < x)
            return r;
        // 中轴
        int pivot = (l + r)/2;
        if (arr[pivot] == x)
            return pivot;
        if (arr[pivot] < x)
            return binarySearch(arr, pivot + 1 ,r, x);
        return binarySearch(arr, l, pivot - 1, x);
    }

    public static void main(String[] args) {
        FindKClosestElements fk = new FindKClosestElements();
        int[] arr = new int[] {0,0,0,1, 3, 5, 6, 6,6,7};
        int[] arr1 = new int[] {0,2,2,3,4,6,7,8,9,9};
//        int i = fk.findTheLeftPivotPosition(arr, 3, 5);
//        System.out.println(i);
//        i = fk.findTheLeftPivotPosition(arr, 3, -1);

        System.out.println(fk.findClosestElements(arr1, 4, 4));
        System.out.println(Arrays.binarySearch(arr, 4));

    }
}
