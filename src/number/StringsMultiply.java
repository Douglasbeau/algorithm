package number;

import java.util.LinkedList;

public class StringsMultiply {
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2))
            return "0";
        int n1 = num1.length();
        int n2 = num2.length();
        LinkedList<Integer> res = new LinkedList<>();
        // num1每一位数字
        for (int i=n1-1; i>=0; i--) {
            int v = num1.charAt(i) - '0';
            if (v == 0)
                continue;
            LinkedList<Integer> sub = new LinkedList<>();
            int c = 0;
            for (int j=n2-1; j>=0; j--) {
                int p = v * (num2.charAt(j) - '0') + c;
                c = p / 10;
                sub.addFirst(p % 10);
            }
            if (c != 0)
                sub.addFirst(c);
            // 补上位置权重
            for (int k=1; k<n1-i; k++) {
                sub.add(0);
            }
            res = add(res, sub);
        }
        StringBuilder sb = new StringBuilder();
        for (Integer re : res) {
            sb.append(re);
        }
        return sb.toString();
    }
    LinkedList<Integer> add(LinkedList<Integer> a, LinkedList<Integer> b) {
        if (a.isEmpty())
            return b;
        LinkedList<Integer> res = new LinkedList<>();
        int c = 0;
        int i = a.size() - 1;
        int j = b.size() - 1;
        while(i >= 0 && j>= 0) {
            int v = a.get(i--) + b.get(j--) + c;
            c = v / 10;
            res.addFirst(v % 10);
        }
        // 下面俩while只会进一个
        while(i>=0) {
            int v = c + a.get(i--);
            c = v / 10;
            res.addFirst(v % 10);
        }
        while(j>=0) {
            int v = c + b.get(j--);
            c = v / 10;
            res.addFirst(v % 10);
        }
        if (c != 0)
            res.addFirst(1);
        return res;
    }

    public static void main(String[] args) {
        final StringsMultiply stringsMultiply = new StringsMultiply();
        String p = stringsMultiply.multiply("98", "109");
        System.out.println(p);
    }
}
