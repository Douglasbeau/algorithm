package array;

public class Removal {
    public int removeElement(int[] nums, int val) {
        int n = nums.length;
        int l = 0; // 剩余数字长度
        for (int i=0; i<n; i++) {
            if (nums[i] != val) {
                swap(nums, l++, i);
            }
        }
        return l;
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        final Removal removal = new Removal();
        System.out.println(removal.removeElement(new int[]{2,3,3,2}, 3));
    }
}
