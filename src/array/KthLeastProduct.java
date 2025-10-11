package array;

import java.io.*;
import java.util.PriorityQueue;

// 两个有序数组的第k小乘积
// TODO 二分法？
public class KthLeastProduct {

    public long big(int[] factors, int[] x, long k) {
        PriorityQueue<long[]> q = new PriorityQueue<>(factors.length, (l1, l2) -> (int) (l2[0] - l1[0]));
        for (int i = 0; i < factors.length; i++) {
            int j = factors[i] < 0 ? 0 : x.length - 1;
            long biggest = (long) factors[i] * x[j];
            long indices = ((long) i << 32) | j;
            q.offer(new long[]{biggest, indices});
        }
        long ans = 0;
        while (k != 0) {
            long[] pi = q.poll();
            if (pi == null)
                break; // could not happen
            ans = pi[0];
            int i = (int) (pi[1] >> 32);
            int j = (int) pi[1];
            k--;
            if (k == 0) {
                break;
            }

            long[] nextPI = q.peek();
            if (factors[i] < 0) {
                while (j < x.length - 1) {
                    long product = (long) factors[i] * x[j + 1];
                    if (nextPI == null || product >= nextPI[0]) {
                        ans = product;
                        k--;
                        if (k == 0) break;
                        j++;
                    } else {
                        long indices = ((long) i << 32) | (j + 1);
                        q.offer(new long[]{product, indices});
                        break;
                    }
                }
            } else {
                while (j > 0) {
                    long product = (long) factors[i] * x[j - 1];
                    if (nextPI == null || product >= nextPI[0]) {
                        ans = product;
                        k--;
                        if (k == 0) break;
                        j--;
                    } else {
                        long indices = ((long) i << 32) | (j - 1);
                        q.offer(new long[]{product, indices});
                        break;
                    }
                }
            }
        }
        return ans;
    }

    public long small(int[] factors, int[] x, long k) {
        PriorityQueue<long[]> q = new PriorityQueue<>(factors.length, (l1, l2) -> (int) (l1[0] - l2[0]));
        for (int i = 0; i < factors.length; i++) {
            int j = factors[i] < 0 ? x.length - 1 : 0;
            long smallest = (long) factors[i] * x[j];
            long indices = ((long) i << 32) | j;
            q.offer(new long[]{smallest, indices});
        }
        long ans = 0;
        while (k != 0) {
            long[] pi = q.poll();
            if (pi == null)
                break; // could not happen
            ans = pi[0];
            int i = (int) (pi[1] >> 32);
            int j = (int) pi[1];
            k--;
            if (k == 0) {
                break;
            }

            long[] nextPI = q.peek();
            if (factors[i] < 0) {
                while (j > 0) {
                    long product = (long) factors[i] * x[j - 1];
                    if (nextPI == null || product <= nextPI[0]) {
                        ans = product;
                        k--;
                        if (k == 0) break;
                        j--;
                    } else {
                        long indices = ((long) i << 32) | (j - 1);
                        q.offer(new long[]{product, indices});
                        break;
                    }
                }
            } else {
                while (j < x.length - 1) {
                    long product = (long) factors[i] * x[j + 1];
                    if (nextPI == null || product <= nextPI[0]) {
                        ans = product;
                        k--;
                        if (k == 0) break;
                        j++;
                    } else {
                        long indices = ((long) i << 32) | (j + 1);
                        q.offer(new long[]{product, indices});
                        break;
                    }
                }
            }
        }
        return ans;
    }

    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        int n1Len = nums1.length;
        int n2Len = nums2.length;
        int[] factors, x; // 系数和自变量
        if (n1Len <= n2Len) {
            factors = nums1;
            x = nums2;
        } else {
            factors = nums2;
            x = nums1;
        }
//        System.out.printf("xlen:%d factorsLen:%d product num: %d\n", x.length, factors.length, n1Len*n2Len);

        boolean reverse = k > (long) n1Len * n2Len / 2;
        long ans = 0;
        if (reverse) {
            k = (long) n1Len * n2Len - k + 1;
            System.out.println("reversed k:" + k);
            ans = big(factors, x, k);
        } else {
            ans = small(factors, x, k);
        }

        return ans;
    }
    public static void main(String[] args) {
        KthLeastProduct kthLeastProduct = new KthLeastProduct();

        int[] nums1 = new int[]{-1, 2, 3, 4, 5};
        int[] nums2 = new int[]{5, 2, 4, 6, 8};
        long start = System.currentTimeMillis();

        long l = kthLeastProduct.kthSmallestProduct(nums1, nums2, 9);
        System.out.println("size1: " + nums1.length + " size2:" + nums2.length);
        System.out.println("time cost: " + (System.currentTimeMillis() - start) + " result: " + l);
    }
}