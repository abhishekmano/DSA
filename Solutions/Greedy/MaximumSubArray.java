package Solutions.Greedy;

//53. Maximum Subarray
//https://leetcode.com/problems/maximum-subarray
public class MaximumSubArray {
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int runningSum = 0;
        for (int num : nums) {
            runningSum += num;
            if (runningSum > max) {
                max = runningSum;
            }
            if (runningSum < 0)
                runningSum = 0;
        }
        return max;
    }
}
