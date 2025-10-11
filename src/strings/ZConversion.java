package strings;

// 字符串的字符从上到下、从左到右排布，给定行数，返回重排后的从左到右、从上到下读出的字符串
public class ZConversion {
    public String convert(String s, int numRows) {
        System.out.println(s.length());
        char[] converted = new char[s.length()];
        int j = 0;
        for(int r = 0; r < numRows; r++) {
            int i = r;
            int skip;
            if (numRows == 1) {
                skip = 1;
            } else if (r == 0 || r == numRows - 1) {
                skip = (numRows - 1) << 1;
            } else {
                skip = (numRows - 1 - r) << 1;
            }
            while(i < s.length()) {
                converted[j++] = s.charAt(i);
                i = i + skip;
                if (skip != 1)
                    skip = (r == numRows - 1 || r == 0) ? skip : (numRows - 1 << 1) - skip;
            }
        }
        return new String(converted);
    }

    public static void main(String[] args) {
        final ZConversion c = new ZConversion();
        final String ss = c.convert("123456abcdef", 10);
        System.out.println(ss);
    }
}
