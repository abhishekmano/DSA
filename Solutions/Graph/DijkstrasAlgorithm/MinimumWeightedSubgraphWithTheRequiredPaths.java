package Solutions.Graph.DijkstrasAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

//2203. Minimum Weighted Subgraph With the Required Paths
//https://leetcode.com/problems/minimum-weighted-subgraph-with-the-required-paths
public class MinimumWeightedSubgraphWithTheRequiredPaths {
    long max = Long.MAX_VALUE;

    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
        List<Pair>[] graph = new List[n];
        List<Pair>[] graphR = new List[n];
        for (int idx = 0; idx < n; ++idx) {
            graph[idx] = new ArrayList<Pair>();
            graphR[idx] = new ArrayList<Pair>();
        }
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            int w = edge[2];
            graph[a].add(new Pair(b, (long) w));
            graphR[b].add(new Pair(a, (long) w));
        }
        long[] fromS1 = getMin(src1, graph);
        long[] fromS2 = getMin(src2, graph);
        long[] fromD = getMin(dest, graphR);
        long res = max;
        for (int idx = 0; idx < n; ++idx) {
            long a = fromS1[idx];
            long b = fromS2[idx];
            long c = fromD[idx];
            if (a == max || b == max || c == max)
                continue;
            res = Math.min(a + b + c, res);
        }
        if (res == max)
            return -1;
        return res;

    }

    public long[] getMin(int source, List<Pair>[] graph) {
        int n = graph.length;
        long[] res = new long[n];
        Arrays.fill(res, max);
        res[source] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((a, b) -> a.weight.compareTo(b.weight));
        pq.offer(new Pair(source, 0l));
        while (!pq.isEmpty()) {
            Pair top = pq.remove();
            if (res[top.node] < top.weight)
                continue;
            for (Pair next : graph[top.node]) {
                long newWeight = top.weight + next.weight;
                if (newWeight < res[next.node]) {
                    res[next.node] = newWeight;
                    pq.offer(new Pair(next.node, newWeight));
                }
            }
        }
        return res;
    }

    public record Pair(int node, Long weight) {
    }
}
