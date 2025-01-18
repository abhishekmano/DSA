package Solutions.DP.TwoD;

//https://leetcode.com/problems/longest-common-subsequence
//1143. Longest Common Subsequence
public class LongestIncreasingSusSequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int idx = 1; idx <= m; ++idx) {
            for (int idy = 1; idy <= n; ++idy) {
                int prevMax = Math.max(dp[idx][idy - 1], dp[idx - 1][idy]);
                dp[idx][idy] = prevMax;
                if (text1.charAt(idx - 1) == text2.charAt(idy - 1)) {
                    dp[idx][idy] = Math.max(dp[idx][idy], 1 + dp[idx - 1][idy - 1]);
                }
            }
        }
        return dp[m][n];
    }
}
