package Solutions.DP.OneD;

//213. House Robber II
//https://leetcode.com/problems/house-robber-ii/
public class HouseRobberTwo {
    public int rob(int[] nums) {
        // take 2 cases from excluding last and excluding first and take the best;
        int n = nums.length;
        if (n == 1)
            return nums[0];
        int ans1 = rob1(nums, 0, n - 2);
        int ans2 = rob1(nums, 1, n - 1);
        return Math.max(ans1, ans2);

    }

    public int rob1(int[] nums, int start, int end) {
        int len = end - start + 1;
        if (len == 1)
            return nums[start];
        if (len == 2)
            return Math.max(nums[start], nums[end]);
        int prev = nums[start];
        int curr = Math.max(nums[start], nums[start + 1]);
        for (int idx = start + 2; idx <= end; ++idx) {
            int next = Math.max(prev + nums[idx], curr);
            prev = curr;
            curr = next;
        }
        return curr;
    }
}
