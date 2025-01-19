package Solutions.Greedy;

//55. Jump Game
//https://leetcode.com/problems/jump-game
public class JumpGame {
    public boolean canJump(int[] nums) {
        int maxReachable = 0;
        for (int idx = 0; idx < nums.length; ++idx) {
            if (maxReachable < idx) {
                return false;
            } else {
                maxReachable = Math.max(maxReachable, idx + nums[idx]);
            }
        }
        return maxReachable >= nums.length - 1;
    }
}
