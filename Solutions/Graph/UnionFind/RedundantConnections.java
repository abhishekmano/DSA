package Solutions.Graph.UnionFind;

//684. Redundant Connection
//https://leetcode.com/problems/redundant-connection
public class RedundantConnections {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        UnionFind set = new UnionFind(n);
        for (int[] edge : edges) {
            if (!set.union(edge[0] - 1, edge[1] - 1)) {
                return edge;
            }
        }
        return edges[0];
    }

    public class UnionFind {
        int[] size;
        int[] parent;

        public UnionFind(int n) {
            size = new int[n];
            parent = new int[n];
            for (int idx = 0; idx < n; ++idx) {
                size[idx] = 1;
                parent[idx] = idx;
            }
        }

        public int findParent(int a) {
            if (parent[a] == a)
                return a;
            else
                return parent[a] = findParent(parent[a]);
        }

        public boolean union(int a, int b) {
            int pa = findParent(a);
            int pb = findParent(b);
            if (pa == pb)
                return false;
            if (size[pa] >= size[pb]) {
                size[pa] += size[pb];
                parent[pb] = pa;
            } else {
                size[pb] += size[pb];
                parent[pa] = pb;
            }
            return true;
        }
    }
}
