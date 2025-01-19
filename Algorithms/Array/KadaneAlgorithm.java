package Algorithms.Array;

public class KadaneAlgorithm {
    // This is used to find the maximum subarray in an array
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
