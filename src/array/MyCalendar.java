package array;

import java.util.*;

// 往calendar里面加 (start, end)数组，返回是否可以成功加入
public class MyCalendar {
    // TODO 大佬的解法，值得借鉴——重新定义相等 - 有冲突
//    TreeSet<int[]> calendars;
//
//    public MyCalendar() {
//        calendars = new TreeSet<>((a, b) -> {
//            if(a[1] <= b[0])
//                return -1;
//            else if(a[0] >= b[1])
//                return 1;
//            else
//                return 0;
//        });
//    }
//
//    public boolean book(int start, int end) {
//        int[] e = new int[]{start, end};
//        return calendars.add(e);
//    }
//    Set<Integer> booked;
    Map<Integer, Pair> startToPair;
    private static class Pair implements Comparable<Pair>{
        int s;
        int e;

        public Pair(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Pair pair) {
            return this.s - pair.s;
        }
    }
    public MyCalendar(){
//        booked = new TreeSet<>();
        startToPair = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        Pair toAdd;
        // 已存在了
        if (startToPair.containsKey(start)) {
            return false;
        }
        for (Pair toIter : startToPair.values()) {
            // 没冲突就继续，否则表明有冲突
            if ((toIter.s > start || toIter.e <= start) && (start > toIter.s || end <= toIter.s)) {
                // 遍历到了更大的数 则不需要继续了，肯定没有冲突
                if (start < toIter.s) {
                    break;
                }
            } else {
                return false;
            }
        }
        // 遍历完也没找到
        toAdd = new Pair(start, end);
        startToPair.put(start, toAdd);
        return true;
    }

    private boolean noConflict(int[] curr, int[] toAdd) {
//        return (curr[0] > toAdd[0] || curr[1] <= toAdd[0]) && (toAdd[0] > curr[0] || toAdd[1] <= curr[0]);
        // 参考别人，简化判断!
        return curr[0] >= toAdd[1] || curr[1] <= toAdd[0];
    }

    public static void main(String[] args) {
        MyCalendar myCalendar = new MyCalendar();
        boolean b = myCalendar.noConflict(new int[]{10, 20}, new int[]{15, 24});
        System.out.println(b);

        boolean book = myCalendar.book(10, 20);  // true
        boolean book1 = myCalendar.book(15, 25); // false
        boolean book2 = myCalendar.book(20, 30); // true
        boolean book3 = myCalendar.book(51, 52); // true
        boolean book4 = myCalendar.book(50, 54); // false
        boolean book5 = myCalendar.book(1, 4); // true
        boolean book6 = myCalendar.book(15, 16); // false
        System.out.println(Arrays.toString(new boolean[]{book, book1, book2, book3, book4, book5, book6}));
        System.out.println((232343 >> 8) & 0xFF);
        System.out.println(232343 >> 8);
    }
}
