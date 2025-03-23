package Solutions.Graph.DijkstrasAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

//1976. Number of Ways to Arrive at Destination
//https://leetcode.com/problems/number-of-ways-to-arrive-at-destination
public class NumberOfWaysToArriveAtDestination {
    public int countPaths(int n, int[][] roads) {
        int[] time = new int[n];
        long[] ways = new long[n];
        int mod = 1000_000_007;
        Arrays.fill(time, Integer.MAX_VALUE);
        List<Edge>[] adjList = new List[n];
        for (int idx = 0; idx < n; ++idx) {
            adjList[idx] = new ArrayList<Edge>();
        }
        for (int[] road : roads) {
            int s = road[0];
            int d = road[1];
            int t = road[2];
            adjList[s].add(new Edge(d, t));
            adjList[d].add(new Edge(s, t));
        }
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>((a, b) -> a.time - b.time);
        time[0] = 0;
        ways[0] = 1;
        pq.offer(new Edge(0, 0));
        while (pq.size() != 0) {
            Edge top = pq.poll();
            if (top.node == n - 1)
                break;
            if (time[top.node] < top.time) {
                continue;
            }

            for (Edge next : adjList[top.node]) {
                int newTime = top.time + next.time;
                if (time[next.node] == newTime) {
                    ways[next.node] += ways[top.node];
                    ways[next.node] %= mod;
                } else if (time[next.node] > newTime) {
                    ways[next.node] = ways[top.node];
                    time[next.node] = newTime;
                    pq.offer(new Edge(next.node, newTime));
                }
            }
        }
        // print(ways);
        return (int) ways[n - 1];
    }

    public record Edge(int node, int time) {
    }
}
