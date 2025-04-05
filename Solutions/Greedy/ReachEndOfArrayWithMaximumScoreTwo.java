package Solutions.Greedy;
//Google Question

// Given an array of non-negative integers, the goal is to travel from the first index to the last index with maximum possible score with as many jumps allowed. Score of a jump is defined as the number of index jumped multiplied by the value on the jumped index.
// e.g. [3,7,9,10]

// if the jump is from index0 to index2, the score is (2-0)*9 = 18

// Sample input: [3,12,9,10]
// Sample output: 32
// Explanation: jump from index0 to index1 and then to index3 = (1-0) * 12 + (3-1) * 10 = 12 + 20 = 32
public class ReachEndOfArrayWithMaximumScoreTwo {
    // Simple solution
    public int getMaxScore(int[] nums) {
        int total = 0;
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        for (int idx = n - 1; idx > 0; --idx) {
            max = Math.max(max, nums[idx]);
            total += max;
        }
        return total;
    }
}
