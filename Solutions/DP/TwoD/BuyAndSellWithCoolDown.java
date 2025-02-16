package Solutions.DP.TwoD;

//309. Best Time to Buy and Sell Stock with Cooldown
//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
public class BuyAndSellWithCoolDown {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 2][2];
        // 0 is cant buy
        // 1 is you can buy
        for (int idx = n - 1; idx >= 0; --idx) {
            // either buy on that day or dont buy
            dp[idx][1] = Math.max(dp[idx + 1][1], dp[idx + 1][0] - prices[idx]);
            // either sell or dont sell if sell you cant use idx + 1 instead use idx+2
            dp[idx][0] = Math.max(dp[idx + 1][0], prices[idx] + dp[idx + 2][1]);
        }
        return dp[0][1];
    }
}
