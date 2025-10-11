package strings;

import java.util.Arrays;

// 最少删除几个字符串 可以让任意ij 有|freq[i] - freq[j]|<=k
public class KSpecialString {
    public int minimumDeletions(String word, int k) {
        // 遍历得到字符频率
        int[] freq = new int[26];
        for (int i = 0; i < word.length(); i++) {
            freq[word.charAt(i) - 'a']++;
        }
        // 排序
        Arrays.sort(freq);

        int i = 0;
        while (freq[i] == 0) {
            i++;
        }
        int first = i;
        int target = freq[25] - freq[first] - k;

        if (target <= 0) {
            return 0;
        }

        // 0 ~ (gain - k) -> cost
        int[] cost = new int[target + 1];
        int contribute = 1;
        int pre = 24;
        while(contribute <= target) { // pre
            while(freq[pre] == freq[25] - contribute + 1) {
                pre--;
            }
            cost[contribute] = 25 - pre + cost[contribute - 1];
            contribute++;
        }
        // 删除字符 递增
        int toDel = cost[target]; // 只删除右边

        int leftToDel = 0;

        // index -> target - gain => cost
        while (i < 25) {
            leftToDel += freq[i];
            while (freq[i+1] == freq[i]) {
                leftToDel += freq[++i];
            }
            contribute = freq[++i] - freq[first];
            int rightCost = target - contribute < 0 ? 0 : cost[target - contribute];
            toDel = Math.min(toDel, leftToDel + rightCost);

            if (contribute >= target) {
                break;
            }
        }

        return toDel;
    }

    public static void main(String[] args) {
        KSpecialString c = new KSpecialString();
/*        int n = c.minimumDeletions("uzzezzuzenzu", 1);
        System.out.println("min deletions: " + n);*/
        System.out.println(c.tenMirror("11", 2));
    }


    public long tenMirror(String s, int k) {
        int sLen = s.length();
        long num = 0;
        int weight = 1;
        for (int i=sLen-1; i>=0; i--) {
            num += (long)(s.charAt(i) - '0') * weight;
            weight = weight * k;
        }

        // 判断num是mirror
        String numStr = "" + num;
        int left = 0;
        int right = numStr.length() - 1;

        boolean mirror = true;
        while(left < right && mirror) {
            if (numStr.charAt(left++) != numStr.charAt(right--)) {
                mirror = false;
            }
        }
        return mirror ? num : 0;
    }
}