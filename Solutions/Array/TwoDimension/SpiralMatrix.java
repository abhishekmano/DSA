package Solutions.Array.TwoDimension;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/spiral-matrix
//54. Spiral Matrix
public class SpiralMatrix {
    int[][] dir = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public List<Integer> spiralOrder(int[][] matrix) {
        int current = 0;
        List<Integer> res = new ArrayList<Integer>();
        int count = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int x = 0;
        int y = 0;
        boolean[][] visited = new boolean[m][n];
        while (count < m * n) {
            while (isValid(x, y, m, n) && !visited[x][y]) {
                res.add(matrix[x][y]);
                visited[x][y] = true;
                x += dir[current][0];
                y += dir[current][1];
                count++;
            }
            x -= dir[current][0];
            y -= dir[current][1];
            current = (current + 1) % 4;
            x += dir[current][0];
            y += dir[current][1];

        }
        return res;

    }

    public boolean isValid(int x, int y, int m, int n) {
        return x >= 0 && y >= 0 && x < m && y < n;
    }
}
