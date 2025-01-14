package Solutions.TwoPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

//15. 3Sum
//https://leetcode.com/problems/3sum
public class ThreeSum {
    // O(N2 + NLOGN)
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        for (int idx = 0; idx < n; ++idx) {
            if (idx != 0 && nums[idx - 1] == nums[idx])
                continue;
            int left = idx + 1;
            int right = n - 1;
            while (left < right) {
                if (left != idx + 1 && nums[left] == nums[left - 1]) {
                    left++;
                    continue;
                }
                if (right != n - 1 && nums[right] == nums[right + 1]) {
                    right--;
                    continue;
                }
                int sum = nums[left] + nums[right] + nums[idx];
                if (sum == 0) {
                    List<Integer> res = new ArrayList<Integer>();
                    res.add(nums[idx]);
                    res.add(nums[left]);
                    res.add(nums[right]);
                    result.add(res);
                    left++;
                    right--;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return result;
    }

    // using two sum with Hash
    public List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        int n = nums.length;
        for (int idx = 0; idx < n; ++idx) {
            if (idx != 0 && nums[idx - 1] == nums[idx])
                continue;
            HashSet<Integer> seen = new HashSet<Integer>();
            int target = -nums[idx];
            for (int idy = idx + 1; idy < n; ++idy) {
                int elem = nums[idy];
                int req = target - elem;
                if (seen.contains(req)) {
                    List<Integer> triplet = Arrays.asList(-target, req, nums[idy]);
                    result.add(triplet);
                    while (idy + 1 < n && nums[idy] == nums[idy + 1])
                        idy++;
                }
                seen.add(elem);
            }
        }
        return result;
    }
}
