package Solutions.BitManipulation;

//https://leetcode.com/problems/find-the-duplicate-number
//287. Find the Duplicate Number
public class FindDuplicateNumber {
    // N LOG N sort and check duplicate
    // Add a set and check duplicate
    // Negative marking or array as hashmap check 41
    public int findDuplicate(int[] nums) {
        int[] bitCount = new int[32];
        int n = nums.length;
        for (int idx = 0; idx < n; ++idx) {
            int num = nums[idx];
            int index = 0;
            while (num != 0) {
                bitCount[index] += num & 1;
                num = num >> 1;
                index++;
            }
            num = idx;
            index = 0;
            while (num != 0) {
                bitCount[index] -= num & 1;
                num = num >> 1;
                index++;
            }
        }
        // print(bitCount);
        // at the end of this process every bit which is greated than 0 will be set in
        // the result
        int result = 0;
        for (int idx = 31; idx >= 0; --idx) {
            result = result << 1;
            if (bitCount[idx] > 0) {
                result = result | 1;
            }

        }
        return result;
    }

    // Tortoise algorithm
    public int findDuplicate2(int[] nums) {
        int slow = 0;
        int fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        // when slow and fast meets slow = 0;
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
