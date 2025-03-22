package Solutions.Graph.DijkstrasAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

//2737. Find the Closest Marked Node
//https://leetcode.com/problems/find-the-closest-marked-node
public class FindTheClosestMarkedNode {
    public int minimumDistance(int n, List<List<Integer>> edges, int s, int[] marked) {
        int[] visited = new int[n];
        Arrays.fill(visited, Integer.MAX_VALUE);
        boolean[] markedSet = new boolean[n];
        List<Edge>[] adjList = new List[n];
        for (int idx = 0; idx < n; ++idx) {
            adjList[idx] = new ArrayList<Edge>();
        }
        // Construct the adjList
        for (List<Integer> edge : edges) {
            int a = edge.get(0);
            int b = edge.get(1);
            int w = edge.get(2);
            adjList[a].add(new Edge(b, w));
        }
        // create the marked map
        for (int mark : marked) {
            markedSet[mark] = true;
        }
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>((a, b) -> a.weight - b.weight);
        pq.offer(new Edge(s, 0));
        visited[s] = 0;
        while (pq.size() != 0) {
            Edge top = pq.poll();
            if (visited[top.node] < top.weight)
                continue;
            if (markedSet[top.node])
                return top.weight;
            for (Edge next : adjList[top.node]) {
                int distance = next.weight + top.weight;
                if (visited[next.node] > distance) {
                    pq.offer(new Edge(next.node, distance));
                    visited[next.node] = distance;
                }
            }
        }
        return -1;

    }

    public record Edge(int node, int weight) {
    };
}
