package Solutions.Array.TwoDimension;

//48. Rotate Image
//https://leetcode.com/problems/rotate-image
public class RotateImage {
    public void rotate(int[][] matrix) {
        // Idea is to swap i and j
        // then take the reflection over j replace j with n - j - 1;
        // swapping i and j
        int n = matrix.length;
        for (int idx = 0; idx < n; ++idx) {
            for (int idy = idx + 1; idy < n; ++idy) {
                int temp = matrix[idx][idy];
                matrix[idx][idy] = matrix[idy][idx];
                matrix[idy][idx] = temp;
            }
        }
        for (int idy = 0; idy < n / 2; ++idy) {
            for (int idx = 0; idx < n; ++idx) {
                int temp = matrix[idx][idy];
                matrix[idx][idy] = matrix[idx][n - idy - 1];
                matrix[idx][n - idy - 1] = temp;
            }
        }
    }
}
