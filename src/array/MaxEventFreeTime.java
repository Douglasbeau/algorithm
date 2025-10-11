package array;

import java.util.Collections;

// LC 3440
public class MaxEventFreeTime {
    public int maxFreeTime(int eventTime, int[] startTime, int[] endTime) {
        int len = startTime.length;
        int[] nums = new int[2 * len + 1];//记录空、会时长
        int[] free = new int[len + 1]; // 记录空闲时长
        nums[0] = startTime[0];
        free[0] = startTime[0];
        for (int i=0; i < len - 1; i++) {
            free[i+1] = startTime[i+1] - endTime[i];
            nums[2*i+1] = endTime[i] - startTime[i];
            nums[2*i+2] = free[i+1];
        }
        free[len] = eventTime - endTime[len - 1];
        nums[2*len] = free[len];
        nums[2*len-1] = endTime[len-1] - startTime[len-1];
        //将free排序 从大到小 带位置
        long[] freePos = new long[free.length];
        for (int i=0; i<free.length; i++) {
            freePos[i] = ((long) free[i] << 32) | i;
        }
        moveForwardTop3(freePos);

        return Math.max(proc1(free), proc2(nums, freePos));
    }
    // 平移 不改变会议顺序，相邻两个元素和
    private int proc1(int[] free) {
        int ans = 0;
        for (int i=0; i<free.length-1; i++) {
            ans = Math.max(ans, free[i] + free[i+1]);
        }
        return ans;
    }
    // 搬运 改变会议顺序，相邻三个元素和最大，须有非相邻的空闲够大 >=中间元素
    private int proc2(int[] nums, long[] freePos) {
        int ans = 0;
        int n = nums.length;
        // 奇数i是会议时间，相邻空闲是i/2和i/2+1
        for (int i=1; i<n; i += 2) {
            // 寻找free大且不相邻的数
            int j = 0;
            while(nums[i] <= freePos[j] >> 32) {
                if ((int)freePos[j] == i/2 || (int)freePos[j] == i/2 + 1)
                    j++;//相邻 下一个 最多两次
                else
                    break; // 找到
            }
            if (nums[i] <= freePos[j]>>32)
                ans = Math.max(nums[i-1] + nums[i] + nums[i+1], ans);
        }
        return ans;
    }

    private void moveForwardTop3(long[] freePos) {
        int len = freePos.length;
        long first = partition(freePos, 0, len - 1, len - 1);
        long second = partition(freePos, 0, len - 1, len - 2);
        long third = partition(freePos, 0, len - 1, len - 3);
        freePos[0] = first;
        freePos[1] = second;
        freePos[2] = third;
    }
    private long partition(long[] nums, int l, int r, int idx) {
        int p = (int)(Math.random() * (r - l + 1) + l);
        long pivot = nums[p];
        int small = l - 1;
        int big = r + 1;
        int i = l;
        while(i < big) {
            if (nums[i] < pivot) {
                swap(nums, ++small, i++);
            } else if (nums[i] == pivot) {
                i++;
            } else {
                swap(nums, --big, i);
            }
        }
        if (small + 1 <= idx && big - 1 >= idx) {
            return nums[idx];
        }
        if (small + 1 > idx) {
            return partition(nums, l, small, idx);
        }
        return partition(nums, big, r, idx);
    }

    private void swap(long[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        long tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        final MaxEventFreeTime meft = new MaxEventFreeTime();
        int et = 10;
        int[] startTime = new int[] {0, 3, 7, 9};
        int[] endTime = new int[] {1, 4, 8, 10};
        final int i = meft.maxFreeTime(et, startTime, endTime);
        System.out.println(i);
    }
}
