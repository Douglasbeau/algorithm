package array;

import util.RandomArrayGenerator;

import java.util.Arrays;

//荷兰国旗问题
public class DutchNationalFlag {
    public static void main(String[] args) {
        int[] arr = RandomArrayGenerator.generate(10, 30);
        System.out.println(Arrays.toString(arr));

        System.out.println(Arrays.toString(partition(arr)));
        System.out.println(Arrays.toString(arr));
    }

    private static int[] partition(int[] arr) {
        int[] result = new int[2];
        if (arr == null || arr.length == 0) {
            return new int[]{-1};
        }
        if (arr.length == 1){
            return new int[] {0, 0};
        }
        int lb = -1;
        int rb = arr.length;
        int target = arr[arr.length-1];
        int i = 0;
        while (i<rb) {
            if (arr[i]==target){
                i++;
            } else if (arr[i]<target){
                swap(arr, i++, ++lb);
            } else { // arr[i]大则大的往左扩张
                swap(arr, i, --rb);
            }
        }

        result[0] = lb + 1;
        result[1] = rb - 1;
        return result;
    }

    private static void swap(int[] arr, int i, int r) {
        int tmp = arr[i];
        arr[i] = arr[r];
        arr[r] = tmp;
    }

}
