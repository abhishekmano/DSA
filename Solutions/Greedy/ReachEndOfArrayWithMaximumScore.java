package Solutions.Greedy;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

//3282. Reach End of Array With Max Score
//https://leetcode.com/problems/reach-end-of-array-with-max-score
public class ReachEndOfArrayWithMaximumScore {
    // from a to last distance is x and there is another point b distance y from
    // last
    // point without taking a stop at b is => xa
    // if we stop at b => (x - y)a + yb
    // xa < (x - y)a + yb
    // ya < yb
    // a < b so for every element bigger than current better to do a jump
    // i complicated unnecessary
    public long findMaximumScore(List<Integer> nums) {
        int n = nums.size();
        int[] nextBigger = nextLargest(nums);
        long[] dp = new long[n];
        long result = 0;
        for (int idx = 0; idx < n; ++idx) {
            int next = nextBigger[idx];
            if (next != -1) {
                dp[next] = Math.max(dp[next], dp[idx] + (long) (next - idx) * nums.get(idx));
            }
            // consider the case of direct jump from this to end
            long inter = (long) (n - 1 - idx) * nums.get(idx) + dp[idx];
            result = Math.max(result, inter);
        }
        return result;

    }

    public int[] nextLargest(List<Integer> nums) {
        int n = nums.size();
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Stack<Integer> decreasing = new Stack<Integer>();
        for (int idx = 0; idx < n; ++idx) {
            int elem = nums.get(idx);
            while (decreasing.size() != 0 && nums.get(decreasing.peek()) <= elem) {
                int top = decreasing.pop();
                result[top] = idx;
            }
            decreasing.push(idx);
        }
        return result;
    }

    // Simple Solution
    public long findMaximumScore2(List<Integer> nums) {
        Integer[] arr = nums.toArray(new Integer[0]);
        long score = 0;
        int max = 0;
        int n = arr.length - 1;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, arr[i]);
            score += max;
        }
        return score;
    }
}
