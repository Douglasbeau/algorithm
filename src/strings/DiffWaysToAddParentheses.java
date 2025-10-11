package strings;

import java.util.ArrayList;
import java.util.List;

// 运算优先级自定义种类，返回每种对应的运算结果
// 输入：expression = "2-1-1"
// 输出：[0,2]
public class DiffWaysToAddParentheses {
    public List<Integer> diffWaysToCompute(String expression) {
        // extract numbers and operator
        List<Integer> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();
        extract(expression, numbers, operators);

        List<Integer> result = new ArrayList<>();
        // permutation
        permutation(numbers, operators, result);

        return result;
    }

    private void extract(String expression, List<Integer> numbers, List<Character> operators) {
        StringBuilder number = new StringBuilder();
        for (int i=0; i< expression.length(); i++) {
            char curr = expression.charAt(i);
            if (curr <= '9' && curr >= '0') {
                number.append(curr);
            } else {
                operators.add(curr);
                numbers.add(Integer.valueOf(number.toString()));
                number = new StringBuilder();
            }
        }
        numbers.add(Integer.valueOf(number.toString()));
    }

    // 本质是将numbers两两组合，或说选一个运算符，得到结果后重复该过程
    private void permutation(List<Integer> nums, List<Character> ops, List<Integer> results) {
        if (ops.isEmpty()){
            results.add(nums.get(0));
        }
        for (int i = 0; i < ops.size(); i++) {
            // 选择第i个，计算运算结果，其余的递归调用
            int first = nums.get(i);
            int second = nums.get(i+1);
            int combineTwo = first + second;
            List<Character> currOps = new ArrayList<>(ops);
            Character op = currOps.remove(i);
            switch (op) {
                case '-':
                    combineTwo = first - second;
                    break;
                case '*':
                    combineTwo = first * second;
                    break;
            }
            List<Integer> currNums = new ArrayList<>(nums);
            currNums.remove(i+1);
            currNums.set(i, combineTwo);
            permutation(currNums, currOps, results);
        }

    }

    public static void main(String[] args) {
        DiffWaysToAddParentheses thisObj = new DiffWaysToAddParentheses();

        List<Integer> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();
        thisObj.extract("1*12*4", numbers, operators);
        System.out.println(numbers);
        System.out.println(operators);

        List<Integer> results = new ArrayList<>();
        thisObj.permutation(numbers, operators, results);
        System.out.println(results);
    }
}
