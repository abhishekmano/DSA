package Solutions.Stack.MonotonicStack;

import java.util.ArrayDeque;
import java.util.Deque;

//2104. Sum of Subarray Ranges
//https://leetcode.com/problems/sum-of-subarray-ranges
public class SumOfSubArrayRanges {
    class Solution {
        public long subArrayRanges(int[] nums) {
            long max = minMaxSum(nums, false);
            long min = minMaxSum(nums, true);
            return max - min;
        }

        // to avoid double counting on the left we take strict min
        // on the right it can be minimum or equal
        public long minMaxSum(int[] nums, boolean isMin) {
            Deque<Integer> mono = new ArrayDeque<Integer>();
            int n = nums.length;
            long sum = 0;
            for (int idx = 0; idx <= n; ++idx) {
                while (mono.size() != 0
                        && (idx == n || (isMin ? nums[mono.peek()] >= nums[idx] : nums[mono.peek()] <= nums[idx]))) {
                    var top = mono.pop();
                    int left = mono.isEmpty() ? -1 : mono.peek();
                    int right = idx;
                    sum += ((long) (right - top) * (top - left) * nums[top]);
                }
                if (idx < n)
                    mono.push(idx);
            }
            return sum;
        }

        // This can be used to find max sum but better to reuse the same logic
        public long maxSum(int[] nums) {
            Deque<Integer> mono = new ArrayDeque<Integer>();
            int n = nums.length;
            long sum = 0;
            for (int idx = 0; idx <= n; ++idx) {
                while (mono.size() != 0 && (idx == n || nums[mono.peek()] <= nums[idx])) {
                    var top = mono.pop();
                    int left = mono.isEmpty() ? -1 : mono.peek();
                    int right = idx;
                    sum += ((long) (right - top) * (top - left) * nums[top]);
                }
                if (idx < n)
                    mono.push(idx);
            }
            return sum;
        }
    }
}
