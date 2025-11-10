package stack;

public class OperateNumsTo0 {
    public int minOperations(int[] nums) {
        // monotonic stack <
        int n = nums.length;
        int ans = 0;
        int[] stack = new int[n];
        int si = -1;
        for (int i=0; i<n; i++) {
            int cur = nums[i];
            // pop all big values that cost 1 op, equal values only count once
            while(si >= 0 && stack[si] >= cur) {
                if (stack[si--] > cur){
                    ans++;
                }
            }
            stack[++si] = cur;
        }
        return ans + (stack[0] == 0 ? si : si + 1);
    }
}
