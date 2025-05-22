package Solutions.Array.PrefixSum;

import java.util.Arrays;
import java.util.PriorityQueue;

//https://leetcode.com/problems/zero-array-transformation-iii/
//3362. Zero Array Transformation III
public class ZeroArrayTransformationThree {
    public int maxRemoval(int[] nums, int[][] queries) {
        Arrays.sort(queries, (a, b) -> a[0] - b[0]);
        int n = nums.length;
        int[] prefix = new int[n + 1];
        int counter = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> b - a);
        // iterate through elements in nums also the things which we made zero is idy
        for (int idx = 0, idy = 0; idx < n; ++idx) {
            counter += prefix[idx];
            while (idy < queries.length && queries[idy][0] == idx) {
                pq.offer(queries[idy][1]);
                idy++;
            }
            // make the current number zero
            while (counter < nums[idx] && !pq.isEmpty() && pq.peek() >= idx) {
                counter++;
                prefix[pq.poll() + 1]--;
            }
            // still counter is less then impossible to get a zero
            if (counter < nums[idx])
                return -1;
        }
        return pq.size();

    }
}
