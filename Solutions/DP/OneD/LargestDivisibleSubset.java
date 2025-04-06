package Solutions.DP.OneD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//368. Largest Divisible Subset
//https://leetcode.com/problems/largest-divisible-subset
public class LargestDivisibleSubset {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        Arrays.sort(nums);
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxIndex = 0;
        int maxLen = 1;
        int[] prev = new int[n];
        for (int idx = 0; idx < n; ++idx) {
            prev[idx] = idx;
        }
        for (int idx = 0; idx < n; ++idx) {
            for (int idy = 0; idy < idx; ++idy) {
                if (nums[idx] % nums[idy] == 0 || nums[idy] % nums[idx] == 0) {
                    int prevLength = dp[idx];
                    int newLen = dp[idy] + 1;
                    if (newLen > prevLength) {
                        dp[idx] = newLen;
                        prev[idx] = idy;
                    }
                    if (newLen > maxLen) {
                        maxLen = newLen;
                        maxIndex = idx;
                    }
                }
            }
        }
        while (prev[maxIndex] != maxIndex) {
            result.add(nums[maxIndex]);
            maxIndex = prev[maxIndex];
        }
        result.add(nums[maxIndex]);
        Collections.reverse(result);
        return result;
    }
}
