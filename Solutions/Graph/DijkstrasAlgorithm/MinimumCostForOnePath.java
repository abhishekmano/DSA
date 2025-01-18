package Solutions.Graph.DijkstrasAlgorithm;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MinimumCostForOnePath {
    int[][] dir = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    // O(n⋅m⋅log(n⋅m))
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[2] - b[2]);
        int[][] distance = new int[m][n];
        for (int idx = 0; idx < m; ++idx) {
            Arrays.fill(distance[idx], Integer.MAX_VALUE);
        }
        distance[0][0] = 0;
        // print(distance);
        pq.add(new int[] { 0, 0, 0 });
        while (pq.size() != 0) {
            int[] top = pq.remove();
            int x = top[0];
            int y = top[1];
            int cost = top[2];
            if (cost > distance[x][y])
                continue;
            distance[x][y] = cost;
            for (int idx = 0; idx < 4; ++idx) {
                int newx = x + dir[idx][0];
                int newy = y + dir[idx][1];
                if (!isValid(newx, newy, m, n))
                    continue;
                int newCost = (idx != grid[x][y] - 1) ? cost + 1 : cost;
                if (newCost < distance[newx][newy]) {
                    pq.add(new int[] { newx, newy, newCost });
                    distance[newx][newy] = newCost;
                }
            }

        }
        return distance[m - 1][n - 1];

    }

    public boolean isValid(int x, int y, int m, int n) {
        return x >= 0 && y >= 0 && x < m && y < n;
    }
}
