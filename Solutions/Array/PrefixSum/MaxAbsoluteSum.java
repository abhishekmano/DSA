package Solutions.Array.PrefixSum;

//https://leetcode.com/problems/maximum-absolute-sum-of-any-subarray
//1749. Maximum Absolute Sum of Any Subarray
public class MaxAbsoluteSum {
    // Running double kadane to fund both max and min and taking their best absolute
    public int maxAbsoluteSum(int[] nums) {
        int max = 0;
        int runningP = 0;
        int runningN = 0;
        for (int num : nums) {
            runningP += num;
            runningN += num;
            max = Math.max(max, Math.max(runningP, -runningN));
            if (runningP < 0)
                runningP = 0;
            if (runningN > 0)
                runningN = 0;
        }
        return max;
    }
}
