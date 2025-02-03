package Solutions.Graph.DijkstrasAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

//1928. Minimum Cost to Reach Destination in Time
//https://leetcode.com/problems/minimum-cost-to-reach-destination-in-time
public class MinimumCostToReachDestinationInTime {
    // PQ must be first based on cost then time
    // Otherwise wrong result will come (not sure exactly why)
    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        int n = passingFees.length;
        List<int[]>[] adjList = new List[n];
        int[] minTime = new int[n];
        int[] minCost = new int[n];
        Arrays.fill(minTime, Integer.MAX_VALUE);
        Arrays.fill(minCost, Integer.MAX_VALUE);
        for (int idx = 0; idx < n; ++idx) {
            adjList[idx] = new ArrayList<int[]>();
        }
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            int c = edge[2];
            adjList[a].add(new int[] { b, c });
            adjList[b].add(new int[] { a, c });
        }
        // [node , time , fee]
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[2] != b[2] ? a[2] - b[2] : a[1] - b[1]);
        pq.offer(new int[] { 0, 0, passingFees[0] });
        minTime[0] = 0;
        minCost[0] = passingFees[0];
        while (pq.size() != 0) {
            int[] top = pq.poll();
            int v = top[0];
            int t = top[1];
            int c = top[2];
            for (int[] next : adjList[v]) {
                int newTime = t + next[1];
                int node = next[0];
                int newFee = c + passingFees[node];
                if (newTime <= maxTime) {
                    if (minCost[node] > newFee) {
                        pq.offer(new int[] { node, newTime, newFee });
                        minCost[node] = newFee;
                    } else if (minTime[node] > newTime) {
                        pq.offer(new int[] { node, newTime, newFee });
                        minTime[node] = newTime;
                    }
                }
            }
        }
        return (minCost[n - 1] == Integer.MAX_VALUE) ? -1 : minCost[n - 1];
    }
}
