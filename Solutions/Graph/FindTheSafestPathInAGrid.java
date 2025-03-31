package Solutions.Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

//https://leetcode.com/problems/find-the-safest-path-in-a-grid/
//2812. Find the Safest Path in a Grid
public class FindTheSafestPathInAGrid {
    int[][] dir = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    // IDEA
    // Run a BFS first multi source to all to get the maximum distance for all
    // points from thief
    // After that run a Shortest Path algorithm from top left till we reach bottom
    // right
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int m = grid.size();
        int n = grid.get(0).size();
        int[][] distance = getDistance(grid);
        boolean[][] visited = new boolean[m][n];
        // Perform Dijkstra from top left;
        PriorityQueue<Point> pq = new PriorityQueue<>((a, b) -> distance[b.x][b.y] - distance[a.x][a.y]);
        pq.offer(new Point(0, 0));
        visited[0][0] = true;
        while (!pq.isEmpty()) {
            var top = pq.poll();
            if (top.x == m - 1 && top.y == n - 1)
                return distance[m - 1][n - 1];
            int value = distance[top.x][top.y];
            if (value == 0)
                return 0;
            for (int[] d : dir) {
                int newx = top.x + d[0];
                int newy = top.y + d[1];
                if (!isValid(newx, newy, m, n))
                    continue;
                if (visited[newx][newy] == true)
                    continue;
                distance[newx][newy] = Math.min(distance[newx][newy], value);
                visited[newx][newy] = true;
                ;
                pq.offer(new Point(newx, newy));
            }

        }
        return -1;

    }

    public int[][] getDistance(List<List<Integer>> grid) {
        int m = grid.size();
        int n = grid.get(0).size();
        int[][] result = new int[m][n];
        for (int idx = 0; idx < m; ++idx) {
            Arrays.fill(result[idx], Integer.MAX_VALUE);
        }
        Queue<Point> bfs = new LinkedList<Point>();
        for (int idx = 0; idx < m; ++idx) {
            for (int idy = 0; idy < n; ++idy) {
                if (grid.get(idx).get(idy) == 1) {
                    bfs.offer(new Point(idx, idy));
                    result[idx][idy] = 0;
                }
            }
        }
        int distance = 0;
        while (!bfs.isEmpty()) {
            int count = bfs.size();
            while (count != 0) {
                Point top = bfs.poll();
                count--;
                for (int[] d : dir) {
                    int newx = top.x + d[0];
                    int newy = top.y + d[1];
                    if (!isValid(newx, newy, m, n))
                        continue;
                    if (result[newx][newy] != Integer.MAX_VALUE)
                        continue;
                    result[newx][newy] = distance + 1;
                    bfs.offer(new Point(newx, newy));
                }

            }
            distance++;
        }
        return result;

    }

    public record Point(int x, int y) {
    };

    public boolean isValid(int x, int y, int m, int n) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
