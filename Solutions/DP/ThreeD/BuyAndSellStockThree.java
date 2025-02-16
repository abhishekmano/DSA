package Solutions.DP.ThreeD;

//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii
//123. Best Time to Buy and Sell Stock III
public class BuyAndSellStockThree {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int k = 2;
        int[][][] dp = new int[n + 1][2][k + 1];
        for (int idk = 1; idk <= k; ++idk) {
            for (int idx = n - 1; idx >= 0; --idx) {
                // only sell is counter as a new transaction
                // 0 is cannot buy
                // 1 is can buy
                dp[idx][0][idk] = Math.max(dp[idx + 1][0][idk], prices[idx] + dp[idx + 1][1][idk - 1]);
                dp[idx][1][idk] = Math.max(dp[idx + 1][1][idk], -prices[idx] + dp[idx + 1][0][idk]);
            }
        }
        return dp[0][1][k];

    }
}
