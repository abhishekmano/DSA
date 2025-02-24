package Solutions.Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//2467. Most Profitable Path in a Tree
//https://leetcode.com/problems/most-profitable-path-in-a-tree
public class MostProfitablePath {
    // Idea is to find the path of bob from its node to root
    // Store this path
    // Run bfs from root node and a traversal in bobs path
    // Remove the amount if the node is visited by alice or bob
    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        int n = amount.length;
        if (n == 1)
            return amount[0] / 2;
        List<Integer>[] adjList = new List[n];
        for (int idx = 0; idx < n; ++idx) {
            adjList[idx] = new ArrayList<Integer>();
        }
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            adjList[a].add(b);
            adjList[b].add(a);
        }
        List<Integer> bobPath = findPath(bob, 0, adjList);
        // System.out.println(bobPath);
        int max = Integer.MIN_VALUE;
        Queue<Pair> bfs = new LinkedList<Pair>();
        bfs.offer(new Pair(0, 0, -1));
        while (bfs.size() != 0) {
            int count = bfs.size();
            int bobIndex = -1;
            if (bobPath.size() != 0)
                bobIndex = bobPath.removeFirst();
            while (count != 0) {
                count--;
                var top = bfs.poll();
                int val = top.value;
                if (top.node == bobIndex) {
                    val += amount[top.node] / 2;
                } else {
                    val += amount[top.node];
                }
                amount[top.node] = 0;
                for (int next : adjList[top.node]) {
                    if (next != top.parent) {
                        bfs.offer(new Pair(next, val, top.node));
                    }
                }
                // if its a leaf node
                if (adjList[top.node].size() == 1 && top.node != 0) {
                    max = Math.max(max, val);
                }
            }
            if (bobIndex != -1)
                amount[bobIndex] = 0;
        }
        return max;

    }

    public record Pair(int node, int value, int parent) {
    };

    public List<Integer> findPath(int source, int dest, List<Integer>[] adjList) {
        int n = adjList.length;
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        Deque<Integer> stack = new ArrayDeque<Integer>();
        stack.push(source);
        while (stack.size() != 0) {
            int top = stack.pop();
            if (top == dest)
                break;
            for (int next : adjList[top]) {
                if (parent[next] == -1) {
                    parent[next] = top;
                    stack.push(next);
                }
            }
        }
        List<Integer> path = new LinkedList<Integer>();
        int start = dest;
        while (true) {
            path.addFirst(start);
            if (start == -1 || start == source)
                break;
            start = parent[start];
        }
        return path;

    }
}
