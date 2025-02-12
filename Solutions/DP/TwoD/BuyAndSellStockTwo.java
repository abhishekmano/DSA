package Solutions.DP.TwoD;

public class BuyAndSellStockTwo {
    // DP solution
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2];
        // 0 you cannot buy
        // 1 you can buy
        for (int idx = n - 1; idx >= 0; --idx) {
            // at idx you can buy means => you bought + you cannot buy next day or you can
            // buy next day
            dp[idx][1] = Math.max(dp[idx + 1][1], dp[idx + 1][0] - prices[idx]);
            // at idx you cannot buy mean => you can sell + you can buy next day or you
            // cannot buy next day
            dp[idx][0] = Math.max(dp[idx + 1][0], dp[idx + 1][1] + prices[idx]);
        }
        return dp[0][1];
    }

    public int maxProfit2(int[] prices) {
        int n = prices.length;
        int sum = 0;
        for (int idx = 1; idx < n; ++idx) {
            // if a high price next day then simply buy what to think
            if (prices[idx] > prices[idx - 1]) {
                sum += prices[idx] - prices[idx - 1];
            }
        }
        return sum;
    }
}
