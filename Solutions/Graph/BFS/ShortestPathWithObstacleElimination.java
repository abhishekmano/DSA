package Solutions.Graph.BFS;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/
//1293. Shortest Path in a Grid with Obstacles Elimination
public class ShortestPathWithObstacleElimination {
    int[][] dir = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public int shortestPath(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][][] seen = new boolean[m][n][k + 1];
        Queue<int[]> bfs = new LinkedList<int[]>();
        bfs.offer(new int[] { 0, 0, 0, 0 });
        while (!bfs.isEmpty()) {
            int[] top = bfs.poll();
            if (top[0] == m - 1 && top[1] == n - 1) {
                return top[3];
            }
            for (int[] d : dir) {
                int newx = top[0] + d[0];
                int newy = top[1] + d[1];
                if (!isValid(newx, newy, m, n))
                    continue;
                int remove = top[2] + (grid[newx][newy] == 1 ? 1 : 0);
                if (remove > k)
                    continue;
                if (seen[newx][newy][remove])
                    continue;
                bfs.offer(new int[] { newx, newy, remove, top[3] + 1 });
                seen[newx][newy][remove] = true;
            }
        }
        return -1;
    }

    public boolean isValid(int x, int y, int m, int n) {
        return x >= 0 && y >= 0 && x < m && y < n;
    }
}
