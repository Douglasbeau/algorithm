package matrix;

// LC 240 与前一个比，没有i+1行任意元素>i行任意元素的限制
// 需要估算一下二分的复杂度，是否比z字形查找快
public class SearchMatrix2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        if (row == 1 && col == 1)
            return matrix[0][0] == target;

        // 法二、z字形
        for (int i=0, j=col-1; i<row && j>=0;) {
            if (target == matrix[i][j])
                return true;
            if (target < matrix[i][j])
                j--;
            else
                i++;
        }
        return false;
    }
    public boolean searchMatrix2(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        // 法一、二分
         return search(matrix, 0, row-1, 0, col-1, target);
    }

    // 需要行的起、止位置，列的起、止位置
    boolean search(int[][] matrix, int rs, int re, int cs, int ce, int target) {
        if(rs > re || cs > ce)
            return false;
        // 在中间行做二分查找
        int mr = rs + (re - rs) / 2;
        int l = cs;
        int r = ce;
        int mid;
        while (l < r) {
            mid = l + (r - l)/ 2;
            if (matrix[mr][mid] == target)
                return true;
            if (matrix[mr][mid] < target) {
                l = mid+1;
            } else {
                r = mid-1;
            }
        }
        if (matrix[mr][l] == target)
            return true;
        // 每次排除一半可能性
        if (matrix[mr][l] > target) {
            return search(matrix, rs, mr-1, l, ce, target) ||
                    search(matrix, mr+1, re, cs, l-1, target);
        }
        return search(matrix, rs, mr-1, l+1, ce, target) ||
                search(matrix, mr+1, re, cs, l, target);
    }

    public static void main(String[] args) {
        int N = 8000;
        int[][] matrix = new int[N][N];
        int v = 1;
        // 斜线填充，确保下面大，右面大
        for (int i = 0; i < N; i++) {
            for (int j = i, k = 0; j >= 0; j--, k++) {
                matrix[j][k] = v++;
            }
        }
        // 到底了 起点总在(3999, k++)
        for (int i = 0; i < N-1; i++) {
            for (int j = N-1, k=i+1; k < N; k++, j--) {
                matrix[j][k] = v++;
            }
        }
        System.out.println(matrix[300][300]);
        System.out.println(matrix[3000][400]);
        System.out.println(matrix[4000][4000]);
        System.out.println(matrix[7999][7888]);
        System.out.println(matrix[7999][7889]);
        SearchMatrix2 searchMatrix2 = new SearchMatrix2();

        long start = System.nanoTime();
        boolean b = searchMatrix2.searchMatrix2(matrix, 200_000_000);
        System.out.println(b + " m2 time cost, " + (System.nanoTime() - start));

        start = System.nanoTime();
        b = searchMatrix2.searchMatrix(matrix, 200_000_000);
        System.out.println(b + " m1 time cost: " + (System.nanoTime()-start));
    }
}
