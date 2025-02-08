package Solutions.DP.ThreeD;

//2510. Check if There is a Path With Equal Number of 0's And 1's
//https://leetcode.com/problems/check-if-there-is-a-path-with-equal-number-of-0s-and-1s
public class PathWithEqualOnes {
    public boolean isThereAPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int max = n + m;
        int req = (m + n - 1) / 2;
        if (max % 2 == 0)
            return false;
        boolean[][][] dp = new boolean[m + 1][n + 1][req + 1];
        for (int idx = 0; idx < m; ++idx) {
            dp[idx][0][0] = true;
        }
        for (int idy = 0; idy < n; ++idy) {
            dp[0][idy][0] = true;
        }
        for (int idx = 1; idx <= m; ++idx) {
            for (int idy = 1; idy <= n; ++idy) {
                for (int idz = 0; idz <= req; ++idz) {
                    if (grid[idx - 1][idy - 1] == 1) {
                        if (idz == 0) {
                            dp[idx][idy][idz] = false;
                        } else {
                            dp[idx][idy][idz] = dp[idx - 1][idy][idz - 1] || dp[idx][idy - 1][idz - 1];
                        }
                    } else {
                        dp[idx][idy][idz] = dp[idx - 1][idy][idz] || dp[idx][idy - 1][idz];
                    }
                }
            }
        }
        return dp[m][n][req];
    }
}
