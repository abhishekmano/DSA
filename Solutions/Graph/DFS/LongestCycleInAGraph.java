package Solutions.Graph.DFS;

import java.util.Arrays;

//2360. Longest Cycle in a Graph
//https://leetcode.com/problems/longest-cycle-in-a-graph
public class LongestCycleInAGraph {
    boolean[] visited;
    boolean[] pathVisited;
    int[] depth;
    int max = -1;

    public int longestCycle(int[] edges) {
        // since its mentioned that atmost one outgoing edge
        // each node can be part of atmost one cycle only
        // length of a cycle is depth last node - depth of first repeated node
        int n = edges.length;
        visited = new boolean[n];
        pathVisited = new boolean[n];
        depth = new int[n];
        Arrays.fill(depth, 1);
        for (int idx = 0; idx < n; ++idx) {
            if (!visited[idx]) {
                dfs(idx, edges, 1);
            }
        }
        return max;

    }

    public void dfs(int node, int[] edges, int curr) {
        visited[node] = true;
        pathVisited[node] = true;
        depth[node] = curr;
        if (edges[node] != -1) {
            int next = edges[node];
            if (pathVisited[next]) {
                int len = depth[node] - depth[next] + 1;
                max = Math.max(max, len);
            } else if (!visited[next]) {
                dfs(next, edges, curr + 1);
            }
        }
        pathVisited[node] = false;
    }
}
