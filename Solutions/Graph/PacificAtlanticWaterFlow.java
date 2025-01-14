package Solutions.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//417. Pacific Atlantic Water Flow
//https://leetcode.com/problems/pacific-atlantic-water-flow
public class PacificAtlanticWaterFlow {
    int[][] dir = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        HashSet<Point> pacific = new HashSet<>();
        HashSet<Point> atlantic = new HashSet<>();
        int m = heights.length;
        int n = heights[0].length;
        // pacific start points
        List<Point> startA = new ArrayList<Point>();
        // atlantic start points
        List<Point> startB = new ArrayList<Point>();
        for (int idx = 0; idx < m; ++idx) {
            startA.add(new Point(idx, 0));
            startB.add(new Point(idx, n - 1));
        }
        for (int idy = 0; idy < n; ++idy) {
            startA.add(new Point(0, idy));
            startB.add(new Point(m - 1, idy));
        }
        pacific = traverse(startA, heights);
        atlantic = traverse(startB, heights);
        List<List<Integer>> result = new ArrayList<>();
        for (Point point : pacific) {
            if (atlantic.contains(point)) {
                List<Integer> p = Arrays.asList(point.x, point.y);
                result.add(p);
            }
        }
        return result;
    }

    public HashSet<Point> traverse(List<Point> start, int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        HashSet<Point> result = new HashSet<>();
        Queue<Point> bfs = new LinkedList();
        for (Point p : start) {
            bfs.add(p);
            result.add(p);
        }
        while (!bfs.isEmpty()) {
            Point top = bfs.remove();
            for (int[] d : dir) {
                int newx = top.x + d[0];
                int newy = top.y + d[1];
                Point next = new Point(newx, newy);
                if (!isValid(newx, newy, m, n) || result.contains(next))
                    continue;
                if (heights[newx][newy] >= heights[top.x][top.y]) {
                    result.add(next);
                    bfs.add(next);
                }
            }
        }
        return result;
    }

    public boolean isValid(int x, int y, int m, int n) {
        return x >= 0 && y >= 0 && x < m && y < n;
    }

    public record Point(int x, int y) {
    };
}
