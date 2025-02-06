package Algorithms.Graph.MST;

import java.util.Arrays;

public class Kruskal {
    class Solution {
        public int minimumCost(int n, int[][] connections) {
            int sum = 0;
            int count = 0;
            UnionFind set = new UnionFind(n + 1);
            Arrays.sort(connections, (a, b) -> a[2] - b[2]);
            for (int[] edge : connections) {
                if (set.union(edge[0], edge[1])) {
                    sum += edge[2];
                    count++;
                    if (count == n - 1)
                        return sum;
                }
            }
            return -1;
        }

        public class UnionFind {
            int[] parent;
            int[] size;

            public UnionFind(int n) {
                parent = new int[n];
                size = new int[n];
                for (int idx = 0; idx < n; ++idx) {
                    size[idx] = 1;
                    parent[idx] = idx;
                }
            }

            public int findParent(int a) {
                if (parent[a] == a)
                    return a;
                return parent[a] = findParent(parent[a]);
            }

            public boolean union(int a, int b) {
                int pa = findParent(a);
                int pb = findParent(b);
                if (pa == pb)
                    return false;
                if (size[pa] > size[pb]) {
                    size[pa] += size[pb];
                    parent[pb] = pa;
                } else {
                    size[pb] += size[pa];
                    parent[pa] = pb;
                }
                return true;
            }
        }
    }
}
