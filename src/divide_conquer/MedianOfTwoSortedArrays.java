package divide_conquer;

// 两个有序数组的中位数 hard, O(log(m+n))
// 2022.09.02
// 2024.07.29
 public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] longer = nums1.length >= nums2.length ? nums1 : nums2;
        int[] shorter = nums1.length >= nums2.length ? nums2 : nums1;
        int s = shorter.length;
        int l = longer.length;
        if (s == 0)
            return (l & 1) == 0 ? (longer[l/2] + longer[l/2 -1]) / 2.0 : longer[l/2];

        int k;
        //奇数个
        if ((s - l & 1) == 1) {
            k = (s + l + 1) / 2;
            return findKthLeast(longer, shorter,  k);
        }

        k = (s + l + 1) / 2;
        int a = findKthLeast(longer, shorter, k);
        int b = findKthLeast(longer, shorter, k+1);
        return (a + b) / 2.0;
    }

    // 仅限于 k ∈ (短数组长度，长数组长度]
    // 去掉了长数组不可能是中位数的元素
    int findKthLeast(int[] longer, int[] shorter, int k) {
        if (k <= shorter.length)
            return findUpMedian(shorter, 0, k - 1, longer, 0, k - 1);
        if (k > longer.length) {
            if (shorter[k-longer.length-1] >= longer[longer.length-1])
                return shorter[k-longer.length-1];
            if (longer[k-shorter.length-1] >= shorter[shorter.length-1])
                return longer[k-shorter.length-1];
            return findUpMedian(longer, k - shorter.length, longer.length-1, shorter, k-longer.length, shorter.length-1);
        }
        if (longer[k - shorter.length - 1] >= shorter[shorter.length - 1])
            return longer[k - shorter.length - 1];
        return findUpMedian(longer, k - shorter.length, k - 1, shorter, 0, shorter.length-1);
    }

    // 两个等长数组中位数
    int findUpMedian(int[] arr1, int s1, int e1, int[] arr2, int s2, int e2) {
        if (e1 == s1)
            return Math.min(arr1[s1], arr2[s2]);
        int m1 = 0;
        int m2 = 0;

        // 比较中间位置
        while (s1 < e1) {
            m1 = s1 + (e1 - s1) / 2;
            m2 = s2 + (e2 - s2) / 2;
            if (arr1[m1] == arr2[m2]) {
                return arr1[m1];
            }

            if (((e1 - s1 + 1) & 1) == 1) { // 奇数个，额外去掉一个
                if (arr1[m1] > arr2[m2]) {
                    if (arr2[m2] >= arr1[m1-1])
                        return arr2[m2];
                    e1 = m1 - 1;
                    s2 = m2 + 1;
                } else {
                    if (arr1[m1] >= arr2[m2-1])
                        return arr1[m1];
                    e2 = m2 - 1;
                    s1 = m1 + 1;
                }
            } else { // 偶数个
                if (arr1[m1] > arr2[m2]) {
                    e1 = m1;
                    s2 = m2 + 1;
                } else {
                    e2 = m2;
                    s1 = m1 + 1;
                }
            }
        }
        return Math.min(arr1[s1], arr2[s2]);
    }

    public static void main(String[] args) {
        final MedianOfTwoSortedArrays medianOfTwoSortedArrays = new MedianOfTwoSortedArrays();
        double upMedian = medianOfTwoSortedArrays.findMedianSortedArrays(new int[]{1, 2,2}, new int[]{3, 4});
        System.out.println(upMedian);
    }
}
