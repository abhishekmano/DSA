package Solutions.Graph.UnionFind;

import java.util.Arrays;

//https://leetcode.com/problems/minimum-cost-walk-in-weighted-graph/
//3108. Minimum Cost Walk in Weighted Graph
public class MinimumCostWalkInGraph {
    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        int[] costs = new int[n];
        Arrays.fill(costs, Integer.MAX_VALUE);
        UnionFind set = new UnionFind(n);
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            int pav = costs[set.findParent(a)];
            int pab = costs[set.findParent(b)];
            set.union(a, b);
            int parent = set.findParent(a);
            costs[parent] = pav & pab & edge[2];
        }
        int qLen = query.length;
        int[] result = new int[qLen];
        for (int idx = 0; idx < qLen; ++idx) {
            int[] q = query[idx];
            int a = q[0];
            int b = q[1];
            if (set.isConnected(a, b)) {
                result[idx] = costs[set.findParent(a)];
            } else {
                result[idx] = -1;
            }
        }
        return result;
    }

    class UnionFind {
        int[] parent;
        int[] size;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            for (int idx = 0; idx < n; ++idx) {
                parent[idx] = idx;
                size[idx] = 1;
            }
        }

        public int findParent(int u) {
            if (parent[u] == u)
                return u;
            return parent[u] = findParent(parent[u]);
        }

        public boolean union(int u, int v) {
            int pu = findParent(u);
            int pv = findParent(v);
            if (pu == pv)
                return false;
            if (size[pu] > size[pv]) {
                parent[pv] = pu;
                size[pu] += size[pv];
            } else {
                parent[pu] = pv;
                size[pv] += size[pu];
            }
            return true;
        }

        public boolean isConnected(int u, int v) {
            return findParent(u) == findParent(v);
        }
    }
}
