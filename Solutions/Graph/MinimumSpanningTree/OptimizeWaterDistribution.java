package Solutions.Graph.MinimumSpanningTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

//1168. Optimize Water Distribution in a Village
//https://leetcode.com/problems/optimize-water-distribution-in-a-village
public class OptimizeWaterDistribution {
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        int[] visited = new int[n];
        Arrays.fill(visited, Integer.MAX_VALUE);
        PriorityQueue<Cost> create = new PriorityQueue<Cost>((a, b) -> a.cost - b.cost);
        List<Cost>[] adjList = new List[n];
        for (int idx = 0; idx < n; ++idx) {
            adjList[idx] = new ArrayList<Cost>();
        }
        for (int[] pipe : pipes) {
            int a = pipe[0] - 1;
            int b = pipe[1] - 1;
            int cost = pipe[2];
            adjList[a].add(new Cost(b, cost));
            adjList[b].add(new Cost(a, cost));
        }
        for (int idx = 0; idx < n; ++idx) {
            Cost newcost = new Cost(idx, wells[idx]);
            create.offer(newcost);
        }
        int total = 0;
        while (create.size() != 0) {
            Cost top = create.poll();
            if (visited[top.house] < top.cost)
                continue;
            visited[top.house] = 0;
            total += top.cost;
            for (Cost adj : adjList[top.house]) {
                if (visited[adj.house] > adj.cost) {
                    visited[adj.house] = adj.cost;
                    create.offer(adj);
                }
            }
        }
        return total;
    }

    public record Cost(int house, int cost) {
    };
}
