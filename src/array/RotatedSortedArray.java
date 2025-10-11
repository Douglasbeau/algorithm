package array;

import util.RandomArrayGenerator;

// LC 33
// 在搜索旋转数组中查找目标值
// 方法一分了两步：先找二分法最小值位置，再选一个区二分找值
// 方法二是参考题解，直接分情况讨论：
// 如果target在右区，mid在左区时右移，同区则mid大左移mid小右移
// 如果target在左区，mid在右区时左移，同区则同上
public class RotatedSortedArray {
    public int search1(int[] nums, int target) {
        int n = nums.length;
        if (n == 1)
            return nums[0] == target ? 0 : -1;
        int l = 0;
        int r = n-1;
        int endNum = nums[n-1];

        while(l < r) {
            int mid = l + (r - l)/2;
            if (nums[mid] == target)
                return mid;
            // target在右区
            if (target <= endNum) {
                if (nums[mid] <= endNum) {
                    if (nums[mid] > target)
                        r = mid - 1;
                    else
                        l = mid + 1;
                } else {
                    l = mid + 1;
                }
            }
            // target在左区
            else {
                if (nums[mid] > endNum) {
                    if (nums[mid] > target)
                        r = mid - 1;
                    else
                        l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return nums[l] == target ? l : -1;
    }
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 1)
            return target == nums[0] ? 0 : -1;
        // 查找最小值位置
        int minPos = -1;
        // 全正序
        if (nums[0] < nums[n-1]) {
            minPos = 0;
            return binarySearch(nums, 0, n-1, target);
        }
        // 两段正序
        int l = 0;
        int r = n - 1;
        while (l < r) {
            int mid = l + (r - l)/2;
            if ((mid+1==n || nums[mid] < nums[mid+1]) && nums[mid] < nums[mid-1]) {
                minPos = mid;
                break;
            }
            if (nums[mid] >= nums[0]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        minPos = minPos == -1 ? l : minPos;
        if (target == nums[minPos])
            return minPos;
        if (target < nums[minPos])
            return -1;
        if (target > nums[minPos-1])
            return -1;

        if (target > nums[n-1])
            return binarySearch(nums, 0, minPos-1, target);
        return binarySearch(nums, minPos+1, n-1, target);
    }
    int binarySearch(int[] nums, int l, int r, int target) {
        if (l > r)
            return -1;
        int s = l;
        int e = r;
        while(s < e) {
            int mid = s + (e-s)/2;
            if (nums[mid] == target)
                return mid;
            if (nums[mid] > target) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return nums[s] == target ? s : -1;
    }

    public static void main(String[] args) {
        final RotatedSortedArray rotatedSortedArray = new RotatedSortedArray();
        final int search = rotatedSortedArray.search1(new int[]{3, 4, 0, 2}, 2);

        System.out.println(search);
    }
}

