package Solutions.BinarySearch;

//153. Find Minimum in Rotated Sorted Array
//https://leetcode.com/problems/find-minimum-in-rotated-sorted-array
public class MininRotatedSortedArray {
    public int findMin(int[] nums) {
        int min = Integer.MAX_VALUE;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[left] <= nums[right]) {
                return Math.min(min, nums[left]);
            }
            if (nums[left] <= nums[mid]) {
                min = Math.min(min, nums[left]);
                left = mid + 1;
            } else {
                min = Math.min(min, nums[mid]);
                right = mid - 1;
            }
        }
        return min;
    }
}
