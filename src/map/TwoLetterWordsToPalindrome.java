package map;

import java.util.HashMap;
import java.util.Map;

// LC 2131 两字符的单词数组 能拼接成的最长回文字符串长度
// 一次选择一对互为回文的单词，最后从剩余的有相同字母的单词中选择一个
public class TwoLetterWordsToPalindrome {
    public int longestPalindrome(String[] words) {
        Map<String, Integer> freq = new HashMap<>();
        // 俩字符相同的，单独统计一下
        int[] sameCharFreq = new int[26];
        for (String w : words) {
            freq.put(w, freq.getOrDefault(w, 0) + 1);
            if (w.charAt(0) == w.charAt(1))
                sameCharFreq[w.charAt(0) - 'a']++;
        }
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
                int cnt;
                if((cnt = sameCharFreq[c - 'a']) > 1) {
                    sameCharFreq[c - 'a'] = ((cnt & 1) == 0) ? 0 : 1;
                    freq.put(key, sameCharFreq[c - 'a']);
                    ans += (cnt >> 1) << 2; // 例如，两个则ans加上4
                }
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
        // 成对的最多者
        int maxCnt = 0;
        for (int scf : sameCharFreq) {
            maxCnt = Math.max(maxCnt, scf);
        }
        return ans + maxCnt * 2;
    }
}
