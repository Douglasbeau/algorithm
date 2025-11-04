package matrix;

// LC 74搜索二维矩阵，行间有单调性
// 法二看似简单，不如法一
public class SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        // 法二、z字形查找，复杂度O(C+R)
        // for (int i=0, j=matrix[0].length-1; i<matrix.length && j >= 0;) {
        //     if (target == matrix[i][j])
        //         return true;
        //     if (target > matrix[i][j]) {
        //         i++;
        //     } else {
        //         j--;
        //     }
        // }
        // return false;
        return search(matrix, target, 0, matrix.length * matrix[0].length - 1);
    }
    // 法一、二分查找，调用 search(matrix, target, 0, r * c - 1);
    // 时间复杂度O(logC + logR)
    boolean search(int[][] matrix, int target, int l, int r) {
        int C = matrix[0].length;
        boolean found = false;
        // c * i + j
        while (l <= r) {
            int mid = l + (r - l)/2;
            if (matrix[mid / C][mid % C] < target) {
                l = mid + 1;
            } else if (matrix[mid / C][mid % C] > target) {
                r = mid - 1;
            } else {
                return true;
            }
        }
        return found;
    }
}
