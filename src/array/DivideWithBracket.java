package array;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

// TODO 还需要看看 —— nums数组中数字逐个相除，怎么加小括号使其最大
// 输出加了括号的表达式，如 10/0.3/(3/5)
public class DivideWithBracket {
    public static void main(String[] args) {
        double[] nums = {10, 0.1, 2, 5};
        double[][] numArr = {
                {10, 0.2, 4},
                {10, 0.1, 2, 5},
                {10, 1000, 5, 2, 0.2, 4},
                {1000, 33, 0.1, 3, 0.4},
                {1000, 33, 0.9, 3, 0.8},
                {10, 0.1, 2, 0.1, 2}
        };
//        String placesNeedBracket = findPlacesNeedBracket(nums);
        for (double[] doubles : numArr) {
            System.out.println(findPlacesNeedBracket(doubles));
        }
    }

    public static String findPlacesNeedBracket(double[] nums){
        Stack<Integer> positions = new Stack<>();
        int len = nums.length;
        boolean needTailsBig = (len%2 == 0);
//        System.out.printf("len:%d; need big:%b\n", len, needTailsBig);
        int p = len - 1;
        Double tailProduct = nums[p];
        for (int i=p-1; i>=1; i--) {
            // 需要更大
            if (needTailsBig) {
                // 只有相除更大时才加括号
                if (nums[i]/tailProduct > nums[i]*tailProduct) {
                    positions.push(i);
                    tailProduct = nums[i]/tailProduct;
                }
                tailProduct = tailProduct * nums[i];
            } else { // 需要更小
                if (nums[i]/tailProduct < nums[i]*tailProduct) {
                    positions.push(i);
                    tailProduct = nums[i]/tailProduct;
                }
                tailProduct = tailProduct * nums[i];
            }
            needTailsBig = !needTailsBig;
        }
//        System.out.println("tail product: " + tailProduct);
        System.out.println("result: " + nums[0]/ tailProduct);
        StringBuilder sb = new StringBuilder();

        sb.append(nums[0]).append("/");
        int total = positions.size();
        for (int i=1; i<len-1; i++) {
            if (positions.size()>0 && positions.peek() == i) {
                sb.append("(").append(nums[i]).append("/");
                positions.pop();
            } else {
                sb.append(nums[i]).append("/");
            }
        }
        sb.append(nums[len-1]);
        while (total-- != 0) {
            sb.append(")");
        }
        return sb.toString();
    }
}
