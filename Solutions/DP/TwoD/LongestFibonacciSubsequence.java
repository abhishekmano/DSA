package Solutions.DP.TwoD;

import java.util.HashMap;

//873. Length of Longest Fibonacci Subsequence
//https://leetcode.com/problems/length-of-longest-fibonacci-subsequence
public class LongestFibonacciSubsequence {
    // idea is for every pair check the prev values is there in the array
    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int idx = 0; idx < n; ++idx) {
            map.put(arr[idx], idx);
        }
        for (int curr = 0; curr < n; ++curr) {
            for (int prev = 0; prev < curr; ++prev) {
                int last = arr[curr] - arr[prev];
                if (map.containsKey(last) && last < arr[prev]) {
                    int index = map.get(last);
                    dp[prev][curr] = dp[index][prev] + 1;
                } else {
                    dp[prev][curr] = 2;
                }
                max = Math.max(max, dp[prev][curr]);

            }
        }
        return max > 2 ? max : 0;
    }
}
