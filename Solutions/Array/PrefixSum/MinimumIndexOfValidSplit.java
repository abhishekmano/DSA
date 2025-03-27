package Solutions.Array.PrefixSum;

import java.util.List;

//2780. Minimum Index of a Valid Split
//https://leetcode.com/problems/minimum-index-of-a-valid-split
public class MinimumIndexOfValidSplit {
    public int minimumIndex(List<Integer> nums) {
        int count = 1;
        int element = nums.get(0);
        int n = nums.size();
        for (int idx = 1; idx < nums.size(); ++idx) {
            int num = nums.get(idx);
            if (element == num)
                count++;
            else
                count--;
            if (count == 0) {
                element = num;
                count = 1;
            }
        }
        int total = 0;
        for (int num : nums) {
            if (num == element)
                total++;
        }
        if (total <= n / 2)
            return -1;
        int running = (nums.get(0) == element) ? 1 : 0;
        for (int idx = 1; idx < n; ++idx) {
            int left = running;
            int right = total - left;
            if (left > idx / 2 && right > (n - idx) / 2)
                return idx - 1;
            running += (nums.get(idx) == element) ? 1 : 0;
        }
        return -1;

    }
}
