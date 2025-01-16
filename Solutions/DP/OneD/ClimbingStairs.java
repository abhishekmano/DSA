package Solutions.DP.OneD;

public class ClimbingStairs {
    public int climbStairs(int n) {
        if (n <= 2)
            return n;
        int prev = 1;
        int curr = 2;
        for (int idx = 3; idx <= n; ++idx) {
            int next = curr + prev;
            prev = curr;
            curr = next;
        }
        return curr;
    }

    public int climbStairs2(int n) {
        if (n <= 2)
            return n;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int idx = 3; idx <= n; ++idx) {
            dp[idx] = dp[idx - 1] + dp[idx - 2];
        }
        return dp[n];
    }
}
