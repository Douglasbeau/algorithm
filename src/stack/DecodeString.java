package stack;

import java.util.ArrayDeque;
import java.util.LinkedList;

public class DecodeString {
    public String decodeString(String s) {
        int n = s.length();
        ArrayDeque<String> stack = new ArrayDeque<>();
        int i = 0;
        while (i < n) {
            StringBuilder sb = new StringBuilder();
            // 字母 连续起来压栈
            while (i < n && s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                sb.append(s, i, ++i);
            }
            if (sb.length() != 0) {
                // 考虑2[ab]cd 到cd时 stack顶是字符串
                if (!stack.isEmpty() && stack.peekLast().charAt(0) >= 'a')
                    sb.insert(0, stack.pollLast());
                stack.add(sb.toString());
            }
            if (i >= n)
                break;

            // 数字 完整压栈
            int num = 0;
            while(i < n && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                num = 10 * num + s.charAt(i++) - '0';
            }
            if (num != 0) {
                stack.add(String.valueOf(num));
            }

            // 左括号跳过，后面会是字母串和右括号
            if (s.charAt(i) == '[') {
                i++;
                continue;
            }

            // 否则只能是 右括号
            i++;
            // 考虑2[a3[b]]，每个右括号只repeat一次
            String seed = stack.pollLast();
            if(!stack.isEmpty() && stack.peekLast().charAt(0) <= '9') {
                seed = repeat(Integer.parseInt(stack.pollLast()), seed);
            }

            // 并且跟stack顶的字符串连接（如果有）
            if (!stack.isEmpty() && stack.peekLast().charAt(0) >= 'a') {
                stack.add(stack.pollLast() + seed);
            } else {
                stack.add(seed);
            }
        }
        return stack.pollLast();
    }
    String repeat(int repeat, String seed) {
        StringBuilder sb = new StringBuilder();
        while (repeat-- > 0) {
            sb.append(seed);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        final DecodeString decodeString = new DecodeString();
        String s = decodeString.decodeString("100[leetcode]");
        System.out.println(s.length());
    }
}
