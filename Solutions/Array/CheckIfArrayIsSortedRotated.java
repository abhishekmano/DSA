package Solutions.Array;

//1752. Check if Array Is Sorted and Rotated
//https://leetcode.com/problems/check-if-array-is-sorted-and-rotated
public class CheckIfArrayIsSortedRotated {
    public boolean check(int[] nums) {
        int count = 0;
        int n = nums.length;
        for (int idx = 0; idx < n - 1; ++idx) {
            if (nums[idx] > nums[idx + 1]) {
                count++;
                if (count == 2)
                    return false;
            }
        }
        if (nums[n - 1] > nums[0])
            count++;
        return count <= 1;
    }
}
