package Solutions.DP.OneD;

public class LongestPalindromeSubstring {
    public String longestPalindrome(String s) {
        int n = s.length();
        int result = 1;
        int left = 0;
        int right = 0;
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
                if (dp[idx][idy] && len + 1 > result) {
                    left = idx;
                    right = idy;
                    result = len + 1;
                }
            }
        }
        return s.substring(left, right + 1);
    }

    // Using Expand From center
    public String longestPalindrome2(String s) {
        int n = s.length();
        String res = "";
        for (int idx = 0; idx < n; ++idx) {
            int left = idx - 1;
            int right = idx + 1;
            int start1 = expandAndReturn(s, left, right);
            int start2 = expandAndReturn(s, left + 1, right);
            int len1 = (idx - start1) * 2 + 1;
            if (len1 > res.length()) {
                res = s.substring(start1, start1 + len1);
            }
            int len2 = (idx - start2 + 1) * 2;
            if (len2 > res.length()) {
                res = s.substring(start2, start2 + len2);
            }
        }
        return res;
    }

    public int expandAndReturn(String s, int left, int right) {
        int n = s.length();
        while (right < n && left >= 0 && s.charAt(left) == s.charAt(right)) {
            right++;
            left--;
        }
        return left + 1;
    }
}
