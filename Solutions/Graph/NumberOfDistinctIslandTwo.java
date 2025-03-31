package Solutions.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
//Impossible to come up with the idea and write code in 45 minutes in an interview 
// If you have not seen this problem and knows the solution
//711. Number of Distinct Islands II
//https://leetcode.com/problems/number-of-distinct-islands-ii/

/* 
You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

An island is considered to be the same as another if they have the same shape, or have the same shape after rotation (90, 180, or 270 degrees only) or reflection (left/right direction or up/down direction).

Return the number of distinct islands.
*/
public class NumberOfDistinctIslandTwo {
    int[][] grid;
    HashSet<String> unique;
    List<Integer> points;
    boolean[][] visited;
    int m = 0;
    int n = 0;

    public int numDistinctIslands2(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        visited = new boolean[m][n];
        unique = new HashSet<>();
        for (int idx = 0; idx < m; ++idx) {
            for (int idy = 0; idy < n; ++idy) {
                points = new ArrayList<Integer>();
                if (visited[idx][idy] == false && grid[idx][idy] == 1) {
                    dfs(idx, idy);
                    // System.out.println(points);
                    String rep = getCanonical(points);
                    // System.out.println(rep);
                    unique.add(rep);
                }

            }
        }
        return unique.size();
    }

    // given list of points return its canonical representation
    // by taking all possible 8 forms and finding the one with max string
    // representation
    public String getCanonical(List<Integer> shape) {
        String result = "";
        int len = shape.size();
        // x cordinates after each op of rotation or mirroring
        int[] xm = new int[len];
        int[] ym = new int[len];
        int[] computed = new int[len];
        int shift = m + n;
        for (int c = 0; c < 8; ++c) {
            int index = 0;
            for (int elem : shape) {
                int x = elem / n;
                int y = elem % n;
                // (x,y) ,(-x,y) (x,-y) (-x,-y) //no mirroring on y axis
                // (y,x) ,(-y,x) , (y,-x) (-y,-x)
                // for simplicity first 4 we dont swap
                // for x every odd we change sign // for y every other 2 we chang sign
                if (c < 4) {
                    xm[index] = x;
                    ym[index] = y;
                } else {
                    xm[index] = y;
                    ym[index] = x;
                }
                if (c % 2 == 1) {
                    xm[index] *= -1;
                }
                if (c % 4 >= 2) {
                    ym[index] *= -1;
                }
                index++;
            }
            // find min x and y
            int minx = xm[0];
            int miny = ym[0];
            for (int x : xm) {
                minx = Math.min(minx, x);
            }
            for (int y : ym) {
                miny = Math.min(miny, y);
            }
            for (int idx = 0; idx < len; ++idx) {
                computed[idx] = (xm[idx] - minx) * shift + (ym[idx] - miny);
            }
            Arrays.sort(computed);
            String rep = Arrays.toString(computed);
            // result is smaller than the new rep
            if (result.compareTo(rep) < 0) {
                result = rep;
            }
        }
        return result;
    }

    public void dfs(int x, int y) {
        if (x < 0 || y < 0 || x >= m || y >= n)
            return;
        if (visited[x][y])
            return;
        if (grid[x][y] == 0)
            return;
        int value = x * n + y;
        points.add(value);
        visited[x][y] = true;
        dfs(x + 1, y);
        dfs(x - 1, y);
        dfs(x, y + 1);
        dfs(x, y - 1);
    }
}
