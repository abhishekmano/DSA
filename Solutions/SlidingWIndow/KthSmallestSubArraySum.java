package Solutions.SlidingWIndow;

import java.util.Arrays;

//https://leetcode.com/problems/kth-smallest-subarray-sum/
// 1918. Kth Smallest Subarray Sum
class Solution {
    // Binary Search on Sum less than K
    // Runs in NLogS where S is the Sum
    public int kthSmallestSubarraySum(int[] nums, int k) {
        int left = 0;
        int right = Arrays.stream(nums).sum();
        int result = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int count = subArrayWithSumLess(nums, mid);
            if (count < k) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;

    }

    // Runs in O(n) Sliding Window
    public int subArrayWithSumLess(int[] nums, int sum) {
        int count = 0;
        int left = 0;
        int running = 0;
        int n = nums.length;
        for (int right = 0; right < n; ++right) {
            running += nums[right];
            while (running >= sum) {
                running -= nums[left];
                left++;
            }
            count += (right - left + 1);
        }
        return count;
    }
}