package array;

import java.util.*;

public class AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        LinkedList<Integer> stack = new LinkedList<>();
        for (int p : asteroids) {
            // 1. 正数，压栈
            if (p > 0) {
                stack.push(p);
                continue;
            }
            // 2. 负数，栈空或者栈顶 < 0 则直接压入；
            if (stack.isEmpty() || stack.peek() < 0) {
                stack.push(p);
                continue;
            }
            // 循环比较，栈顶绝对值大 则下一个，小则弹出且继续peek，相等则弹出
            int top = stack.peek();
            boolean flag = false;
            while (!stack.isEmpty()) {
                top = stack.peek();
                // 栈里只剩下负数。不空
                if (top < 0) {
                    stack.push(p);
                    break;
                }
                // 质量相等，方向相反。抵消，加个标志
                if (p + top == 0) {
                    stack.pop();
                    flag = true;
                    break;
                }
                // 向左的行星质量更大。循环要继续
                if (p + top < 0) {
                    stack.pop();
                // 正向行星的质量更大。 不空
                } else {
                    break;
                }
            }
            // 正负抵消
            if (flag) {
                continue;
            }
            // 负数吃到底
            if (stack.isEmpty()) {
                stack.push(p);
            }

        }
        int[] result = new int[stack.size()];
        for (int i=0; i< result.length; i++) {
            result[i] = stack.removeLast();
        }
        return result;
    }
    public int findRepeatNumber(int[] nums) {
        int[] bits = new int[3125];
        // num/64 = 在nums的某个数
        // num%64 = 在64位中的位置
        for(int num: nums) {

            int a = 3&100;
            int bit = bits[num >> 5];
            int i = 1 << (num % 32);
            if ((bits[num>>5] &  1<< (num%32)) != 0)
                return num;
            bits[num>>5] = bits[num>>5] | (1 << (num%32));
        }
        return -1;
    }
    public static void main(String[] args) {
        AsteroidCollision ac = new AsteroidCollision();
        int[] arr = new int[] {-2, -2, -2, 1, -2};
        int[] remainders = ac.asteroidCollision(arr);
        System.out.println(Arrays.toString(remainders));
    }
}
