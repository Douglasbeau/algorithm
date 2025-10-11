package array;

import java.util.Arrays;

// TODO 没考虑到后面区间比前面短的情况，该贪心不行，得改成 右端点升序排列
// 给定若干区间，每个长度都不小于2，输出S集合最小长度，使得S与每个区间都至少两个相交元素
public class SetIntersectionAtLeastTwo {
    public int intersectionSizeTwo(int[][] intervals) {
        int len = intervals.length;
        Pair[] pairs = new Pair[len];
        for (int i=0; i<len; i++) {
            pairs[i] = new Pair(intervals[i][0], intervals[i][1]);
        }
        Arrays.sort(pairs);
        int top1 = -1;
        int top2 = -1;
        int result = 0;
        for(int i=0; i<len; i++) {
            // 判断区间跟现有 top 的交集元素数
            int curLeft = pairs[i].left;
            int curRight = pairs[i].right;
            // 0
            if (top1 < curLeft) {
                top1 = curRight;
                top2 = curRight - 1;
                result += 2;
                continue;
            }
            // 1 此处top1在区间中，下面判断top2不在其中
            if (top2 < curLeft) {
//                top2 = top1;
//                top1 = curRight;
                result++;
                if (top1 == curRight) {
                    top2 = curRight - 1;
                } else {
                    top2 = top1;
                    top1 = curRight;
                }
            }
            // 2或以上
            System.out.println("no need to update ");
        }
        return result;
    }

    public static void main(String[] args) {
        SetIntersectionAtLeastTwo s = new SetIntersectionAtLeastTwo();
        int[][] intervals = new int[][] {{2,10},{3,7},{3,15},{4,11},{6,12},{6,16},{7,8},{7,11},{7,15},{11,12}};
        int[][] intervals2 = new int[][] {{1,2}};
        int min = s.intersectionSizeTwo(intervals);
        System.out.println(min);
    }
    private static class Pair implements Comparable<Pair>{
        int left;
        int right;
        Pair(int l, int r) {
            this.left = l;
            this.right = r;
        }

        @Override
        public int compareTo(Pair o) {
            return right - o.right;
        }
    }
}
