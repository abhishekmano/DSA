package Solutions.Graph.UnionFind;

//261. Graph Valid Tree
//https://leetcode.com/problems/graph-valid-tree/
public class GraphValidTree {
    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1)
            return false;
        UnionFind set = new UnionFind(n);
        for (int[] edge : edges) {
            int source = edge[0];
            int dest = edge[1];
            if (!set.union(source, dest))
                return false;
        }
        return true;
    }

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

    public int findParent(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = findParent(parent[a]);
    }

    public boolean union(int a, int b) {
        int pa = findParent(a);
        int pb = findParent(b);
        if (pa == pb)
            return false;
        if (size[pa] >= size[pb]) {
            parent[pb] = pa;
            size[pa] += size[pb];
        } else {
            parent[pa] = pb;
            size[pb] += size[pa];
        }
        return true;
    }
}
