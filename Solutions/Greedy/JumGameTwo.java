package Solutions.Greedy;

//45. Jump Game II
//https://leetcode.com/problems/jump-game-ii/
public class JumGameTwo {
    public int jump(int[] nums) {
        int count = 0;
        int maxDistance = 0;
        int currentEnd = 0;
        int n = nums.length;
        for (int idx = 0; idx < n - 1; ++idx) {
            maxDistance = Math.max(maxDistance, nums[idx] + idx);
            if (currentEnd == idx) {
                count++;
                currentEnd = maxDistance;
                if (currentEnd >= n - 1)
                    break;
            }

        }
        return count;
    }
}
