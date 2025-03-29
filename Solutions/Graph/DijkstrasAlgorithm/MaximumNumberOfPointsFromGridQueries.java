package Solutions.Graph.DijkstrasAlgorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

//2503. Maximum Number of Points From Grid Queries
//https://leetcode.com/problems/maximum-number-of-points-from-grid-queries
public class MaximumNumberOfPointsFromGridQueries {
    class Solution {
        int[][] dir = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

        // Instead of running the queries for each value
        // sort the queries values and find the answers incrementally
        public int[] maxPoints(int[][] grid, int[] queries) {
            HashMap<Integer, Integer> answers = new HashMap<Integer, Integer>();
            // length of query
            int k = queries.length;
            int[] sorted = new int[queries.length];
            for (int idx = 0; idx < queries.length; ++idx) {
                sorted[idx] = queries[idx];
            }
            Arrays.sort(sorted);
            int m = grid.length;
            int n = grid[0].length;
            boolean[][] visited = new boolean[m][n];
            int points = 0;
            PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> grid[a[0]][a[1]] - grid[b[0]][b[1]]);
            int index = 0;
            pq.add(new int[] { 0, 0 });
            visited[0][0] = true;
            while (!pq.isEmpty()) {
                if (index >= k)
                    break;
                int[] top = pq.poll();
                int value = grid[top[0]][top[1]];
                while (index < k && value >= sorted[index]) {
                    answers.put(sorted[index], points);
                    index++;
                }
                points++;
                for (int[] d : dir) {
                    int newx = top[0] + d[0];
                    int newy = top[1] + d[1];
                    if (!isValid(newx, newy, m, n))
                        continue;
                    if (visited[newx][newy])
                        continue;
                    visited[newx][newy] = true;
                    pq.offer(new int[] { newx, newy });
                }

            }
            int[] result = new int[k];
            for (int idx = 0; idx < k; ++idx) {
                result[idx] = answers.getOrDefault(queries[idx], points);
            }
            return result;

        }

        // public record Point(int x , int y){}
        public boolean isValid(int x, int y, int m, int n) {
            return x >= 0 && x < m && y >= 0 && y < n;
        }
    }
}
