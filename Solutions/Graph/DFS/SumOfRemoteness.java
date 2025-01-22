package Solutions.Graph.DFS;

import java.util.LinkedList;
import java.util.Queue;

public class SumOfRemoteness {
    // EASY DFS Solution
    int[][] direction = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public long sumRemoteness(int[][] grid) {
        long result = 0;
        long totalSum = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int idx = 0; idx < m; ++idx) {
            for (int idy = 0; idy < n; ++idy) {
                if (grid[idx][idy] != -1)
                    totalSum += grid[idx][idy];
            }
        }
        for (int idx = 0; idx < m; ++idx) {
            for (int idy = 0; idy < n; ++idy) {
                if (grid[idx][idy] == -1 || grid[idx][idy] == 0)
                    continue;
                long res[] = new long[2];
                dfs(idx, idy, grid, res);
                long valueOnCell = totalSum - res[1];
                result += valueOnCell * res[0];

            }
        }
        return result;

    }

    public void dfs(int x, int y, int[][] grid, long[] res) {
        if (!isValid(x, y, grid.length, grid[0].length))
            return;
        if (grid[x][y] <= 0)
            return;
        res[0]++;
        res[1] += grid[x][y];
        grid[x][y] = 0;
        dfs(x + 1, y, grid, res);
        dfs(x, y + 1, grid, res);
        dfs(x - 1, y, grid, res);
        dfs(x, y - 1, grid, res);
    }

    // BFS Solution if required
    public long sumRemotenessBFS(int[][] grid) {
        Queue<int[]> bfs = new LinkedList<>();
        long result = 0;
        long totalSum = 0;
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int idx = 0; idx < m; ++idx) {
            for (int idy = 0; idy < n; ++idy) {
                if (grid[idx][idy] != -1)
                    totalSum += grid[idx][idy];
            }
        }
        for (int idx = 0; idx < m; ++idx) {
            for (int idy = 0; idy < n; ++idy) {
                if (grid[idx][idy] == -1)
                    continue;
                if (visited[idx][idy])
                    continue;
                bfs.add(new int[] { idx, idy });
                visited[idx][idy] = true;
                int cellCount = 0;
                long sum = 0;
                while (bfs.size() != 0) {
                    int[] top = bfs.poll();
                    sum += grid[top[0]][top[1]];
                    cellCount++;
                    for (int[] dir : direction) {
                        int newx = top[0] + dir[0];
                        int newy = top[1] + dir[1];
                        if (isValid(newx, newy, m, n) && grid[newx][newy] != -1 && !visited[newx][newy]) {
                            visited[newx][newy] = true;
                            int[] pair = new int[] { newx, newy };
                            bfs.offer(pair);
                        }
                    }
                }
                long valueOnCell = totalSum - sum;
                result += valueOnCell * cellCount;

            }
        }
        return result;

    }

    public boolean isValid(int x, int y, int m, int n) {
        return x >= 0 && y >= 0 && x < m && y < n;
    }
}
