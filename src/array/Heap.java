package array;

import java.util.Arrays;

// 堆的实现
// 堆排序
public class Heap {
    private final int[] nums;
    private int heapSize;
    public Heap(int capacity) {
        nums = new int[capacity];
    }
    public Heap(int[] nums) { //用于一次给出整个数组的情况
        this.nums = nums;
        heapSize = nums.length;
        int i = nums.length - 1;
        while (i >= 0) {
            heapify(i);
            i--;
        }
    }

    // 插入末尾，往上调整
    public void heapInsert(int num) {
        nums[heapSize++] = num;

        int index = heapSize - 1;
        while(nums[index] > nums[(index - 1)/2]) {
            swap(index, (index - 1)/2);
            index = (index-1)/2;
        }
    }

    public int poll() {
        int head = nums[0];
        swap(0, --heapSize);
        heapify(0);
        return head;
    }

    // 下沉
    void heapify(int i) {
        while(2*i + 1 < heapSize) {
            int left = i*2 + 1;
            int right = left + 1;
            int big;
            if (right < heapSize) {
                big = nums[left] > nums[right] ? left : right;
            } else {
                big = left;
            }
            if (nums[big] <= nums[i])
                break;
            swap(i, big);
            i = big;
        }
    }

    private void swap(int i, int j) {
        if (i != j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }

    public void print() {
        int i = 0;
        while(i < heapSize - 1){
            System.out.printf("%d,", nums[i++]);
        }
        System.out.println(nums[i]);
    }

    public int[] getNums() {
        return nums;
    }


    public static void main(String[] args) {
        final Heap heap = new Heap(6);
        // 逐个插入
        heap.heapInsert(2);
        heap.heapInsert(3);
        heap.heapInsert(4);
        heap.heapInsert(1);
        heap.heapInsert(5);
        heap.heapInsert(7);
        heap.print();

        // 堆排序，1. 从下往上遍历heapify下沉，2.弹出并进行heapify
        int[] nums = new int[] {1, 2, 3, 5, 6, 8};
        final Heap sort = new Heap(nums);
        sort.print();
        while (sort.heapSize > 0) {
            System.out.println(sort.poll());;
        }
        System.out.println(Arrays.toString(sort.nums));
    }
}
