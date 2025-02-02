package Solutions.Array.SlidingWIndow;

import java.util.HashMap;

//1852. Distinct Numbers in Each Subarray
//https://leetcode.com/problems/distinct-numbers-in-each-subarray
public class DistinctNumbersInEachSubArray {
    public int[] distinctNumbers(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        HashMap<Integer, Integer> counter = new HashMap<>();
        for (int idx = 0; idx < k; ++idx) {
            counter.put(nums[idx], counter.getOrDefault(nums[idx], 0) + 1);
        }
        res[0] = counter.size();
        for (int idx = k; idx < n; ++idx) {
            int add = nums[idx];
            int remove = nums[idx - k];
            counter.put(add, counter.getOrDefault(add, 0) + 1);
            counter.put(remove, counter.get(remove) - 1);
            if (counter.get(remove) == 0)
                counter.remove(remove);
            res[idx - k + 1] = counter.size();

        }
        return res;
    }
}
