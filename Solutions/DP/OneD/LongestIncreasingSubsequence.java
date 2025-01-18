package Solutions.DP.OneD;

import java.util.ArrayList;
import java.util.List;

//300. Longest Increasing Subsequence
//https://leetcode.com/problems/longest-increasing-subsequence
public class LongestIncreasingSubsequence {
    // O(n long n) solution using binary search
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        List<Integer> increasing = new ArrayList<Integer>();
        for (int idx = 0; idx < n; ++idx) {
            addNumToArray(increasing, nums[idx]);
        }
        return increasing.size();
    }

    public void addNumToArray(List<Integer> array, int num) {
        int start = 0;
        int end = array.size() - 1;
        int n = array.size();
        int index = n;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (array.get(mid) >= num) {
                index = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        if (index == n) {
            array.add(num);
        } else {
            array.set(index, num);
        }
    }

    // O(n2) solution using dp
    public int lengthOfLIS2(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 1;
        int max = 1;
        for (int idx = 1; idx < n; ++idx) {
            dp[idx] = Math.max(dp[idx], 1);
            for (int idy = 0; idy < idx; ++idy) {
                if (nums[idx] > nums[idy]) {
                    dp[idx] = Math.max(dp[idy] + 1, dp[idx]);
                }
            }
            max = Math.max(max, dp[idx]);
        }
        return max;
    }
}
