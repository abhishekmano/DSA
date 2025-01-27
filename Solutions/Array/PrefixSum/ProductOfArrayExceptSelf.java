package Solutions.Array.PrefixSum;

import java.util.Arrays;

//238. Product of Array Except Self
//https://leetcode.com/problems/product-of-array-except-self
public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, 1);
        int left = 1;
        int right = 1;
        for (int idx = 0; idx < n; ++idx) {
            res[idx] *= left;
            res[n - idx - 1] *= right;
            left *= nums[idx];
            right *= nums[n - idx - 1];
        }
        return res;
    }
}
