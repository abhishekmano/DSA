package Solutions.Graph.DijkstrasAlgorithm;

import java.util.Arrays;
import java.util.PriorityQueue;

//https://leetcode.com/problems/the-maze-ii/
//505. The Maze II
public class TheMazeTwo {
    int[][] dir = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    // Can be done in M * N Log(MN)
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int m = maze.length;
        int n = maze[0].length;
        int[][] distance = new int[m][n];
        for (int idx = 0; idx < m; ++idx) {
            Arrays.fill(distance[idx], Integer.MAX_VALUE);
        }
        PriorityQueue<Point> queue = new PriorityQueue<Point>((a, b) -> a.distance - b.distance);
        distance[start[0]][start[1]] = 0;
        queue.add(new Point(start[0], start[1], 0));
        while (queue.size() != 0) {
            Point top = queue.poll();
            if (top.x == destination[0] && top.y == destination[1]) {
                return top.distance;
            }
            if (top.distance != distance[top.x][top.y])
                continue;
            for (int idx = 0; idx < 4; ++idx) {
                int newx = top.x;
                int newy = top.y;
                int steps = top.distance;
                while (isValid(newx, newy, m, n) && maze[newx][newy] == 0) {
                    newx += dir[idx][0];
                    newy += dir[idx][1];
                    steps++;
                }
                newx -= dir[idx][0];
                newy -= dir[idx][1];
                steps--;
                if (distance[newx][newy] > steps) {
                    queue.offer(new Point(newx, newy, steps));
                    distance[newx][newy] = steps;
                }

            }
        }
        return -1;
    }

    public record Point(int x, int y, int distance) {
    }

    public boolean isValid(int x, int y, int m, int n) {
        return x >= 0 && y >= 0 && x < m && y < n;
    }
}
