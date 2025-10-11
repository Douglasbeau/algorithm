package array;

// 将质数放在质数位置，位置索引从1开始，排列方式有K种，返回(K % (10^9 + 7))
// 可优化点是：质数的计数
public class PutPrimeAtPrimePosition {
    final static int BASE = (int) (Math.pow(10, 9) + 7);

    public int numPrimeArrangements(int n) {

        // 找到质数， 排列组合； 跟其余的数排列组合；找的过程，需要对每个都遍历n/2个数
//        int primeCount = 0;
//        for (int i=1; i<=n ;i++) {
//            if (isPrime(i)) {
//                primeCount++;
//            }
//        }
        int primeCount = countPrime(n);
        long factorial1 = factorial(primeCount);

        long factorial2 = factorial(n - primeCount);
        return (int) (factorial1 * factorial2 % BASE);
    }
    // 计数质数的第二种方法——挑选出来合数，参考别人的答案
    private int countPrime(int n) {
        boolean[] isComposite = new boolean[n+1];
        int cntPrime = 0;
        for (int i=2; i<=n; i++) {
            if (!isComposite[i]) { //不是合数就++
                cntPrime++;
                for (int j = 2; i*j <=n; j++) {
                    isComposite[i*j] = true;
                }
            }

        }
        return cntPrime;
    }

    private boolean isPrime(int n) {
        if (n <= 1){
            return false;
        }
        for (int i = 2; i <= n / 2; i++) {
            if (n%i == 0) {
                return false;
            }
        }
        return true;
    }

    private long factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        long result = 1;
        for (int i = 2; i<=n; i++) {
            result = result * i;
            if (result>BASE) {
                result = result % BASE;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        PutPrimeAtPrimePosition putPrimeAtPrimePosition = new PutPrimeAtPrimePosition();
//        boolean prime = putPrimeAtPrimePosition.isPrime(10);
//        System.out.println(prime);
//        boolean prime = putPrimeAtPrimePosition.isPrime(1);
//        System.out.println(prime);
        int i = putPrimeAtPrimePosition.numPrimeArrangements(2);
        System.out.println(i);
        long factorial = putPrimeAtPrimePosition.factorial(5);
        System.out.println(factorial);
//        System.out.println(putPrimeAtPrimePosition.countPrime(7));
    }
}
