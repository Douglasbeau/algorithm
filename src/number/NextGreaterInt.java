package number;

import java.util.LinkedList;
import java.util.List;

// 输入正整数M，输出下一个更大的数字N——是M各位的重新排列后大于M中的最小的
public class NextGreaterInt {
    public int nextGreaterElement(int n) {
        List<Integer> queue = new LinkedList<>();
        int remainder = n;
        int d = 0;
        int max = 0;
        boolean desc = true;
        while(remainder != 0) {
            // 当前digit
            d = remainder%10;
            remainder = remainder/10;
            if (d < max) {
                desc = false;
                break;
            }
            queue.add(d);
            max = d;
        }
        // 数字各位是递减的
        if (desc)
            return -1;

        int theGreaterDigit = 0;
        for(int i=0; i<queue.size(); i++) {
            if ((theGreaterDigit=queue.get(i)) > d) {
                queue.set(i, d);
                break;
            }
        }
        remainder = remainder * 10 + theGreaterDigit;

        // 队列已经是升序了，直接找
        for (int i : queue) {
            remainder = remainder * 10 + i;
        }
        // 溢出
        if (remainder < n) {
            return -1;
        }

        return remainder;
    }

    public static void main(String[] args) {
//        System.out.println(Integer.MAX_VALUE);
//        int a = 2147483642;
//        System.out.println(a + 200);
        NextGreaterInt nextGreaterInt = new NextGreaterInt();
//        System.out.println(nextGreaterInt.nextGreaterElement(2088888888));
//        System.out.println(nextGreaterInt.nextGreaterElement(1));
//        System.out.println(nextGreaterInt.nextGreaterElement(12));
        System.out.println(nextGreaterInt.nextGreaterElement(1999999999));
    }
}
