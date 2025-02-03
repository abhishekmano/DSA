package Solutions.Array;

//3105. Longest Strictly Increasing or Strictly Decreasing Subarray
//https://leetcode.com/problems/longest-strictly-increasing-or-strictly-decreasing-subarray
public class LongestStrictlyIncreasingOrDecreasing {
    public int longestMonotonicSubarray(int[] nums) {
        int max = 1;
        int increase = 1;
        int decrease = 1;
        int n = nums.length;
        if (n == 1)
            return 1;
        for (int idx = 1; idx < n; ++idx) {
            if (nums[idx - 1] == nums[idx]) {
                increase = 1;
                decrease = 1;
            } else if (nums[idx - 1] < nums[idx]) {
                increase++;
                decrease = 1;
            } else {
                decrease++;
                increase = 1;
            }
            max = Math.max(max, Math.max(increase, decrease));
        }
        return max;
    }
}
