package Solutions.DP.TwoD;

//647. Palindromic Substrings
//https://leetcode.com/problems/palindromic-substrings
public class PalindromicSubStrings {
    public int countSubstrings(String s) {
        int n = s.length();
        int count = 0;
        boolean[][] dp = new boolean[n][n];
        for (int len = 0; len < n; ++len) {
            for (int idx = 0; idx < n - len; ++idx) {
                int idy = idx + len;
                if (len == 0) {
                    dp[idx][idy] = true;
                } else if (len == 1) {
                    dp[idx][idy] = s.charAt(idx) == s.charAt(idy);
                } else {
                    dp[idx][idy] = (s.charAt(idx) == s.charAt(idy)) && dp[idx + 1][idy - 1];
                }
                if (dp[idx][idy]) {
                    count++;
                }

            }
        }
        return count;
    }
}
