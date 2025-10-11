package strings;

import java.util.LinkedList;

public class MaxScoreRemoveAB {
    int gain = 0;
    public int maximumGain(String s, int x, int y) {
        int n = s.length();
        if (n < 2)
            return 0;

        if (x >= y) {
            s = remove(s, x, true);
            remove(s, y, false);
        } else {
            s = remove(s, y, false);
            remove(s, x, true);
        }
        return gain;
    }

    String remove(String s, int g, boolean ab) {
        int n = s.length();
        if (n < 2)
            return s;
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (stack.isEmpty()) {
                stack.add(c);
                continue;
            }
            char top = stack.peekLast();
            if (ab && top == 'a' && c == 'b' || !ab && top == 'b' && c == 'a') { // 凑成ab/ba
                stack.removeLast();
                this.gain += g;
                continue;
            }
            // 避免多余的字符压栈
            if (top == 'a' || top == 'b' || c == 'a' || c == 'b')
                stack.add(c);
        }
        return listToString(stack);
    }

    String listToString(LinkedList<Character> list) {
        char[] chars = new char[list.size()];
        for (int i = 0; i < list.size(); i++) {
            chars[i] = list.get(i);
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        final MaxScoreRemoveAB maxScoreRemoveAB = new MaxScoreRemoveAB();
        final int a = maxScoreRemoveAB.maximumGain("ababadfbababfabab", 4, 6);
        System.out.println(a);
    }
}
