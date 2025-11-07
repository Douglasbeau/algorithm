package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 堆的实现，加上值更新操作，同时维持堆结构
// 堆排序
public class Heap {
    private final int[] nums;
    private int heapSize;
    private Map<Integer, Integer> numPos;
    public Heap(int capacity) {
        nums = new int[capacity];
        numPos = new HashMap<>();
    }
    public Heap(int[] nums) { //用于一次给出整个数组的情况
        this.nums = nums;
        heapSize = nums.length;
        numPos = new HashMap<>();
        for (int i=0; i< nums.length; i++) {
            numPos.put(nums[i], i);
        }
        int i = nums.length - 1;
        while (i >= 0) {
            heapify(i);
            i--;
        }
    }

    public void add(int val) {
        numPos.put(val, heapSize);
        nums[heapSize++] = val;
        heapInsert(heapSize - 1);
    }

    // 插入末尾，往上调整。记录num->index
    public void heapInsert(int idx) {
        int p;
        while((p = idx - 1 >> 1) >= 0 && nums[idx] > nums[p]) {
            swap(idx, p);
            numPos.put(nums[idx], idx);
            idx = p;
            numPos.put(nums[idx], idx);
        }
    }
    public void update(int v, int nv) {
        if (v == nv)
            return;
        int pos = numPos.getOrDefault(v, -1);
        if (pos == -1)
            return;
        nums[pos] = nv;
        numPos.put(nv, pos);
        numPos.remove(v);
        // 下面只会中一个分支
        heapify(pos);
        heapInsert(pos);
    }

    public int poll() {
        int head = nums[0];
        numPos.remove(head);
        swap(0, --heapSize);
        heapify(0);
        return head;
    }

    // 小的下沉
    void heapify(int i) {
        int left;
        while((left = i << 1 | 1) < heapSize) {
            int big;
            if (left + 1 < heapSize) {
                big = nums[left] > nums[left + 1] ? left : left + 1;
            } else {
                big = left;
            }
            // nums[i] big enough, no more need to sink
            if (nums[big] <= nums[i])
                break;
            swap(i, big);
            numPos.put(nums[i], i);
            i = big;
            numPos.put(nums[i], i);
        }
    }

    private void swap(int i, int j) {
        if (i != j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }

    // remove head to tail, and heapify
    public int[] sorted() {
        while(heapSize > 0) {
            swap(0, --heapSize);
            // root could be small
            heapify(0);
        }
        return nums;
    }

    public void print() {
        int i = 0;
        while(i < heapSize - 1){
            System.out.printf("%d,", nums[i++]);
        }
        System.out.println(nums[i]);
    }

    public static void main(String[] args) {
        final Heap ih = new Heap(9);
        // 逐个插入
        ih.add(2);
        ih.add(3);
        ih.add(4);
        ih.add(1);
        ih.add(5);
        ih.add(7);
        ih.add(9);
        ih.add(10);
        ih.add(11);
        System.out.println("before update");
        ih.print();
        ih.update(5, 8);
        System.out.println("after update");
        ih.print();
        System.out.println("sorted:");
        System.out.println(Arrays.toString(ih.sorted()));
        System.out.println("---");

        // 堆排序，1. 从下往上遍历heapify下沉，2.弹出并进行heapify
        int[] nums = new int[] {1, 2, 9, 7, 10, 20, 21, 11, 5, 6, 8};
        final Heap sh = new Heap(nums);
        sh.print();
        sh.update(8, 13);
        sh.print();
        System.out.println(Arrays.toString(sh.sorted()));
        System.out.println(Arrays.toString(sh.nums));
    }
}
