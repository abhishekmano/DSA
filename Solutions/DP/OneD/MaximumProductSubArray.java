package Solutions.DP.OneD;

//152. Maximum Product Subarray
//https://leetcode.com/problems/maximum-product-subarray
public class MaximumProductSubArray {
    // calculate from right and left
    public int maxProduct2(int[] nums) {
        int left = 1;
        int right = 1;
        int n = nums.length;
        int res = nums[0];
        for (int idx = 0; idx < n; ++idx) {
            left = left * nums[idx];
            right = right * nums[n - idx - 1];
            res = Math.max(res, Math.max(left, right));
            if (left == 0)
                left = 1;
            if (right == 0)
                right = 1;
        }
        return res;
    }

    public int maxProduct(int[] nums) {
        int minSoFar = nums[0];
        int maxSoFar = nums[0];
        int res = nums[0];
        for (int idx = 1; idx < nums.length; ++idx) {
            int curr = nums[idx];
            int tempMin = minSoFar * curr;
            int tempMax = maxSoFar * curr;
            maxSoFar = Math.max(curr, Math.max(tempMin, tempMax));
            minSoFar = Math.min(curr, Math.min(tempMin, tempMax));
            res = Math.max(res, maxSoFar);
        }
        return res;
    }
}
