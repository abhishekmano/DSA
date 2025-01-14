package Solutions.TwoPointer;

//https://leetcode.com/problems/best-time-to-buy-and-sell-stock
//121. Best Time to Buy and Sell Stock
public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int min = prices[0];
        for (int idx = 1; idx < prices.length; ++idx) {
            maxProfit = Math.max(maxProfit, prices[idx] - min);
            min = Math.min(min, prices[idx]);
        }
        return maxProfit;
    }
}
