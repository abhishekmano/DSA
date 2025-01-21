package Solutions.Array.PrefixSum;
//2017. Grid Game

//https://leetcode.com/problems/grid-game/description

public class GridGame {
    public long gridGame(int[][] grid) {
        // best path for the second robot is always complete top or complete bottom
        int n = grid[0].length;
        long[] prefix = new long[n];
        long[] prefix2 = new long[n];
        for (int idx = 0; idx < n; ++idx) {
            prefix[idx] = grid[0][idx];
            prefix2[idx] = grid[1][idx];
            if (idx != 0) {
                prefix[idx] += prefix[idx - 1];
                prefix2[idx] += prefix2[idx - 1];
            }
        }
        long min = Long.MAX_VALUE;
        // consider for each breaking point of robot one where it takes the down
        // movement
        for (int idx = 0; idx < n; ++idx) {
            // if the second robot take top path it can accumulate
            long top = prefix[n - 1] - prefix[idx];
            // if it takes the below path
            long bottom = idx == 0 ? 0 : prefix2[idx - 1];
            // take the max
            long max = Math.max(top, bottom);
            // minimize this result
            min = Math.min(max, min);
        }
        return min;
    }
}
