package array;

// LC 158 会员题 用只能读取4char的方法，读取指定长度
// 类似拆包、粘包问题
public class Buffer4Reader {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    char[] last = new char[3];
    int lastStart = 0;
    int lastLen = 0;
    public int read(char[] buf, int n) {
        char[] buf4 = new char[4];
        // total read
        int total = 0;
        int cur = 0;
        // check last, could be cleared or partly used
        if (lastLen != 0) {
            copy(last, lastStart, buf, 0, Math.min(n, lastLen));
            if (n >= lastLen) {
                total = lastLen;
                lastLen = 0;
                lastStart = 0;
            } else {
                lastStart += n;
                lastLen -= n;
                total = n;
            }
        }

        while(total != n && (cur = read4(buf4)) != 0) {
            // read to buf4, then copy to buf
            copy(buf4, 0, buf, total, Math.min(cur, n - total));
            // more than need
            if (n - total < cur) {
                lastLen = cur + total - n; // redundant
                // cache bf4 to last
                copy(buf4, n - total, last, 0, lastLen);
                total = n;
                break;
            }
            total += cur;
        }
        return total;
    }

    void copy(char[] from, int fs, char[] to, int ts, int len) {
        for (int i=0; i<len; i++) {
            to[ts+i] = from[i+fs];
        }
    }

    private int read4(char[] buf) {
        return 0; // TODO leetcode implementation
    }
}

