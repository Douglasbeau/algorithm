package strings;

// 查找最长回文子串
public class Manacher {
    public String longestPalindrome(String s) {
        if (s == null)
            return null;
        if (s.length() < 2) {
            return s;
        }
        // 插入 _
        s = pretreat(s);
        // 以s[c]为中心的字符是最靠右的回文的中心，右边来到R
        int R = -1;
        int C = -1;
        // 记录以每个字符为中心的最长回文的半径
        int[] rArr = new int[s.length()];
        char[] chars = s.toCharArray();
        int center = 0;
        for (int i = 0; i < chars.length; i++) {
            if (i >= R) {
                int d = 0;
                while (i + d < chars.length && i - d > -1 && chars[i + d] == chars[i - d]) {
                    rArr[i]++;
                    d++;
                }
            } else {
                // 观察i关于C的对称点
                int j = 2 * C - i;
                if (j - rArr[j] < 2*C - R) {
                    rArr[i] = R - i;
                } else
                if (j - rArr[i] < 2 * C - R) {
                    rArr[i] = rArr[j];
                }
                else {
                    int d = rArr[i];
                    while (i + d < chars.length && i - d > -1 && chars[i + d] == chars[i - d]) {
                        rArr[i]++;
                        d++;
                    }
                }
            }

            // 更新右边界
            if (i + rArr[i] > R) {
                C = i;
                R = i + rArr[i];
            }
            // 更新最长回文的中心
            center = rArr[i] > rArr[center] ? i : center;
        }

        return s.substring(center - rArr[center] + 1, center + rArr[center]).replace("_", "");
    }

    private String pretreat(String s) {
        // 3 -> 7 2->5
        char[] newS = new char[(s.length() << 1) | 1];
        for (int i = 0; i < s.length(); i++) {
            newS[i<<1] = '_';
            newS[(i<<1)|1] = s.charAt(i);
        }
        newS[newS.length - 1] = '_';
        return new String(newS);
    }

    public static void main(String[] args) {
        final Manacher manacher = new Manacher();
        final String p = manacher.longestPalindrome("abcbaddabc");
        System.out.println(p);
    }
}
