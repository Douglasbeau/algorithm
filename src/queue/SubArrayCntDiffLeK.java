package queue;

import java.util.LinkedList;

// 微软 满足max-min <= k的子数组的数量
// 以i开头，刚好不满足<=k的时候，以i开头的每个子数组都满足
// 此时i++
public class SubArrayCntDiffLeK {
    // 找以i开头最大窗口
    public int maxNumOfSubArr(int[] arr, int k) {
        LinkedList<Integer> small = new LinkedList<>();
        LinkedList<Integer> big = new LinkedList<>();
        int l = 0;
        int r = 0;
        int ans = 0;
        while (l < arr.length) {
            while (r < arr.length) { // 可以右移
                while (!big.isEmpty() && arr[big.peekLast()] <= arr[r]) {
                    big.removeLast();
                }
                while(!small.isEmpty() && arr[small.peekLast()] >= arr[r]) {
                    small.removeLast();
                }
                big.add(r);
                small.add(r);
                if (arr[big.peekFirst()] - arr[small.peekFirst()] > k) {
                    break;
                }
                r++;
            }
            // 统计
            ans += r - l;

            // 更新最大最小值
            if (!small.isEmpty() && l == small. peekFirst())
                small.removeFirst();
            if (!big.isEmpty() && l == big.peekFirst())
                big.removeFirst();
            l++;
        }
        return ans;
    }
    public static void main(String[] args) {
        final SubArrayCntDiffLeK sacdlk = new SubArrayCntDiffLeK();
        final int i = sacdlk.maxNumOfSubArr(new int[]{9, 3, 8, 6}, 5);
        System.out.println(i);
    }
}
