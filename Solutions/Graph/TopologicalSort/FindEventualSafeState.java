package Solutions.Graph.TopologicalSort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//802. Find Eventual Safe States
//https://leetcode.com/problems/find-eventual-safe-states
public class FindEventualSafeState {
    // this is using DFS without topological sort
    // anything part of a cycle is a non safe state
    boolean[] visited;
    boolean[] pathVisited;

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        visited = new boolean[n];
        pathVisited = new boolean[n];
        for (int idx = 0; idx < n; ++idx) {
            if (!visited[idx]) {
                dfs(idx, graph);
            }
        }
        List<Integer> result = new ArrayList();
        for (int idx = 0; idx < n; ++idx) {
            if (!pathVisited[idx]) {
                result.add(idx);
            }
        }
        return result;

    }

    public boolean dfs(int node, int[][] graph) {
        visited[node] = true;
        ;
        pathVisited[node] = true;
        for (int next : graph[node]) {
            if (!visited[next]) {
                if (dfs(next, graph)) {
                    return true;
                }
            } else if (pathVisited[next]) {
                return true;
            }
        }
        pathVisited[node] = false;
        return false;
    }

    // Solution using khans algorithm
    public List<Integer> eventualSafeNodes2(int[][] graph) {
        List<Integer> safe = new ArrayList<>();
        int n = graph.length;
        List<Integer>[] adjList = new List[n];
        for (int idx = 0; idx < n; ++idx) {
            adjList[idx] = new ArrayList<>();
        }
        int[] indegree = new int[n];
        for (int idx = 0; idx < n; ++idx) {
            for (int next : graph[idx]) {
                adjList[next].add(idx);
                indegree[idx]++;
            }
        }
        Queue<Integer> bfs = new LinkedList<>();
        for (int idx = 0; idx < n; ++idx) {
            if (indegree[idx] == 0) {
                bfs.offer(idx);
            }
        }
        while (bfs.size() != 0) {
            var top = bfs.remove();
            for (int next : adjList[top]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    bfs.offer(next);
                }
            }
        }
        for (int idx = 0; idx < n; ++idx) {
            if (indegree[idx] == 0) {
                safe.add(idx);
            }
        }
        return safe;
    }
}
