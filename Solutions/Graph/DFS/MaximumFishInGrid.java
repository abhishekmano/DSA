package Solutions.Graph.DFS;

//2658. Maximum Number of Fish in a Grid
//https://leetcode.com/problems/maximum-number-of-fish-in-a-grid
public class MaximumFishInGrid {
    // Plain DFS to get max fish in each connected component
    public int findMaxFish(int[][] grid) {
        int res = 0;
        for (int idx = 0; idx < grid.length; ++idx) {
            for (int idy = 0; idy < grid[0].length; ++idy) {
                int sum = getFish(idx, idy, grid);
                res = Math.max(res, sum);
            }
        }
        return res;
    }

    public int getFish(int x, int y, int[][] grid) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length)
            return 0;
        if (grid[x][y] == 0)
            return 0;
        int sum = grid[x][y];
        grid[x][y] = 0;
        sum += getFish(x + 1, y, grid);
        sum += getFish(x - 1, y, grid);
        sum += getFish(x, y + 1, grid);
        sum += getFish(x, y - 1, grid);
        return sum;
    }
}
