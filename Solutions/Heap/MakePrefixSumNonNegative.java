package Solutions.Heap;

import java.util.PriorityQueue;

//https://leetcode.com/problems/make-the-prefix-sum-non-negative
//2599. Make the Prefix Sum Non-negative
public class MakePrefixSumNonNegative {
    public int makePrefSumNonNegative(int[] nums) {
        long runningSum = 0;
        int count = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        for (int idx = 0; idx < nums.length; ++idx) {
            minHeap.add(nums[idx]);
            runningSum += nums[idx];
            if (runningSum < 0) {
                count++;
                runningSum -= minHeap.poll();
            }

        }
        return count;
    }
}
