package Solutions.Graph.BFS;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//785. Is Graph Bipartite?
//https://leetcode.com/problems/is-graph-bipartite
public class IsBiPartite {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
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
                        for (int next : graph[top]) {
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
