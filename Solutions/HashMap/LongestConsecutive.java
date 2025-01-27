package Solutions.HashMap;

import java.util.HashSet;
import java.util.Set;

//128. Longest Consecutive Sequence
//https://leetcode.com/problems/longest-consecutive-sequence
public class LongestConsecutive {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int num : nums)
            set.add(num);
        int longest = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int start = num;
                int counter = 1;
                while (set.contains(start + 1)) {
                    counter++;
                    start++;
                }
                longest = Math.max(longest, counter);
            }
        }
        return longest;
    }
}
