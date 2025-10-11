package strings;

import java.util.Arrays;

// LC 76
public class MinWindSubString {
    public String minWindow(String s, String t) {
        int tn = t.length();
        int sn = s.length();
        // t中字符词频
        int[] freq = new int[128];
        for (int i = 0; i < tn; i++) {
            freq[t.charAt(i)]++;
        }
        int[] freqWindow = new int[128];
        int l = 0;
        while (l < sn && freq[s.charAt(l)] == 0)
            l++; // 来到t包含的字符
        String ans = "";
        if (l == sn)
            return ans;
        int r = l + 1;
        freqWindow[s.charAt(l)] = 1;
        int covered = 1;
        if (tn == 1)
            return t;
        int start = -1;
        int end = -1;
        while (r < sn) {
            char c = s.charAt(r);
            // 遇到的字符在t内
            if (freq[c] > 0) {
                freqWindow[c]++;
                // 如果c还没凑够
                if (freqWindow[c] <= freq[c]) {
                    covered++;
                    // 如果覆盖了t
                    if (covered == tn) {
                        // 更短则更新答案
                        if (start == -1 || end - start > r - l) {
                            start = l;
                            end = r;
                            if (end - start + 1 == tn)
                                return s.substring(start, end + 1);
                        }
                        //右移l直到cover==tn-1
                        while(covered != tn - 1) {
                            char left = s.charAt(l);
                            if (freq[left] != 0) {
                                freqWindow[left]--;
                                if (freqWindow[left] == freq[left] - 1) {
                                    covered--;
                                }
                            }
                            l++;
                            // l移动到 t中的字符
                            if (covered == tn && freq[s.charAt(l)] != 0 && end - start > r - l){
                                start = l;
                                end = r;
                                if (end - start + 1 == tn)
                                    return s.substring(start, end + 1);
                            }
                        }
                        // 继续右移l到下一个有意义字符
                        while(freq[s.charAt(l)] == 0)
                            l++;
                    }
                }
            }
            r++;
        }
        return start == -1 ? "" : s.substring(start, end + 1);
    }

    public static void main(String[] args) {
        final MinWindSubString mw = new MinWindSubString();
        final String s = mw.minWindow("AbCAbDebAC", "CDA");
        System.out.println(s);
    }
}
