package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class RankTransformOfAnArray {
    public int[] arrayRankTransform0(int[] arr) {
        // 将arr放入小根堆，逐个弹出，map记录序号
        PriorityQueue<Integer> integers = new PriorityQueue<>();
        Map<Integer, Integer> numToRank = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            integers.add(arr[i]);
            numToRank.putIfAbsent(arr[i], 0);
        }
        // 弹出堆，给map赋值
        int rank = 1;
        int cur;
        while (!integers.isEmpty()) {
            cur = integers.poll();
            if (numToRank.get(cur) != 0)
                continue;
            numToRank.put(cur, rank++);
        }
        // 遍历数组，放rank
        for (int i = 0; i < arr.length; i++) {
            arr[i] = numToRank.get(arr[i]);
        }

        return arr;
    }
    public int[] arrayRankTransform(int[] arr) {
        int[] copy = Arrays.copyOf(arr, arr.length);
        //直接排序
        Arrays.sort(copy);
        // 逐个rank++
        HashMap<Integer, Integer> ranks = new HashMap<>();
        int cur;
        int pre = Integer.MIN_VALUE;
        int rank = 1;
        for (int i=0; i<arr.length; i++) {
            cur = copy[i];
            if (pre >= cur)
                continue;
            ranks.put(cur, rank++);
            pre = cur;
        }
        // rank逐个取出
        for (int i=0; i< arr.length; i++) {
            arr[i] = ranks.get(arr[i]);
        }
        return arr;
    }

    public static void main(String[] args) {
        RankTransformOfAnArray rt = new RankTransformOfAnArray();
        int[] arr = new int[] {5,10,3,2,2,1,4};
        int[] ranks = rt.arrayRankTransform(arr);
        System.out.println(Arrays.toString(ranks));
    }
}

