package Solutions.Array;

//31. Next Permutation
//https://leetcode.com/problems/next-permutation
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        // Idea is we have to find from right which number is not decreasing
        // 1 2 3 6 5 4 => here we pick 6 since its not less than 3
        // to the right of 3 we cannot make any bigger number
        // swap 3 with its next highest 1 2 4 6 5 3
        // reverse everything after 4 => 1 2 4 3 5 6
        int n = nums.length;
        int breakpoint = -1;
        for (int idx = n - 2; idx >= 0; --idx) {
            if (nums[idx] < nums[idx + 1]) {
                breakpoint = idx;
                break;
            }
        }
        if (breakpoint == -1) {
            reverse(nums, 0, n - 1);
            return;
        }
        int nextHighest = breakpoint + 1;
        for (int idx = breakpoint + 1; idx < n; ++idx) {
            if (nums[idx] <= nums[breakpoint]) {
                break;
            } else {
                nextHighest = idx;
            }
        }
        int temp = nums[breakpoint];
        nums[breakpoint] = nums[nextHighest];
        nums[nextHighest] = temp;
        reverse(nums, breakpoint + 1, n - 1);

    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
