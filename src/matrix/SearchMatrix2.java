package matrix;

// LC 240 与前一个比，没有i+1行任意元素>i行任意元素的限制
// 需要估算一下二分的复杂度，是否比z字形查找快
public class SearchMatrix2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        if (row == 1 && col == 1)
            return matrix[0][0] == target;
        // 法一、二分
        // return search(matrix, 0, row-1, 0, col-1, target);
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
}
