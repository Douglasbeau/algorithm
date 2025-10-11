package queue;

import java.util.Arrays;

// 给一个int型数组和一个数字L分别作为直线上的点坐标和一根绳子的长度，判断最多cover的点，端点cover就算数
// 关键是双指针右移，外层while true循环，里面判断该移动left还是right，结束条件是cover最右了/到最右了但是没cover住/没到最右
public class MaxCoverDots {
    public static void main(String[] args) {
        int[] arr = {0,1, 5, 7,8, 10};
        int L = 5;
        System.out.println(solution(arr, L));
    }

    private static int solution(int[] arr, int L) {
        Arrays.sort(arr);
        int result = 1;
        if (L <= 0) {
            return 0;
        }
        // 让绳子从左向右爬行，能cover就继续右爬，否则收左边
        int left = 0;
        int right = 0;
        while(true) {
            while (right < arr.length - 1 && arr[right] - arr[left] <= L) {
                right++;
            }
            System.out.printf("左面爬到%d %d 右边在%d %d\n", left, arr[left], right, arr[right]);
            // 判断是右端到了还是因为cover不住了
            if (right == arr.length - 1 && arr[right] - arr[left] <= L){
                result = Math.max(right - left+1, result);//右到且能cover
                break;
            } else if (right == arr.length - 1){
                result = Math.max(result, right - left);// 右到且不能cover
                break;
            } else {
                result = Math.max(result, right - left);// 右边还没到，那动左边
            }
            left++;
        }
        return result;
    }
}
