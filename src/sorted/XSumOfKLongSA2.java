package sorted;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

// LC 3321
// 双有序set，边界条件太多！！
public class XSumOfKLongSA2 {
    public long[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        // 前x，词频低的、元素小的会先删除，其他词频高的、元素大的会先晋升
        TreeSet<int[]> xFreqNum = new TreeSet<>((a, b) -> a[0]==b[0]? a[1] - b[1]: a[0]-b[0]);
        TreeSet<int[]> oFreqNum = new TreeSet<>((a, b) -> a[0]==b[0]? b[1] - a[1]: b[0]-a[0]);
        // 方便查找set元素
        HashMap<Integer, int[]> map = new HashMap<>();
        int l = 0, r = 0;
        long[] sum = new long[n - k + 1];
        int si = 0;
        while(r < n) {
            int cur = nums[r];
            int[] element = map.computeIfAbsent(cur, v -> new int[]{0, v});
            int removed = 0;
            int added = 0;
            // right side, re-add: 1.remove existing
            if (element[0] != 0) {
                // only 1 set contains it
                if (xFreqNum.contains(element)) {
                    xFreqNum.remove(element);
                    removed = element[0] * element[1];
                }
                // xFreqNum must be already full
                else {
                    oFreqNum.remove(element);
                }
            }
            // 2. add back
            ++element[0];
            if (xFreqNum.size() < x) {
                xFreqNum.add(element);
                added = element[0] * element[1];
            } else {
                int[] min = xFreqNum.first();
                if (min[0] < element[0] || min[0] == element[0] && min[1] < element[1]) {
                    xFreqNum.remove(min);
                    xFreqNum.add(element);
                    oFreqNum.add(min);
                    removed = min[0] * min[1];
                    added = element[0] * element[1];
                } else {
                    oFreqNum.add(element);
                }
            }

            // window initialized
            if (r == k-1) {
                sum[si++] = sumup(xFreqNum);
            }
            // need left to move
            if (r >= k) {
                // left side, re-add
                int left = nums[l++];
                int[] evict = map.get(left);
                if (xFreqNum.contains(evict)) {
                    xFreqNum.remove(evict);
                    removed += evict[0] * evict[1];
                } else {
                    oFreqNum.remove(evict);
                }
                --evict[0];
                // add back
                if (xFreqNum.size() < x) {
                    if (!oFreqNum.isEmpty()) {
                        int[] max = oFreqNum.first();
                        if (max[0] > evict[0] || max[0] == evict[0] && max[1] > evict[1]) {
                            xFreqNum.add(max);
                            if(evict[0] > 0)
                                oFreqNum.add(evict);
                            oFreqNum.remove(max);
                            added += max[0] * max[1];
                        } else {
                            if (evict[0] > 0) {
                                xFreqNum.add(evict);
                                added += evict[0] * evict[1];
                            }
                        }
                    } else if (evict[0] > 0) {
                        xFreqNum.add(evict);
                        added += evict[0] * evict[1];
                    }
                } else {
                    if (evict[0] > 0)
                        oFreqNum.add(evict);
                }
                sum[si] = sum[si - 1] + added - removed;
                si++;
            }
            r++;
        }
        return sum;
    }

    long sumup(Set<int[]> xFreqNum) {
        long ans = 0;
        for (int[] xf : xFreqNum) {
            ans += (long)xf[0] * xf[1];
        }
        return ans;
    }

    public static void main(String[] args) {
        XSumOfKLongSA2 xs = new XSumOfKLongSA2();
        long[] x = xs.findXSum(new int[]{3,6,4,3,1,1,2}, 1, 1);
        System.out.println(Arrays.toString(x));
    }
}
