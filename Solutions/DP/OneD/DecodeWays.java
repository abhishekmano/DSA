package Solutions.DP.OneD;

//91. Decode Ways
//https://leetcode.com/problems/decode-ways/
public class DecodeWays {
    // Space optimization can be done
    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n];
        if (s.charAt(0) == '0')
            return 0;
        dp[0] = 1;
        for (int idx = 1; idx < n; ++idx) {
            if (s.charAt(idx) != '0') {
                dp[idx] += dp[idx - 1];
            }
            if (s.charAt(idx - 1) == '1' || (s.charAt(idx) <= '6' && s.charAt(idx - 1) == '2')) {
                dp[idx] += (((idx - 2) >= 0) ? dp[idx - 2] : 1);
            }
        }
        return dp[n - 1];
    }
}
