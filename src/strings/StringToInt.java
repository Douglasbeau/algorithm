package strings;

public class StringToInt {
    public int myAtoi(String s) {
        long ans = 0;
        s = s.trim();
        int i = 0;
        boolean neg = false;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (i == 0 && c == '-') {
                i++;
                neg = true;
                continue;
            }
            if (i == 0 && c == '+') {
                i++;
                continue;
            }
            if (c - '0' > 9 || c - '0' < 0) {
                break;
            }
            ans = neg ? 10 * ans - (c - '0') : 10 * ans + (c - '0');
            if (ans > Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            if (ans < Integer.MIN_VALUE)
                return Integer.MIN_VALUE;
            i++;
        }
        return (int)ans;
    }

    public static void main(String[] args) {
        final StringToInt stringToInt = new StringToInt();
        final int i = stringToInt.myAtoi("-2147483649");
        System.out.println(i);
    }
}
