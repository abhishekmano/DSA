package Solutions.Queue.MonotonicQueue;

import java.util.ArrayDeque;
import java.util.Deque;

//3420. Count Non-Decreasing Subarrays After K Operations
//https://leetcode.com/problems/count-non-decreasing-subarrays-after-k-operations
public class NonDecreasingSubArrayAfterKOperation {
    // Not very Intuitive
    // but if you have sliding window from left to right then it wont work
    // you need the window from right to left
    // any new left if the left is greater than existing top its not deceasing
    // so make all the element in top to the value of current element
    // Add the new element with the calculated frequency
    // then pop all from left till the k operations are reached
    // count all valid subarrays
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        long count = 0;
        int n = nums.length;
        Deque<int[]> mono = new ArrayDeque<int[]>();
        int start = n - 1;
        int end = n - 1;
        long operations = 0;
        while (start >= 0) {
            // if the top element is less than or equal to then pop
            // equal is popped so that we can increase the freq
            int freq = 0;
            while (!mono.isEmpty() && mono.peekLast()[0] < nums[start]) {
                int[] top = mono.pollLast();
                freq += top[1];
                operations += ((long) nums[start] - top[0]) * top[1];
            }
            mono.offerLast(new int[] { nums[start], freq + 1 });
            while (operations > k) {
                int[] top = mono.pollFirst();
                operations -= ((long) top[0] - nums[end]);
                // reduce the frequency
                top[1]--;
                if (top[1] != 0) {
                    mono.offerFirst(top);
                }
                end--;
            }
            count += (long) (end - start + 1);
            start--;
        }
        return count;
    }
}
