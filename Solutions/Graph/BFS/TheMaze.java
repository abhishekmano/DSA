package Solutions.Graph.BFS;

import java.util.LinkedList;
import java.util.Queue;

//490. The Maze
//https://leetcode.com/problems/the-maze
public class TheMaze {
    int[][] dir = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    // Running simple bfs and marking it as visited only if the ball stops at the
    // point
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int m = maze.length;
        int n = maze[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<Point> queue = new LinkedList<Point>();
        visited[start[0]][start[1]] = true;
        queue.add(new Point(start[0], start[1]));
        while (queue.size() != 0) {
            Point top = queue.poll();
            if (top.x == destination[0] && top.y == destination[1]) {
                return true;
            }
            for (int idx = 0; idx < 4; ++idx) {
                int newx = top.x;
                int newy = top.y;
                while (isValid(newx, newy, m, n) && maze[newx][newy] == 0) {
                    newx += dir[idx][0];
                    newy += dir[idx][1];
                }
                newx -= dir[idx][0];
                newy -= dir[idx][1];
                if (!visited[newx][newy]) {
                    queue.offer(new Point(newx, newy));
                    visited[newx][newy] = true;
                }

            }
        }
        return false;
    }

    public record Point(int x, int y) {
    }

    public boolean isValid(int x, int y, int m, int n) {
        return x >= 0 && y >= 0 && x < m && y < n;
    }
}
