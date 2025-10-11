package stack;

public class RemoveKPair {
    public String removeSubstring(String s, int k) {
        int n = s.length();
        int[] stack = new int[n];
        int si = -1;
        int left = -1;
        // 左则累加，右则结算（左不够，左够）
        for (int i=0; i<n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                // 第一个左
                if (left == -1) {
                    stack[++si] = 1;
                    left = si;
                    continue;
                }

                // 前面可能是左，也可能右
                int pre = stack[si];
                stack[++si] = Math.max(0, pre) + 1;
                continue;
            }
            // ')'
            // 之前没有左
            if (left == -1) {
                stack[++si] = -1;
                continue;
            }
            // 之前有左，则累计右括号
            int pre = stack[si];
            stack[++si] = Math.min(0, pre) - 1;
            // 第一个右，打断了左
            if (stack[si] == -1 && si - left < k) {
                left = -1;
                continue;
            }
            // 满足k平衡
            if (stack[si] == -k) {
                si -= 2 * k;
                left = si >= left ? left : -1;
            }
            // 不满足k平衡，没有什么影响
        }
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<=si; i++) {
            sb.append(stack[i] < 0 ? ")" : "(");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        final RemoveKPair removeKPair = new RemoveKPair();
        System.out.println(removeKPair.removeSubstring("((()))()()()", 3));;
    }
}
