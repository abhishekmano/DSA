package Solutions.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//133. Clone Graph
//https://leetcode.com/problems/clone-graph
public class CloneGraph {
    HashMap<Integer, Node> mapping = new HashMap<Integer, Node>();

    // using DFS recursive
    public Node cloneGraph(Node node) {
        if (node == null)
            return null;
        if (mapping.containsKey(node.val)) {
            return mapping.get(node.val);
        }
        Node newNode = new Node(node.val);
        mapping.put(node.val, newNode);
        for (Node adj : node.neighbors) {
            newNode.neighbors.add(cloneGraph(adj));
        }
        return newNode;
    }

    // BFS
    public Node cloneGraph2(Node node) {
        if (node == null)
            return null;
        HashMap<Node, Node> mapping = new HashMap<Node, Node>();
        HashSet<Node> visited = new HashSet<Node>();
        Queue<Node> bfs = new LinkedList<Node>();
        bfs.add(node);
        visited.add(node);
        while (bfs.size() != 0) {
            Node top = bfs.remove();
            if (!mapping.containsKey(top)) {
                mapping.put(top, new Node(top.val));
            }
            Node mapped = mapping.get(top);
            for (Node adj : top.neighbors) {
                if (!mapping.containsKey(adj)) {
                    mapping.put(adj, new Node(adj.val));
                }
                Node adjMapped = mapping.get(adj);
                mapped.neighbors.add(adjMapped);
                if (!visited.contains(adj)) {
                    visited.add(adj);
                    bfs.add(adj);
                }

            }
        }
        return mapping.get(node);
    }

    // Internal Node implementation
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
