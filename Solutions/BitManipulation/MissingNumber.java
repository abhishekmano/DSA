package Solutions.BitManipulation;

//268. Missing Number
//https://leetcode.com/problems/missing-number/
public class MissingNumber {
    // using Bit Manipulation
    public int missingNumber(int[] nums) {
        int res = 0;
        int n = nums.length;
        for (int idx = 0; idx < n; ++idx) {
            res ^= idx + 1;
            res ^= nums[idx];
        }
        return res;
    }

    // you can solve this using the sum of n numbers as well
    public int MissingNumber2(int[] nums) {
        int n = nums.length;
        long sum = n * (n + 1) / 2;
        long current = 0;
        for (int num : nums) {
            current += num;
        }
        return (int) (sum - current);
    }
}
