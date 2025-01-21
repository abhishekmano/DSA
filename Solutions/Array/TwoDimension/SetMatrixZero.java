package Solutions.Array.TwoDimension;

//73. Set Matrix Zeroes
//https://leetcode.com/problems/set-matrix-zeroes
public class SetMatrixZero {
    public void setZeroes(int[][] matrix) {
        int refrow = -1;
        int refcol = -1;
        int m = matrix.length;
        int n = matrix[0].length;
        boolean found = false;
        // finding a reference row and column to store what to make zero
        for (int idx = 0; idx < m; ++idx) {
            for (int idy = 0; idy < n; ++idy) {
                if (matrix[idx][idy] == 0) {
                    refrow = idx;
                    refcol = idy;
                    found = true;
                    break;
                }
            }
            if (found)
                break;
        }
        if (refrow == -1)
            return;
        // finding all zeros and marking the reference as zero
        for (int idx = 0; idx < m; ++idx) {
            for (int idy = 0; idy < n; ++idy) {
                // mark the row and column as zero so that we can set it to zero
                if (matrix[idx][idy] == 0) {
                    matrix[refrow][idy] = 0;
                    matrix[idx][refcol] = 0;
                }
            }
        }
        // set all rows to zero if some zeroes are there
        for (int idx = 0; idx < m; ++idx) {
            // the row idx is supposed to be set to 0
            if (matrix[idx][refcol] == 0 && idx != refrow) {
                for (int idy = 0; idy < n; ++idy) {
                    matrix[idx][idy] = 0;
                }
            }
        }
        // set all column to zero if some zeroes are there
        for (int idy = 0; idy < n; ++idy) {
            // the col idy is supposed to be set to 0
            if (matrix[refrow][idy] == 0 && idy != refcol) {
                for (int idx = 0; idx < m; ++idx) {
                    matrix[idx][idy] = 0;
                }
            }
        }
        // set our reference row and col to zero
        for (int idx = 0; idx < m; ++idx) {
            matrix[idx][refcol] = 0;
        }
        for (int idy = 0; idy < n; ++idy) {
            matrix[refrow][idy] = 0;
        }
    }
}
