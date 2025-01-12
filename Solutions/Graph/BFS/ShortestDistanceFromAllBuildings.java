package Solutions.Graph.BFS;

import java.util.LinkedList;
import java.util.Queue;

//317. Shortest Distance from All Buildings
//https://leetcode.com/problems/shortest-distance-from-all-buildings
public class ShortestDistanceFromAllBuildings {
    // Idea Run bfs from all building or from all lands and calculate the minimum
    int[][] adj = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    // Runs in O(M^2) O(N^2);
    public int shortestDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] res = new int[m][n];
        for (int idx = 0; idx < m; ++idx) {
            for (int idy = 0; idy < n; ++idy) {
                if (grid[idx][idy] != 0) {
                    res[idx][idy] = Integer.MAX_VALUE;
                }
            }
        }
        for (int idx = 0; idx < m; ++idx) {
            for (int idy = 0; idy < n; ++idy) {
                if (grid[idx][idy] == 1) {
                    int[][] distance = bfs(grid, idx, idy);
                    addMatrix(res, distance);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int idx = 0; idx < m; ++idx) {
            for (int idy = 0; idy < n; ++idy) {
                if (grid[idx][idy] == 0) {
                    min = Math.min(res[idx][idy], min);
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public void addMatrix(int[][] a, int[][] b) {
        int m = a.length;
        int n = a[0].length;
        for (int idx = 0; idx < m; ++idx) {
            for (int idy = 0; idy < n; ++idy) {
                int first = a[idx][idy];
                int second = b[idx][idy];
                if (first != Integer.MAX_VALUE && second != Integer.MAX_VALUE) {
                    a[idx][idy] = first + second;
                } else {
                    a[idx][idy] = Integer.MAX_VALUE;
                }
            }
        }
    }

    public int[][] bfs(int[][] grid, int x, int y) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] res = new int[m][n];
        for (int idx = 0; idx < m; ++idx) {
            for (int idy = 0; idy < n; ++idy) {
                res[idx][idy] = Integer.MAX_VALUE;
            }
        }
        Queue<int[]> bfs = new LinkedList();
        int distance = 0;
        bfs.offer(new int[] { x, y });
        while (!bfs.isEmpty()) {
            int count = bfs.size();
            while (count != 0) {
                int[] top = bfs.remove();
                count--;
                if (res[top[0]][top[1]] != Integer.MAX_VALUE)
                    continue;
                res[top[0]][top[1]] = distance;
                for (int[] ad : adj) {
                    int newx = top[0] + ad[0];
                    int newy = top[1] + ad[1];
                    if (!isValid(newx, newy, m, n))
                        continue;
                    if (res[newx][newy] != Integer.MAX_VALUE)
                        continue;
                    if (grid[newx][newy] != 0)
                        continue;
                    bfs.offer(new int[] { newx, newy });
                }

            }
            distance++;
        }
        return res;

    }

    public boolean isValid(int x, int y, int m, int n) {
        return x >= 0 && y >= 0 && x < m && y < n;
    }
}
