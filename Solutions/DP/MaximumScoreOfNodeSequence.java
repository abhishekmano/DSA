package Solutions.DP;

import java.util.PriorityQueue;

//2242. Maximum Score of a Node Sequence
//https://leetcode.com/problems/maximum-score-of-a-node-sequence
public class MaximumScoreOfNodeSequence {
    public int maximumScore(int[] scores, int[][] edges) {
        int n = scores.length;
        PriorityQueue<int[]>[] pq = new PriorityQueue[n];
        for (int idx = 0; idx < n; ++idx) {
            pq[idx] = new PriorityQueue<int[]>((a, b) -> a[1] - b[1]);
        }
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            pq[a].add(new int[] { b, scores[b] });
            pq[b].add(new int[] { a, scores[a] });
            if (pq[a].size() > 3)
                pq[a].poll();
            if (pq[b].size() > 3)
                pq[b].poll();
        }
        int max = -1;
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            for (int[] left : pq[a]) {
                for (int[] right : pq[b]) {
                    int c = left[0];
                    int d = right[0];
                    // System.out.println(a +"-" + b + "-" + c+ "-" + d );
                    if (c != d && c != b && d != a) {
                        int sum = scores[a] + scores[b] + scores[c] + scores[d];
                        max = Math.max(max, sum);
                    }
                }
            }
        }
        return max;
    }
}
