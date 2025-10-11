package strings;

import java.util.Arrays;

// 无重复字符的 最长子串 的长度
public class LongestSubstringWithoutRepeat {
    public int lengthOfLongestSubstring(String s) {
        if(s.length() == 0)
            return 0;
        // 记录字符的下标
        int[] indices = new int[128];
        Arrays.fill(indices, -1);

        // 左右指针往右移动，直到右指针到达 s.length
        int l = 0;
        int r = 1;
        indices[s.charAt(l)] = l;
        // 记录最大长度
        int maxLen = 0;
        while (r < s.length()) {
            char c = s.charAt(r);
            // r处的字符 不存在，记录其index
            if (indices[c] == -1){
                indices[c] = r++;
                continue;
            }
            maxLen = Math.max(maxLen, r - l);
            // 已存在，清理indices =》 -1，l变成重复位置后一个，r作为 c的 新的indices值
            int repeatPos = indices[c];
            setMinusOne(indices, l, repeatPos, s);
            l = repeatPos + 1;
            indices[c] = r++;
            // 加速：如果当前maxLen 已经大于s长度减去l
            if (maxLen > s.length() - l)
                break;
        }
        // 遍历结束，取最大长度
        maxLen = Math.max(maxLen, r - l);
        return maxLen;
    }

    private void setMinusOne(int[] indices, int l, int rExcluded, String s) {
        for (int i=l; i<rExcluded; i++) {
            indices[s.charAt(i)] = -1;
        }
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeat ls = new LongestSubstringWithoutRepeat();
        int maxLen = ls.lengthOfLongestSubstring("abccbnsas");
        System.out.println(maxLen);
    }

}
