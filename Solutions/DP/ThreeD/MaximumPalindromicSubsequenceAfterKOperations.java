package Solutions.DP.ThreeD;

//3472. Longest Palindromic Subsequence After at Most K Operations
//https://leetcode.com/problems/longest-palindromic-subsequence-after-at-most-k-operations
public class MaximumPalindromicSubsequenceAfterKOperations {
    public int longestPalindromicSubsequence(String s, int k) {
        int n = s.length();
        int[][][] dp = new int[n][n][k + 1];
        for (int idk = 0; idk <= k; ++idk) {
            for (int len = 0; len < n; ++len) {
                for (int idx = 0; idx < n - len; ++idx) {
                    int idy = idx + len;
                    if (len == 0) {
                        dp[idx][idy][idk] = 1;
                    } else if (len == 1) {
                        if (idk != 0)
                            dp[idx][idy][idk] = Math.max(dp[idx][idy][idk], dp[idx][idy][idk - 1]);
                        if (s.charAt(idx) == s.charAt(idy)) {
                            dp[idx][idy][idk] = 2;
                        } else {
                            dp[idx][idy][idk] = 1;
                        }
                    } else {
                        dp[idx][idy][idk] = Math.max(dp[idx + 1][idy][idk], dp[idx][idy - 1][idk]);
                        if (idk != 0)
                            dp[idx][idy][idk] = Math.max(dp[idx][idy][idk], dp[idx][idy][idk - 1]);
                        if (s.charAt(idx) == s.charAt(idy)) {
                            dp[idx][idy][idk] = Math.max(dp[idx][idy][idk], 2 + dp[idx + 1][idy - 1][idk]);
                        }
                    }
                    if (idk != 0 && len != 0) {
                        int ch1 = s.charAt(idx) - 'a';
                        int ch2 = s.charAt(idy) - 'a';
                        int diff = (ch1 - ch2 + 26) % 26;
                        int diff2 = (ch2 - ch1 + 26) % 26;
                        diff = Math.min(diff, diff2);

                        dp[idx][idy][idk] = Math.max(dp[idx][idy][idk - 1], dp[idx][idy][idk]);
                        if (idk - diff >= 0) {
                            if (len == 1)
                                dp[idx][idy][idk] = 2;
                            else
                                dp[idx][idy][idk] = Math.max(dp[idx][idy][idk], 2 + dp[idx + 1][idy - 1][idk - diff]);
                        }

                    }
                }
            }
        }
        return dp[0][n - 1][k];
    }
}
