package Solutions.Graph.DFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//https://leetcode.com/problems/count-the-number-of-complete-components
//2685. Count the Number of Complete Components
public class CountNumberOfCompleteComponents {
    public int countCompleteComponents(int n, int[][] edges) {
        List<Integer>[] adjList = new List[n];
        for (int idx = 0; idx < n; ++idx) {
            adjList[idx] = new ArrayList<Integer>();
        }
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            adjList[a].add(b);
            adjList[b].add(a);
        }
        int totalComplete = 0;
        int currentElem = 0;
        int currentEdge = 0;
        boolean[] visited = new boolean[n];
        for (int idx = 0; idx < n; ++idx) {
            if (!visited[idx]) {
                Deque<Integer> dfs = new ArrayDeque<Integer>();
                dfs.offerFirst(idx);
                visited[idx] = true;
                while (dfs.size() != 0) {
                    int top = dfs.pollFirst();
                    // System.out.println("Added " + top + " adj is " + adjList[top].size() );
                    currentElem++;
                    currentEdge += adjList[top].size();
                    for (int next : adjList[top]) {
                        if (!visited[next]) {
                            visited[next] = true;
                            dfs.offerFirst(next);
                        }
                    }
                }
                // System.out.println(currentEdge);
                // System.out.println(currentElem);
                // System.out.println("-----------");

                if (currentEdge == currentElem * (currentElem - 1)) {
                    totalComplete++;
                }
                currentElem = 0;
                currentEdge = 0;
            }
        }
        return totalComplete;
    }
}
