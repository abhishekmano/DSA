package Solutions.Graph.DijkstrasAlgorithm;

import java.util.Arrays;
import java.util.PriorityQueue;

//https://leetcode.com/problems/find-minimum-time-to-reach-last-room-i
//3341. Find Minimum Time to Reach Last Room I
public class FindMinimumTimeToReachLastRoomOne {
    int[][] dir = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    public int minTimeToReach(int[][] moveTime) {
        int m = moveTime.length;
        int n = moveTime[0].length;
        PriorityQueue<Point> pq = new PriorityQueue<Point>((a, b) -> a.time - b.time);
        pq.offer(new Point(0, 0, 0));
        int[][] minTime = new int[m][n];
        for (int idx = 0; idx < m; ++idx) {
            Arrays.fill(minTime[idx], Integer.MAX_VALUE);
        }
        minTime[0][0] = 0;
        while (!pq.isEmpty()) {
            Point top = pq.poll();
            int x = top.x;
            int y = top.y;
            int time = top.time;
            if (minTime[x][y] < time)
                continue;
            if (x == m - 1 && y == n - 1)
                return time;
            for (int[] d : dir) {
                int newx = x + d[0];
                int newy = y + d[1];
                if (!isValid(newx, newy, m, n))
                    continue;
                int newTime = Math.max(time + 1, moveTime[newx][newy] + 1);
                if (newTime < minTime[newx][newy]) {
                    minTime[newx][newy] = newTime;
                    pq.offer(new Point(newx, newy, newTime));
                }

            }
        }
        return 0;

    }

    public boolean isValid(int x, int y, int m, int n) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    public record Point(int x, int y, int time) {
    };
}
