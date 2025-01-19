package Solutions.Graph.BFS;

import java.util.LinkedList;
import java.util.Queue;

//1730. Shortest Path to Get Food
//https://leetcode.com/problems/shortest-path-to-get-food
public class ShortestPathToGetFood {
    int[][] dir = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    // Simple Level Order BFS
    public int getFood(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Pair start = new Pair(0, 0);
        for (int idx = 0; idx < m; ++idx) {
            for (int idy = 0; idy < n; ++idy) {
                if (grid[idx][idy] == '*') {
                    start = new Pair(idx, idy);
                    break;
                }
            }
        }
        int steps = 0;
        boolean[][] visited = new boolean[m][n];
        Queue<Pair> bfs = new LinkedList<>();
        visited[start.x][start.y] = true;
        bfs.add(start);
        while (bfs.size() != 0) {
            int count = bfs.size();
            while (count != 0) {
                count--;
                Pair top = bfs.poll();
                for (int[] d : dir) {
                    int newx = top.x + d[0];
                    int newy = top.y + d[1];
                    if (!isValid(newx, newy, m, n))
                        continue;
                    if (visited[newx][newy])
                        continue;
                    if (grid[newx][newy] == 'X')
                        continue;
                    if (grid[newx][newy] == '#')
                        return steps + 1;
                    if (grid[newx][newy] == 'O') {
                        visited[newx][newy] = true;
                        bfs.add(new Pair(newx, newy));
                    }
                }
            }
            steps++;
        }
        return -1;

    }

    public record Pair(int x, int y) {
    };

    public boolean isValid(int x, int y, int m, int n) {
        return x >= 0 && y >= 0 && x < m && y < n;
    }
}
