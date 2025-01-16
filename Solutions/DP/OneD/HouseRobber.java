package Solutions.DP.OneD;

//198. House Robber
//https://leetcode.com/problems/house-robber/
public class HouseRobber {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return nums[0];
        if (n == 2)
            return Math.max(nums[1], nums[0]);
        int prev = nums[0];
        int curr = Math.max(nums[1], nums[0]);
        for (int idx = 2; idx < n; ++idx) {
            int next = Math.max(prev + nums[idx], curr);
            prev = curr;
            curr = next;
        }
        return curr;
    }
}
