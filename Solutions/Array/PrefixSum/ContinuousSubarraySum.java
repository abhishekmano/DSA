package Solutions.Array.PrefixSum;

import java.util.HashSet;

//523. Continuous Subarray Sum
//https://leetcode.com/problems/continuous-subarray-sum
public class ContinuousSubarraySum {
    // Calculate prefix sum and take mod of it
    // while iterative check whether there is a sum with same modulo
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] prefix = new int[n];
        prefix[0] = nums[0] % k;
        for (int idx = 1; idx < n; ++idx) {
            prefix[idx] = prefix[idx - 1] + nums[idx];
            prefix[idx] %= k;
        }
        HashSet<Integer> sum = new HashSet<Integer>();
        sum.add(0);
        for (int idx = 1; idx < n; ++idx) {
            if (sum.contains(prefix[idx])) {
                return true;
            }
            sum.add(prefix[idx - 1]);
        }
        return false;

    }
}
