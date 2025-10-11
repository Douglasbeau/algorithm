package array;

import java.util.Arrays;

// ^^^摆动排序
public class WiggleSort {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int[] result = Arrays.copyOf(nums, nums.length);
        int j = 0;
        // 小的数
        for (int i=0; i< nums.length; i+=2) {
            nums[i] = result[j++];
        }
        // 大的数
        for (int i=1; i< nums.length; i+=2) {
            nums[i] = result[j++];
        }
    }
/*    public void wiggleSort(int[] nums) {
        int n =nums.length;
        int[] tem = new int[n];
        System.arraycopy(nums,0,tem,0,n);
        Arrays.sort(tem);
        int j=n-1;
        // 数组奇数位置（从零开始）放置大数
        for(int i=1;i<n;i+=2){
            nums[i]=tem[j];
            j--;
        }
        // 数组偶数位置 放置小的数
        for(int i=0;i<n;i+=2){
            nums[i]=tem[j];
            j--;
        }
    }*/

    public static void main(String[] args) {
        WiggleSort ws = new WiggleSort();
        int[] nums = new int[] {1,1,2,2,3,3};
        ws.wiggleSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
