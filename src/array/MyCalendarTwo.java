package array;

import java.util.ArrayList;
import java.util.List;

// 已存在俩就失败，存在一个的话仍成功book
public class MyCalendarTwo {
    // 一次booked
    List<int[]> single = new ArrayList<>();
    List<int[]> doubled = new ArrayList<>();
    // 两次booked
    public MyCalendarTwo() {

    }

    public boolean book(int start, int end) {
        for (int[] cur : doubled) {
            if (end > cur[0] && start < cur[1]) {
                return false;
            }
        }
        // 不双相交
        for(int[] cur: single) {
            // 相交
            if (end > cur[0] && start < cur[1]) {
                doubled.add(new int[]{Math.max(cur[0], start), Math.min(cur[1],end)});
            }
        }
        single.add(new int[]{start, end});
        return true;
    }
}