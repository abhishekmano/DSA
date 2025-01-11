package Solutions.Array.PrefixSum;

//2772. Apply Operations to Make All Array Elements Equal to Zero
//https://leetcode.com/problems/apply-operations-to-make-all-array-elements-equal-to-zero/
//Idea is to construct a prefix sum array and check all elements can be made to zero
// if we want to do z operations add -z at an index and +z at index i+k
public class ApplyOperationstoMakeAllArrayElementsEqualtoZero {
    // O(n) Time O(n) Space
    // space can be optimized
    public boolean checkArray(int[] nums, int k) {
        int n = nums.length;
        int[] prefix = new int[n];
        for (int idx = 0; idx <= n - k; ++idx) {
            prefix[idx] += ((idx == 0) ? 0 : prefix[idx - 1]);
            int sum = prefix[idx] + nums[idx];
            if (sum < 0) {
                return false;
            } else if (sum > 0) {
                prefix[idx] -= sum;
                if (idx + k < n)
                    prefix[idx + k] += sum;
            }
        }
        for (int idx = n - k + 1; idx < n; ++idx) {
            prefix[idx] += ((idx == 0) ? 0 : prefix[idx - 1]);
            if (nums[idx] + prefix[idx] != 0)
                return false;
        }
        return true;
    }

    // Better solution with O(1) Space
    // Copied from leetcode solutions
    public boolean checkArray2(int[] nums, int k) {
        if (k == 1) {
            return true;
        }
        int curr = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (i >= k) {
                curr -= nums[i - k];
            }

            if (nums[i] < curr) {
                return false;
            }

            nums[i] -= curr;
            curr += nums[i];
        }

        return nums[nums.length - 1] == 0;
    }
}
