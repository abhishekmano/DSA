package Solutions.Graph.BFS;

import java.util.LinkedList;
import java.util.Queue;

//1765. Map of Highest Peak
//https://leetcode.com/problems/map-of-highest-peak/
public class MapOfHighestPeak {
    int[][] dir = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length;
        int n = isWater[0].length;
        int[][] res = new int[m][n];
        Queue<int[]> bfs = new LinkedList<>();
        for (int idx = 0; idx < m; ++idx) {
            for (int idy = 0; idy < n; ++idy) {
                if (isWater[idx][idy] == 1) {
                    bfs.add(new int[] { idx, idy });
                    res[idx][idy] = 0;
                } else {
                    res[idx][idy] = -1;
                }

            }
        }
        int distance = 0;
        while (bfs.size() != 0) {
            int count = bfs.size();
            while (count != 0) {
                count--;
                var top = bfs.poll();
                for (int[] d : dir) {
                    int newx = top[0] + d[0];
                    int newy = top[1] + d[1];
                    if (isValid(newx, newy, m, n) && res[newx][newy] == -1) {
                        res[newx][newy] = distance + 1;
                        bfs.offer(new int[] { newx, newy });
                    }
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
