package Solutions.Array.Maths;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//2033. Minimum Operations to Make a Uni-Value Grid
//https://leetcode.com/problems/minimum-operations-to-make-a-uni-value-grid
public class MinimumOperationsToMakeAUniGrid {
    public int minOperations(int[][] grid, int x) {
        List<Integer> nums = new ArrayList<Integer>();
        for (int[] row : grid) {
            for (int elem : row) {
                nums.add(elem);
            }
        }
        Collections.sort(nums);
        int n = nums.size();
        int median = nums.get(n / 2);
        // System.out.println(median);
        int mod = median % x;
        int result = 0;
        for (int elem : nums) {
            if (elem % x != mod)
                return -1;
            result += Math.abs(elem - median) / x;
        }
        return result;

    }
}
