package stack;

// LC 150 逆波兰表达式求值
public class ReversePolishNotation {
    public int evalRPN(String[] tokens) {
        int n = tokens.length;
        int[] stack = new int[n / 2 + 1];
        int si = -1;
        for (String token : tokens) {
            int a, b;
            switch (token) {
                case "+" -> {
                    a = stack[si--];
                    b = stack[si--];
                    stack[++si] = a + b;
                }
                case "-" -> {
                    a = stack[si--];
                    b = stack[si--];
                    stack[++si] = b - a;
                }
                case "*" -> {
                    a = stack[si--];
                    b = stack[si--];
                    stack[++si] = a * b;
                }
                case "/" -> {
                    a = stack[si--];
                    b = stack[si--];
                    stack[++si] = b / a;
                }
                default -> stack[++si] = Integer.parseInt(token);
            }
        }
        return stack[0];
    }

    public static void main(String[] args) {
        final ReversePolishNotation notation = new ReversePolishNotation();
        System.out.println(notation.evalRPN(new String[]{"2","1","+","3","*"}));
    }
}
