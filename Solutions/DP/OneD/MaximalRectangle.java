package Solutions.DP.OneD;

import java.util.ArrayDeque;
import java.util.Deque;

//Combination of maximal rectangle in a histogram and dp
//https://leetcode.com/problems/maximal-rectangle/
//85. Maximal Rectangle
public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] dp = new int[n + 1];
        int max = 0;
        for (int idx = 0; idx < m; ++idx) {
            for (int idy = 1; idy <= n; ++idy) {
                char ch = matrix[idx][idy - 1];
                if (ch == '0') {
                    dp[idy] = 0;
                } else {
                    dp[idy]++;
                }
            }
            // calculate the maximum area rectangle
            int area = maxAreaRectangle(dp);
            max = Math.max(area, max);
        }
        return max;
    }

    public int maxAreaRectangle(int[] heights) {
        int max = 0;
        int n = heights.length;
        Deque<Integer> increasing = new ArrayDeque<Integer>();
        for (int idx = 0; idx <= n; ++idx) {
            while (!increasing.isEmpty() && (idx == n || heights[increasing.peekLast()] > heights[idx])) {
                int mid = heights[increasing.pollLast()];
                int prev = -1;
                if (!increasing.isEmpty())
                    prev = increasing.peekLast();
                ;
                int width = idx - prev - 1;
                int area = mid * (width);
                max = Math.max(area, max);
            }
            increasing.offerLast(idx);
        }
        return max;
    }
}
