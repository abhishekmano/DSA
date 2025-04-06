package Solutions.DP.TwoD;

import java.util.HashMap;

//1027. Longest Arithmetic Subsequence
//https://leetcode.com/problems/longest-arithmetic-subsequence
public class LongestArithmeticSubsequence {
    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> lenMap[] = new HashMap[n];
        int max = 0;
        for (int idx = 0; idx < n; ++idx) {
            lenMap[idx] = new HashMap<Integer, Integer>();
            for (int idy = 0; idy < idx; ++idy) {
                int diff = nums[idx] - nums[idy];
                int prevLen = lenMap[idy].getOrDefault(diff, 1);
                int newLen = prevLen + 1;
                int oldLen = lenMap[idx].getOrDefault(diff, 0);
                if (newLen > oldLen) {
                    lenMap[idx].put(diff, newLen);
                }
                max = Math.max(max, newLen);
            }
        }
        return max;
    }
}
