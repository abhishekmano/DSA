package Solutions.Graph.BFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//2493. Divide Nodes Into the Maximum Number of Groups
//https://leetcode.com/problems/divide-nodes-into-the-maximum-number-of-groups
public class DivideNodeIntoMaximumGroup {
    public int magnificentSets(int n, int[][] edges) {
        List<Integer>[] adjList = new List[n];
        for (int idx = 0; idx < n; ++idx) {
            adjList[idx] = new ArrayList<Integer>();
        }
        for (int[] edge : edges) {
            int a = edge[0] - 1;
            int b = edge[1] - 1;
            adjList[a].add(b);
            adjList[b].add(a);
        }
        if (!isBipartite(adjList))
            return -1;
        int res = 0;

        boolean[] visited = new boolean[n];
        for (int idx = 0; idx < n; ++idx) {
            if (!visited[idx]) {
                int depth = getNumberOfGroups(adjList, idx, visited);
                res += depth;
            }
        }
        return res;

    }

    public int getNumberOfGroups(List<Integer>[] adjList, int source, boolean[] visited) {
        int n = adjList.length;
        Queue<Integer> bfs = new LinkedList<Integer>();
        int min = 1;
        bfs.add(source);
        visited[source] = true;
        while (bfs.size() != 0) {
            int count = bfs.size();
            while (count != 0) {
                count--;
                var top = bfs.poll();
                for (int next : adjList[top]) {
                    if (visited[next])
                        continue;
                    bfs.offer(next);
                    visited[next] = true;
                    int depth = getDepth(adjList, next);
                    // System.out.println("depth from " + next + " is " + depth);
                    min = Math.max(min, depth);
                }
            }
        }
        // System.out.println("depth of component " + source + " is " + min );
        return min;
    }

    public int getDepth(List<Integer>[] adjList, int source) {
        int n = adjList.length;
        boolean[] newVisited = new boolean[n];
        Queue<Integer> bfs = new LinkedList<Integer>();
        bfs.add(source);
        newVisited[source] = true;
        int depth = 0;
        while (bfs.size() != 0) {
            int count = bfs.size();
            while (count != 0) {
                count--;
                var top = bfs.poll();
                for (int next : adjList[top]) {
                    if (newVisited[next])
                        continue;
                    bfs.offer(next);
                    newVisited[next] = true;
                }
            }
            depth++;
        }
        return depth;
    }

    public boolean isBipartite(List<Integer>[] adjList) {
        int n = adjList.length;
        int[] color = new int[n];
        Arrays.fill(color, -1);
        Queue<Integer> bfs = new LinkedList<Integer>();
        for (int idx = 0; idx < n; ++idx) {
            if (color[idx] == -1) {
                bfs.add(idx);
                int curr = 1;
                while (bfs.size() != 0) {
                    int count = bfs.size();
                    while (count != 0) {
                        count--;
                        var top = bfs.poll();
                        color[top] = curr;
                        for (int next : adjList[top]) {
                            if (color[next] == curr)
                                return false;
                            if (color[next] == -1) {
                                bfs.offer(next);
                                color[next] = 1 - curr;
                            }
                        }
                    }
                    curr = 1 - curr;
                }
            }
        }
        return true;
    }
}
