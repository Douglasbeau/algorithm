package map;

import java.util.HashMap;
import java.util.Map;

// LC 2131 两字符的单词数组 能拼接成的最长回文字符串长度
// 一次选择一对互为回文的单词，最后从剩余的有相同字母的单词中选择一个
public class TwoLetterWordsToPalindrome {
    public int longestPalindrome(String[] words) {
        Map<String, Integer> freq = new HashMap<>();
        for (String w : words) {
            freq.put(w, freq.getOrDefault(w, 0) + 1);
        }
        // 标记是否有单独的同字符单词
        boolean flag = false;
        int ans = 0;
        // 一次选一对word
        for (Map.Entry<String, Integer> entry : freq.entrySet()) {
            String key = entry.getKey();
            int f = entry.getValue();
            // 必须 后续操作会把它减为0
            if (f == 0)
                continue;
            char c = key.charAt(0);
            // 字符相同的，扣减
            if (c == key.charAt(1)) {
                if(f > 1) {
                    freq.put(key, (f & 1) == 0 ? 0 : 1);
                    ans += (f >> 1) << 2; // 例如，两个则ans加上4
                }
                if (freq.get(key) == 1)
                    flag = true;
            } else {
                String rKey = key.substring(1) + c;
                if (freq.getOrDefault(rKey, 0) > 0) {
                    int cnt = Math.min(f, freq.get(rKey));
                    freq.put(rKey, freq.get(rKey) - cnt);
                    freq.put(key, f - cnt);
                    ans += cnt << 2;
                }
            }
        }
        return ans + (flag ? 2 : 0);
    }

    public static void main(String[] args) {
        final TwoLetterWordsToPalindrome tl = new TwoLetterWordsToPalindrome();
        String[] ss = new String[] {"mm","mm","yb","bb"};
        System.out.println(tl.longestPalindrome(ss));
    }
}
