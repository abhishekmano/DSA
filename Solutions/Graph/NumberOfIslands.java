package Solutions.Graph;

//200. Number of Islands
//https://leetcode.com/problems/number-of-islands/
public class NumberOfIslands {
    int[][] dir = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public int numIslands(char[][] grid) {
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int idx = 0; idx < m; ++idx) {
            for (int idy = 0; idy < n; ++idy) {
                if (grid[idx][idy] == '1') {
                    count++;
                    DFS(idx, idy, grid);
                }
            }
        }
        return count;

    }

    public void DFS(int x, int y, char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        grid[x][y] = '0';
        for (int idx = 0; idx < 4; ++idx) {
            int newx = x + dir[idx][0];
            int newy = y + dir[idx][1];
            if (isValid(newx, newy, m, n) && grid[newx][newy] == '1') {
                DFS(newx, newy, grid);
            }
        }
    }

    public boolean isValid(int x, int y, int m, int n) {
        return x >= 0 && y >= 0 && x < m && y < n;
    }
}
