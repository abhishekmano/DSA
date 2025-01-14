package Solutions.Graph.UnionFind;

import java.util.HashSet;

//323. Number of Connected Components in an Undirected Graph
//https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph
public class NumberOfConnectedComponents {
    public int countComponents(int n, int[][] edges) {
        UnionFind set = new UnionFind(n);
        for (int[] edge : edges) {
            set.union(edge[0], edge[1]);
        }
        HashSet<Integer> parents = new HashSet();
        for (int idx = 0; idx < n; ++idx) {
            parents.add(set.findParent(idx));
        }
        return parents.size();
    }
}
