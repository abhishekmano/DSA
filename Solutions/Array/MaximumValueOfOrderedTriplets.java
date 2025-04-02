package Solutions.Array;

//2873. Maximum Value of an Ordered Triplet I
//https://leetcode.com/problems/maximum-value-of-an-ordered-triplet-i
public class MaximumValueOfOrderedTriplets {
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        for (int idx = 0; idx < n; ++idx) {
            if (idx != 0) {
                leftMax[idx] = Math.max(nums[idx], leftMax[idx - 1]);
                rightMax[n - idx - 1] = Math.max(nums[n - idx - 1], rightMax[n - idx]);
            } else {
                leftMax[idx] = nums[idx];
                rightMax[n - idx - 1] = nums[n - idx - 1];
            }
        }
        long result = 0;
        for (int idx = 1; idx < n - 1; ++idx) {
            long prod = (long) ((long) leftMax[idx - 1] - nums[idx]) * rightMax[idx + 1];
            result = Math.max(result, prod);
        }
        return result;
    }
}
