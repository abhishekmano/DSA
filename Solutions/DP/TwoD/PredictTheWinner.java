package Solutions.DP.TwoD;

import java.util.Arrays;

//https://leetcode.com/problems/predict-the-winner/
//486. Predict the Winner
public class PredictTheWinner {
    public boolean predictTheWinner(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int len = 0; len < n; ++len) {
            for (int idx = 0; idx < n - len; ++idx) {
                int idy = idx + len;
                if (len == 0) {
                    dp[idx][idy] = nums[idx];
                } else if (len == 1) {
                    dp[idx][idy] = Math.max(nums[idx], nums[idy]);
                } else {
                    // pick idx
                    int first = nums[idx] + Math.min(dp[idx + 1][idy - 1], dp[idx + 2][idy]);
                    int last = nums[idy] + Math.min(dp[idx + 1][idy - 1], dp[idx][idy - 2]);
                    dp[idx][idy] = Math.max(first, last);
                }
            }
        }
        int playerOne = dp[0][n - 1];
        int playerTwo = sum - playerOne;
        return playerOne >= playerTwo;
    }
}
