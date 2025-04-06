package Solutions.DP.DPInGraph;

import java.util.LinkedList;
import java.util.Queue;

//847. Shortest Path Visiting All Nodes
//https://leetcode.com/problems/shortest-path-visiting-all-nodes
public class ShortestPathVisitingAllNode {
    public int shortestPathLength(int[][] graph) {
        Queue<Pair> bfs = new LinkedList<Pair>();
        int n = graph.length;
        int allVisited = (1 << n) - 1;
        int length = 0;
        // System.out.println("all visited " + allVisited);
        boolean[][] visited = new boolean[n][allVisited + 1];
        for (int idx = 0; idx < n; ++idx) {
            bfs.offer(new Pair(1 << idx, idx));
            visited[idx][1 << idx] = true;
        }
        while (bfs.size() != 0) {
            int count = bfs.size();
            while (count != 0) {
                count--;
                Pair top = bfs.poll();
                // System.out.println(top.mask + " len " + (length));
                if (top.mask == allVisited)
                    return length;
                for (int next : graph[top.node]) {
                    // the node is node visited in the current mask
                    int maskWithNode = top.mask | (1 << next);
                    if (!visited[next][maskWithNode]) {
                        bfs.offer(new Pair(maskWithNode, next));
                        visited[next][maskWithNode] = true;
                    }
                }
            }
            length++;
        }
        return 0;
    }

    public record Pair(int mask, int node) {
    };
}
