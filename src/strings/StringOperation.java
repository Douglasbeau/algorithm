package strings;

/// LC 3461
public class StringOperation {
    public boolean hasSameDigits(String s) {
        int n = s.length();
        char[] ca = s.toCharArray();
        for (int i = 0; i < n - 2; i++) {
            for (int j = n-1; j >= i + 1; j--) {
                int num = (ca[j] - '0' + ca[j-1] - '0') % 10;
                ca[j] = (char)(num + '0');
            }
        }
        System.out.println(new String(ca));
        return ca[n-1] == ca[n-2];
    }

    public static void main(String[] args) {
        final StringOperation stringOperation = new StringOperation();
        System.out.println(stringOperation.hasSameDigits("34789"));
    }
}
