package strings;

// 对应的二进制值<=k的最长子串长度
public class LongestSubSeq {
    public int longestSubsequence(String s, int k) {
        int ans = 0;
        int len = s.length();
        long num = 0;
        for (int i=len-1; i>-1; i--) {
            if (s.charAt(i) == '0') {
                ans++;
            } else {
                if(num >= k)
                    continue;
                num += 1L << (len - 1 - i);
                if (num <= k) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        LongestSubSeq longest = new LongestSubSeq();
        final int ans = longest.longestSubsequence("000101010011011001011101111000111111100001011000000100010000111100000011111001000111100111101001111001011101001011011101001011011001111111010011100011110111010000010000010111001001111101100001111"
                , 300429827);
        System.out.println("ans: "+ ans);;
    }
}
