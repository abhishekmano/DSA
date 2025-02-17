package Solutions.DP.TwoD;

//https://leetcode.com/problems/maximal-square/
//221. Maximal Square
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        int max = 0;
        for (int idx = 1; idx <= m; ++idx) {
            for (int idy = 1; idy <= n; ++idy) {
                if (matrix[idx - 1][idy - 1] == '0')
                    dp[idx][idy] = 0;
                else {
                    int min = Math.min(dp[idx - 1][idy - 1], Math.min(dp[idx - 1][idy], dp[idx][idy - 1]));
                    dp[idx][idy] = min + 1;
                    max = Math.max(min + 1, max);
                }
            }
        }
        return max * max;
    }
}
