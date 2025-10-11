package dp;

import java.util.ArrayList;
import java.util.List;

public class RestoreIP {
    String s;
    List<String> ans;
    int n;

    public List<String> restoreIpAddresses(String s) {
        this.s = s;
        this.n = s.length();
        ans = new ArrayList<>();
        if (n < 4 || n > 12)
            return ans;
        process(new StringBuilder(), 0, 4);
        return ans;
    }

    // 从offset指针往后，分成segs段
    void process(StringBuilder sb, int offset, int segs) {
        int m = n - offset;
        if (segs == 0 && m == 0) {
            ans.add(sb.toString());
            return;
        }
        if (m > 3 * segs)
            return;
        if (m == 0 || segs == 0)
            return;
        if (sb.length() != 0)
            sb.append(".");
        // 取一个数字
        String one = s.substring(offset, offset + 1);
        process(sb.append(one), offset + 1, segs - 1);
        sb.delete(sb.length() - 1, sb.length());

        if (s.charAt(offset) == '0' || m == 1) {
            if(segs < 4)
                sb.deleteCharAt(sb.length()-1);
            return;
        }
        String two = s.substring(offset, offset + 2);
        process(sb.append(two), offset + 2, segs - 1);
        sb.delete(sb.length() - 2, sb.length());
        if (m == 2) {
            if(segs < 4)
                sb.deleteCharAt(sb.length()-1);
            return;
        }
        int three = Integer.parseInt(s.substring(offset, offset + 3));
        if (three >= 256) {
            if(segs < 4)
                sb.deleteCharAt(sb.length()-1);
            return;
        }
        process(sb.append(three), offset + 3, segs - 1);
        sb.delete(sb.length() - 3, sb.length());

        if(segs < 4)
            sb.deleteCharAt(sb.length()-1);
    }

    public static void main(String[] args) {
        final RestoreIP restoreIP = new RestoreIP();
        final List<String> strings = restoreIP.restoreIpAddresses("25525511135");
        System.out.println(strings);
    }
}
