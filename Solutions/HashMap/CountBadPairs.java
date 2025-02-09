package Solutions.HashMap;

import java.util.HashMap;

public class CountBadPairs {
    public long countBadPairs(int[] nums) {
        // j - i != nums[j] - nums[i]
        // i - nums[i] != j - nums[j]
        long count = 0;
        // unique i - nums[idx] and their count
        HashMap<Integer, Integer> counter = new HashMap<>();
        int n = nums.length;
        for (int idx = 0; idx < n; ++idx) {
            int diff = idx - nums[idx];
            // increase the count of diff
            counter.put(diff, counter.getOrDefault(diff, 0) + 1);
            // total bad pairs we can make with idx is (idx+1) - (the pairs that cant be
            // made bad [which has the same i - nums[i]])
            int pairs = (idx + 1) - counter.get(diff);
            count += pairs;
        }
        return count;
    }
}
