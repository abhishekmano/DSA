package Solutions.DP.OneD;

//2140. Solving Questions With Brainpower
//https://leetcode.com/problems/solving-questions-with-brainpower
public class SolvingQuestionsWIthBrainPower {
    public long mostPoints(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[n + 1];
        for (int idx = n - 1; idx >= 0; --idx) {
            // if you solve this question
            int power = questions[idx][1];
            long result = (long) questions[idx][0] + ((idx + power + 1 <= n) ? dp[idx + power + 1] : 0);
            dp[idx] = Math.max(result, dp[idx + 1]);
        }
        return dp[0];
    }
}
