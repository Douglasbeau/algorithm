package number;

// LC 263 质因数只含2，3，5的数
public class UglyNum {
    public boolean isUgly(int n) {
        if (n <= 0)
            return false;
        while(n % 5 == 0) {
            n /= 5;
        }
        while(n %3 == 0) {
            n /= 3;
        }
        // 简化2的幂判断
        return (n - 1 & n) == 0;
    }
}
