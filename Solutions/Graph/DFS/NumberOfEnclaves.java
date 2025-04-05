package Solutions.Graph.DFS;

//1020. Number of Enclaves
//https://leetcode.com/problems/number-of-enclaves
public class NumberOfEnclaves {
    int m = 0;
    int n = 0;

    public int numEnclaves(int[][] grid) {
        // idea is check all border 1s and renumber it to zero and at the end count the
        // ones in the matrix
        m = grid.length;
        n = grid[0].length;
        for (int idx = 0; idx < m; ++idx) {
            dfs(idx, n - 1, grid);
            dfs(idx, 0, grid);
        }
        for (int idy = 0; idy < n; ++idy) {
            dfs(0, idy, grid);
            dfs(m - 1, idy, grid);
        }
        int total = 0;
        for (int idx = 0; idx < m; ++idx) {
            for (int idy = 0; idy < n; ++idy) {
                total += grid[idx][idy];
            }
        }
        return total;
    }

    public void dfs(int x, int y, int[][] grid) {
        if (x < 0 || y < 0 || x >= m || y >= n)
            return;
        if (grid[x][y] == 0)
            return;
        grid[x][y] = 0;
        dfs(x + 1, y, grid);
        dfs(x - 1, y, grid);
        dfs(x, y + 1, grid);
        dfs(x, y - 1, grid);
    }
}
