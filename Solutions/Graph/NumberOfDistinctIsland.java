package Solutions.Graph;

import java.util.HashSet;

//694. Number of Distinct Islands
//https://leetcode.com/problems/number-of-distinct-islands
/*
 You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.

Return the number of distinct islands.
 
 */

public class NumberOfDistinctIsland {
    int[][] dir = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    int m = 0;
    int n = 0;

    public int numDistinctIslands(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        HashSet<String> islands = new HashSet();
        for (int idx = 0; idx < m; ++idx) {
            for (int idy = 0; idy < n; ++idy) {
                if (grid[idx][idy] == 1) {
                    StringBuilder path = new StringBuilder();
                    DFS(idx, idy, grid, path);
                    islands.add(path.toString());
                }
            }
        }
        return islands.size();

    }

    public void DFS(int x, int y, int[][] grid, StringBuilder path) {
        for (int idx = 0; idx < 4; ++idx) {
            int[] d = dir[idx];
            int newx = x + d[0];
            int newy = y + d[1];
            if (isValid(newx, newy) && grid[newx][newy] == 1) {
                path.append(idx);
                grid[newx][newy] = -1;
                DFS(newx, newy, grid, path);
            }
        }
        path.append("-");
    }

    public boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < m && y < n;
    }
}
