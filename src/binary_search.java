// 函数f(x)表示x!的结尾有k个0，给定k，输出有几个x符合要求
public class binary_search {
    public int preimageSizeFZF(int k) {
        int i = 5;
        int n;
        int cnt = 0;
        for (; ; i+=6) {
            if(i > k)
                return 5;
            cnt++;
            n = countFactor5s(cnt);
            for (int j = i; j < i+n; j++) {
                System.out.printf("%d,", j);
            }
            System.out.println(i+n);
            i += n;
            if(i >= k) {
                return 0;
            }
        }
    }
    private int countFactor5s(int n) {
        int r = 0;
        while(n % 5 == 0) {
            n /= 5;
            r++;
        }
        return r;
    }

    public static void main(String[] args) {
        binary_search bs = new binary_search();
        int i = bs.preimageSizeFZF(1000);
    }
}
