package Solutions.Heap;

import java.util.PriorityQueue;

public class TrappingRainWaterTwo {
    int[][] dir = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    public int trapRainWater(int[][] heightMap) {
        PriorityQueue<Point> pq = new PriorityQueue<Point>((a, b) -> a.height - b.height);
        int m = heightMap.length;
        int n = heightMap[0].length;
        boolean[][] visited = new boolean[m][n];
        int result = 0;
        // First set of boundaries
        for (int idx = 0; idx < m; ++idx) {
            // idy = 0; idy = n -1
            visited[idx][0] = true;
            visited[idx][n - 1] = true;
            pq.offer(new Point(idx, 0, heightMap[idx][0]));
            pq.offer(new Point(idx, n - 1, heightMap[idx][n - 1]));
        }
        // second set of boundaries
        for (int idy = 0; idy < n; ++idy) {
            // idx = 0; idx = m -1
            visited[0][idy] = true;
            visited[m - 1][idy] = true;
            pq.offer(new Point(0, idy, heightMap[0][idy]));
            pq.offer(new Point(m - 1, idy, heightMap[m - 1][idy]));
        }
        while (!pq.isEmpty()) {
            Point top = pq.poll();
            int x = top.x;
            int y = top.y;
            int height = top.height;
            for (int[] d : dir) {
                int newx = x + d[0];
                int newy = y + d[1];
                if (!isValid(newx, newy, m, n))
                    continue;
                if (visited[newx][newy])
                    continue;
                if (heightMap[newx][newy] < height) {
                    result += height - heightMap[newx][newy];
                }
                visited[newx][newy] = true;
                pq.offer(new Point(newx, newy, Math.max(height, heightMap[newx][newy])));

            }

        }
        return result;

    }

    public boolean isValid(int x, int y, int m, int n) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    public record Point(int x, int y, int height) {
    };
}
