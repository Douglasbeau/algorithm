package number;
// LC 2147 划分长廊
public class CorridorDivide {
    public int numberOfWays(String corridor) {
        int n = corridor.length();
        int sn = 0;
        for (int i=0; i<n; i++) {
            if (corridor.charAt(i) == 'S')
                sn++;
        }
        // 边界：座位数为0或奇数，不存在方案；2则1种方案。
        if (sn == 0 || (sn & 1) == 1)
            return 0;
        if (sn == 2)
            return 1;

        int MOD = (int) (1e9+7);
        int ans = 1;
        int cur = 0; // 0~i座位数
        int pn = 0; // 连续两对椅子间的植物数量
        for (int i = 0; i<n && cur < sn; i++) {
            // 当前是座位
            if (corridor.charAt(i) == 'S') {
                cur++;
                // 奇数则计算并清零pn
                if ((cur & 1) == 1){
                    ans = (int)((long)ans * (pn + 1) % MOD);
                    pn = 0;
                }
            }
            // 当前是植物，在成对的椅子间才有意义
            else if (cur != 0 && (cur & 1) == 0){
                pn++;
            }
        }
        return ans;
    }
}
