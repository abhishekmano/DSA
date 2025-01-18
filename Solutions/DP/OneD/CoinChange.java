package Solutions.DP.OneD;

import java.util.Arrays;

//322. Coin Change
//https://leetcode.com/problems/coin-change
public class CoinChange {
    // recursive DP
    public int coinChange(int[] coins, int amount) {
        return coinChange(coins, amount, new int[amount + 1]);
    }

    public int coinChange(int[] coins, int rem, int[] dp) {
        if (rem == 0)
            return 0;
        if (rem < 0)
            return -1;
        if (dp[rem] != 0)
            return dp[rem];
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange(coins, rem - coin, dp);
            if (res != -1 && res < min) {
                min = res + 1;
            }
        }
        dp[rem] = (min == Integer.MAX_VALUE) ? -1 : min;
        return dp[rem];
    }

    // iterative DP
    public int coinChange2(int[] coins, int amount) {
        Arrays.sort(coins);
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int coin : coins) {
            if (coin > amount)
                continue;
            for (int idx = 1; idx <= amount; ++idx) {
                if (idx - coin >= 0 && dp[idx - coin] != Integer.MAX_VALUE) {
                    dp[idx] = Math.min(dp[idx], dp[idx - coin] + 1);
                }
            }
        }
        return (dp[amount] == Integer.MAX_VALUE) ? -1 : dp[amount];
    }
}
